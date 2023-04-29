package com.srivn.works.smusers.db.dto.personal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthInfoTest {


    @Test
    void test_constructor() {
        // Arrange
        HealthInfo healthInfo = new HealthInfo("B+", "notes");
        // Assert
        // TODO: Add assertions on result
        assertEquals("B+", healthInfo.getBloodGroup());
        assertEquals("notes", healthInfo.getNotes());
    }
}

