package com.example.effectivejava.section_02.item6;

import java.util.regex.Pattern;

/**
 * 코드 6-1: 성능을 훨씬 더 끌어올릴 수 있다!
 * 코드 6-2: 값비싼 객체를 재사용해 성능을 개선한다.
 */
public class RomanNumerals {

    /**
     * isRomanNumeralSlow 함수의 문제점
     *
     * String.matches 메서드를 사용
     * 해당 메서드가 내부에서 만드는 정규 표현식용 Pattern 인스턴스는 한 번 쓰고 버려져서 곧바로 가비지 컬렉션의 대상이 된다.
     * Pattern 은 생성비용이 높은 클래스 중 하나
     */
    static boolean isRomanNumeralSlow(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    /**
     *
     * 늘 같은 Pattern 이 필요함이 보장되고 재사용 빈도가 높다면
     * 다음과 같이 상수(static final)로 초기에 캐싱해놓고 재사용하기
     *
     * 성능을 개선하려면 필요한 정규표현식을 표현하는 (불변) Pattern 인스턴스를
     * 클래스 생성시 초기화 (정적 초기화) 과정에서 직접 생성해 캐싱해두고,
     * 나중에 isRomanNumeralFast 함수가 호출될 때마다 이를 재사용한다.
     */
    private static final Pattern ROMAN = Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})"
                    + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeralFast(String s) {
        return ROMAN.matcher(s).matches();
    }

}
