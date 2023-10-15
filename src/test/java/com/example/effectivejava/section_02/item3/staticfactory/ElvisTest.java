package com.example.effectivejava.section_02.item3.staticfactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ElvisTest {

    @DisplayName("정적 팩터리 메서드 방식의 싱글턴 테스트")
    @Test
    void test() {
        // given
        Elvis instance1 = Elvis.getInstance();
        Elvis instance2 = Elvis.getInstance();

        // when

        // then
        assertThat(instance1).isEqualTo(instance2);
    }

}