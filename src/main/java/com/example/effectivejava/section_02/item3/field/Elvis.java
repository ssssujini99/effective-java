package com.example.effectivejava.section_02.item3.field;

/**
 * 코드 3-1: public static final 필드 방식의 싱글턴
 */
public class Elvis {

    public static final Elvis INSTANCE = new Elvis();

    /**
     * 두 번째 객체가 생성되려 할 때에 예외를 던지도록 수정
     */
    private Elvis() {
        if (INSTANCE != null) {
            throw new RuntimeException("생성자를 호출할 수 없습니다!");
        }
    }
}
