package com.example.effectivejava.section_02.item3.enumtype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ElvisTest {

    @DisplayName("열거 타입 방식의 싱글턴 테스트")
    @Test
    void test() {
        // given
        Elvis instance1 = Elvis.INSTANCE;
        Elvis instance2 = Elvis.INSTANCE;

        // when

        // then
        assertThat(instance1).isEqualTo(instance2);
    }

}