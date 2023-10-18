package com.example.effectivejava.section_02.item6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.junit.jupiter.api.Assertions.*;

class RomanNumeralsTest {

    private static final Logger logger = LoggerFactory.getLogger(RomanNumeralsTest.class);
    private static String string = "sdfa2340sdlfakdf";
    private static final int cnt = 10000000;

    @DisplayName("객체를 계속 생성하는 경우 테스트")
    @Test
    void isRomanNumeralSlowTest() {
        // given

        // when
        long startTime = System.currentTimeMillis();
        for(int i=0; i<cnt; i++) {
            RomanNumerals.isRomanNumeralSlow(string);
        }
        long endTime = System.currentTimeMillis();

        // then
        logger.info("총 걸린 시간: " + (endTime - startTime));
    }

    @DisplayName("정적 팩토리 메서드를 이용해 객체 재사용한 경우 테스트")
    @Test
    void isRomanNumeralFastTest() {
        // given

        // when
        long startTime = System.currentTimeMillis();
        for(int i=0; i<cnt; i++) {
            RomanNumerals.isRomanNumeralFast(string);
        }
        long endTime = System.currentTimeMillis();

        // then
        logger.info("총 걸린 시간: " + (endTime - startTime));
    }


}