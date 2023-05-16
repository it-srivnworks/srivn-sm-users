package com.srivn.works.smusers.db.entity.users;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class VerifTokenEnTest {

    @Test
    void test_constructor_N() {
        // Arrange
        VerifTokenEn actualVerifTokenEn = new VerifTokenEn();
        // Act
        // Assert
        assertNull(actualVerifTokenEn.getToken());
        assertNull(actualVerifTokenEn.getUser());
    }

    /**
     * Method under test: {@link VerifTokenEn#VerifTokenEn(String, UserLoginInfoEn)}
     */
    @Test
    void test_constructor() {
        // Arrange
        UserLoginInfoEn user = UserLoginInfoEn.createNew("jane.doe@example.org");

        // Act
        VerifTokenEn actualVerifTokenEn = new VerifTokenEn("ABC123", user);

        // Assert
        assertSame(user, actualVerifTokenEn.getUser());
        assertEquals("ABC123", actualVerifTokenEn.getToken());
    }

    @Test
    void test_constructor2() {
        // Arrange
        UserLoginInfoEn user = new UserLoginInfoEn("jane.doe@example.org", "iloveyou", mock(Timestamp.class), 1440);
        // Act
        VerifTokenEn actualVerifTokenEn = new VerifTokenEn("ABC123", user);
        // Assert
        assertSame(user, actualVerifTokenEn.getUser());
        assertEquals("ABC123", actualVerifTokenEn.getToken());
    }
}

