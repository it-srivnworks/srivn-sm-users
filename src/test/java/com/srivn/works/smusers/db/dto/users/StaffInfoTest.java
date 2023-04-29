package com.srivn.works.smusers.db.dto.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class StaffInfoTest {

    @Test
    void test_constructor() {
        // Arrange
        // Act
        StaffInfo actualStaffInfo = new StaffInfo("Jane", "Doe", "MALE", "1990-01-01", "STAFF", "jane.doe@example.org",
                "ELEC", "MR");

        // Assert
        assertEquals("Jane", actualStaffInfo.getFirstName());
        assertEquals("Doe", actualStaffInfo.getLastName());
        assertEquals("MALE", actualStaffInfo.getGender());
        assertSame("1990-01-01", actualStaffInfo.getUserDOB());
        assertEquals("STAFF", actualStaffInfo.getUserType());
        assertEquals("jane.doe@example.org", actualStaffInfo.getUserEmail());
        assertEquals("ELEC", actualStaffInfo.getDept());
        assertEquals("MR", actualStaffInfo.getTitle());
    }
}

