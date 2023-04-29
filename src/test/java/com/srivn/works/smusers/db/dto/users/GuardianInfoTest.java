package com.srivn.works.smusers.db.dto.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class GuardianInfoTest {

    @Test
    void test_constructor() {
        // Arrange and Act
        // TODO: Populate arranged inputs
        GuardianInfo actualGuardianInfo = new GuardianInfo("Jane", "Doe", "MALE", "1990-01-01", "STAFF",
                "jane.doe@example.org");

        // Assert
        // TODO: Add assertions on result
        assertEquals("Jane", actualGuardianInfo.getFirstName());
        assertEquals("Doe", actualGuardianInfo.getLastName());
        assertEquals("MALE", actualGuardianInfo.getGender());
        assertSame("1990-01-01", actualGuardianInfo.getUserDOB());
        assertEquals("STAFF", actualGuardianInfo.getUserType());
        assertEquals("jane.doe@example.org", actualGuardianInfo.getUserEmail());
    }

}

