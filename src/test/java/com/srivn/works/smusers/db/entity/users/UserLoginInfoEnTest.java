package com.srivn.works.smusers.db.entity.users;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserLoginInfoEnTest {
    @Test
    void test_constructor_N() {
        // Arrange and Act
        UserLoginInfoEn actualUserLoginInfoEn = new UserLoginInfoEn();
        // Assert
        assertEquals(0, actualUserLoginInfoEn.getCurrentStatus());
        assertNull(actualUserLoginInfoEn.getUserPassword());
        assertNull(actualUserLoginInfoEn.getUserEmail());
        assertNull(actualUserLoginInfoEn.getLastLogin());
    }

    @Test
    void test_constructor2() {
        // Arrange
        Timestamp lastLogin = mock(Timestamp.class);
        // Act
        UserLoginInfoEn actualUserLoginInfoEn = new UserLoginInfoEn("jane.doe@example.org", "iloveyou", lastLogin, 1);
        // Assert
        assertEquals(1, actualUserLoginInfoEn.getCurrentStatus());
        assertEquals("iloveyou", actualUserLoginInfoEn.getUserPassword());
        assertEquals("jane.doe@example.org", actualUserLoginInfoEn.getUserEmail());
        assertSame(lastLogin, actualUserLoginInfoEn.getLastLogin());
    }
}

