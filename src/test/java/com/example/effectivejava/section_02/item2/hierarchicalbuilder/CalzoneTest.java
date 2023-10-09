package com.example.effectivejava.section_02.item2.hierarchicalbuilder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.effectivejava.section_02.item2.hierarchicalbuilder.Pizza.Topping.HAM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalzoneTest {

    @Test
    void test() {
        // given

        // when
        Pizza pizza = new Calzone.Builder()
                .addTopping(HAM)
                .sauceInside()
                .build();

        // then
        assertThat(pizza instanceof Calzone).isTrue();
    }

}