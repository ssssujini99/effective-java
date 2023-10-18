package com.example.effectivejava.section_02.item5.using_static_utility_wrong;

import java.util.List;


/**
 * 코드 5-1: 정적 유틸리티를 잘못 사용한 예 - 유연하지 않고 테스트하기 어렵다.
 */

// SpellChecker: 맞춤법 검사기

public class SpellChecker {

    // private static final Lexicon dictionary = ... ; // 사전에 의존함 -> 그런데 단 하나만 사용..?

    private SpellChecker() {} // 객체 생성 방지

    /**
     *
     * public static 메서드로 제공
     */
//    public static boolean isValid(String word) {
//        // dictionary 를 이용 ..
//    }

//    public static List<String> suggestions(String typo) {
//        // dictionary 를 이용 ..
//    }
}
