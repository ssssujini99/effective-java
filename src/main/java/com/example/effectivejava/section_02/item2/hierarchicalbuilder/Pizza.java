package com.example.effectivejava.section_02.item2.hierarchicalbuilder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;


/**
 * 코드 2-4: 계층적으로 설계된 클래스와 잘 어울리는 빌더 패턴
 */
public abstract class Pizza {
    public enum Topping { HAM, HUSHROOM, ONION, PEPPER, SAUSAGE }
    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        /**
         *
         * @return 구체 하위 클래스를 반환
         *
         * 공변 반환 타이핑: 하위 클래스의 메서드가 상위 클래스의 메서드가 정의한 반환 타입이 아닌,
         *               "그 하위 타입을 반환"하는 기능
         */
        abstract Pizza build();

        /**
         * 하위 클래스는 이 메서드를 재정의하여
         * this를 반환하도록 해야 한다.
         *
         * 이 추상메서드 self() 를 통해
         * 하위 클래스에서는 형변환하지 않고도 메서드 연쇄를 지원할 수 있다.
         */
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}
