package com.srivn.works.smusers.db.dto.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class GuardianInfoTest {

    @Test
    void test_constructor() {
        // Arrange and Act
        // TODO: Populate arranged inputs
        GuardianInfo actualGuardianInfo = new GuardianInfo("primguardian", "Doe", "MALE", "1990-01-01", "STAFF",
                "primguardian.doe@example.org");

        // Assert
        // TODO: Add assertions on result
        assertEquals("primguardian", actualGuardianInfo.getFirstName());
        assertEquals("Doe", actualGuardianInfo.getLastName());
        assertEquals("MALE", actualGuardianInfo.getGender());
        assertSame("1990-01-01", actualGuardianInfo.getUserDOB());
        assertEquals("STAFF", actualGuardianInfo.getUserType());
        assertEquals("primguardian.doe@example.org", actualGuardianInfo.getUserEmail());
    }

}

