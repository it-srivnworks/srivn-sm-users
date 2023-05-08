package com.srivn.works.smusers.db.dto.personal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactInfoTest {

    @Test
    void test_constructor() {
        // Arrange
        ContactInfo contactInfo = new ContactInfo( "123", "456");
        // Assert
        // TODO: Add assertions on result
        assertEquals("123", contactInfo.getPrimaryNo());
        assertEquals("456", contactInfo.getSecondaryNo());
    }
}

