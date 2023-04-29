package com.srivn.works.smusers.db.dto.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class UserInfoTest {
    /**
     * Method under test: {@link UserInfo#UserInfo()}
     */
    @Test
    void test_constructor() {
        // Arrange and Act
        // TODO: Populate arranged inputs
        UserInfo userInfo = new UserInfo("Jane", "Doe", "MALE", "1990-01-01", "STAFF",
                "jane.doe@example.org");

        // Assert
        assertEquals("Jane", userInfo.getFirstName());
        assertEquals("Doe", userInfo.getLastName());
        assertEquals("MALE", userInfo.getGender());
        assertSame("1990-01-01", userInfo.getUserDOB());
        assertEquals("STAFF", userInfo.getUserType());
        assertEquals("jane.doe@example.org", userInfo.getUserEmail());
    }


}

