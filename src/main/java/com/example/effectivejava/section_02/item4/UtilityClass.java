package com.example.effectivejava.section_02.item4;

import java.util.ArrayList;

/**
 * 코드 4-1: 인스턴스를 만들 수 없는 유틸리티 클래스
 */
public class UtilityClass {

    /**
     * private 생성자
     *
     * - 클래스의 인스턴스화를 막을 수 있다 (기본 생성자가 만들어지는 것을 막는다.)
     * - 상속을 불가능하게 한다
     */
    private UtilityClass() {
        throw new AssertionError();
    }

}
