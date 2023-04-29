package com.srivn.works.smusers.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class AppCTest {
    /**
     * Method under test: {@link AppC.Status#getKey(int)}
     */
    @Test
    void test_getKey() {
        // Arrange, Act and Assert
        assertEquals("NEW", AppC.Status.getKey(1));
        assertEquals("ACTIVE", AppC.Status.getKey(2));
        assertNull(AppC.Status.getKey(0));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link AppC.Status#valueOf(String)}
     *   <li>{@link AppC.Status#getCode()}
     * </ul>
     */
    @Test
    void test_valueOf() {
        // Arrange, Act and Assert
        assertEquals(1, AppC.Status.valueOf("NEW").getCode());
    }
}

