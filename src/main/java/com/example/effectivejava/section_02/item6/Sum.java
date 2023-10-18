package com.example.effectivejava.section_02.item6;

/**
 * 코드 6-3: 박싱된 기본 타입보다는 "기본 타입"을 사용하고, 의도치 않은 오토박싱이 숨어있지 않도록 주의하자.
 */
public class Sum {

    public static long sumSlow() {
        Long sum = 0L;
        for (long i=0; i<Integer.MAX_VALUE; i++) {
            sum += i; // 문제인 부분 -> 여기서 매번 불필요한 Long 인스턴스가 생성된다
        }
        return sum;
    }

    public static long sumFast() {
        long sum = 0L;
        for (long i=0; i<Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
}
