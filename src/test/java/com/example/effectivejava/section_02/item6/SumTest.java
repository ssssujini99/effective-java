package com.example.effectivejava.section_02.item6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class SumTest {

    private static final Logger logger = LoggerFactory.getLogger(RomanNumeralsTest.class);

    @DisplayName("sumSlow 성능 테스트")
    @Test
    void sumSlowTest() {
        // given

        // when
        long startTime = System.currentTimeMillis();
        Sum.sumSlow();
        long endTime = System.currentTimeMillis();

        // then
        logger.info("sumSlow 수행 시 총 걸린 시간: " + (endTime - startTime));
    }

    @DisplayName("sumFast 성능 테스트")
    @Test
    void sumFastTest() {
        // given

        // when
        long startTime = System.currentTimeMillis();
        Sum.sumFast();
        long endTime = System.currentTimeMillis();

        // then
        logger.info("sumFast 수행 시 총 걸린 시간: " + (endTime - startTime));
    }
}