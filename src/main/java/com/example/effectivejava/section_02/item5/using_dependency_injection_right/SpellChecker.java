package com.example.effectivejava.section_02.item5.using_dependency_injection_right;

import java.util.List;

/**
 * 코드 5-3: 의존 객체 주입은 유연성과 테스트 용이성을 높여준다.
 */
public class SpellChecker {

    private final Lexicon dictionary;


    /**
     * 인스턴스 SpellChecker를 생성할 때
     * 생성자에 필요한 자원(dictionary)를 넘겨주는 방식
     *
     * @param dictionary
     */
    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public boolean isValid(String word) {
        // ...
    }

    public List<String> suggestions(String typo) {
        // ...
    }
}
