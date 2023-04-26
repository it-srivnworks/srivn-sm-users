package com.srivn.works.smusers.db.entity.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class ClsnValEnTest {

    @Test
    void test_constructor() {
        // Arrange and Act
        ClsnValEn actualClsnValEn = new ClsnValEn(1, "42", new ClsnEn(1, "Clsn Key"));
        // Assert
        ClsnEn expectedClsn = actualClsnValEn.clsn;
        ClsnEn clsn = actualClsnValEn.getClsn();
        assertSame(expectedClsn, clsn);
        assertEquals("42", actualClsnValEn.getValue());
        assertEquals(1, actualClsnValEn.getClsnValID());
        assertEquals(1, clsn.getClsnID());
        assertEquals("Clsn Key", clsn.getClsnKey());
    }
}

