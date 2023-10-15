package com.example.effectivejava.section_02.item3.field;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ElvisTest {

    @DisplayName("public static final 필드 방식의 싱글턴 test")
    @Test
    void test1() {
        // given
        Elvis instance1 = Elvis.INSTANCE;
        Elvis instance2 = Elvis.INSTANCE;

        // when

        // then
        assertThat(instance1).isEqualTo(instance2);
    }


    @Test
    void test2() throws NoSuchMethodException {

        Elvis elvis = Elvis.INSTANCE;
        Constructor<Elvis> constructor = (Constructor<Elvis>) elvis.getClass().getDeclaredConstructor();
        constructor.setAccessible(true);

        assertThrows(InvocationTargetException.class,
                constructor::newInstance);
    }
}