package com.srivn.works.smusers.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppUtilTest {
    /**
     * Method under test: {@link AppUtil#generatePwd()}
     */
    @Test
    void test_generatePwd() {
        assertEquals(12,AppUtil.generatePwd().length());
    }
}

