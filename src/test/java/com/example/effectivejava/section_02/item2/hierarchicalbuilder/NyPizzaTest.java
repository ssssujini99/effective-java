package com.example.effectivejava.section_02.item2.hierarchicalbuilder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NyPizzaTest {

    @Test
    void test() {
        // given

        // when
        Pizza pizza = new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(Pizza.Topping.SAUSAGE)
                .addTopping(Pizza.Topping.ONION)
                .build();

        // then
        assertThat(pizza instanceof NyPizza).isTrue();
    }

}