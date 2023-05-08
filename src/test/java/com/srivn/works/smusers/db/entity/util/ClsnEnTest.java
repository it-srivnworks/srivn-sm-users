package com.srivn.works.smusers.db.entity.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClsnEnTest {
    @Test
    void test_constructor() {
        // Arrange and Act
        ClsnEn actualClsnEn = new ClsnEn(1, "Clsn Key");
        // Assert
        assertEquals(1, actualClsnEn.getClsnID());
        assertEquals("Clsn Key", actualClsnEn.getClsnKey());
    }
}

