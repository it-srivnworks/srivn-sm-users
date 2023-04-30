package com.srivn.works.smusers.db.entity.personal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ContactInfoEnTest {
    @Test
    void test_constructor_N() {
        // Arrange and Act
        ContactInfoEn actualContactInfoEn = new ContactInfoEn();

        // Assert
        assertNull(actualContactInfoEn.getSecondaryNo());
        assertNull(actualContactInfoEn.getPrimaryNo());
    }

    @Test
    void test_constructor_P() {
        // Arrange and Act
            ContactInfoEn actualContactInfoEn = new ContactInfoEn(1L, "123", "456");
        // Assert
        assertEquals("123", actualContactInfoEn.getPrimaryNo());
        assertEquals("456", actualContactInfoEn.getSecondaryNo());
    }
}

