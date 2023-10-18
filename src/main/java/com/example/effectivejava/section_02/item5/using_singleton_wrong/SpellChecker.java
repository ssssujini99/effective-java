package com.example.effectivejava.section_02.item5.using_singleton_wrong;

import java.util.List;

/**
 * 코드 5-2: 싱글턴을 잘못 사용한 예 - 유연하지 않고 테스트하기 어렵다.
 */
public class SpellChecker {

    // private final Lexicon dictionary = ...; // 사전에 의존함 -> 그런데 단 하나만 사용..?

    private SpellChecker() {}

    /**
     *
     * 싱글턴으로 구현
     */
    // public static SpellChecker INSTANCE = new SpellChecker(...);

//    public boolean isValid(String word) {
//        // ...
//    }

//    public List<String> suggestions(String typo) {
//        // ...
//    }
}
