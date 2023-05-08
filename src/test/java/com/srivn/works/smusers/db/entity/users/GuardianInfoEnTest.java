package com.srivn.works.smusers.db.entity.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import com.srivn.works.smusers.db.entity.util.ClsnEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GuardianInfoEnTest {

    ClsnValEn gender;
    Timestamp userDOB;
    ClsnValEn userType;


    GuardianInfoEn actualGuardianInfoEn;
    @BeforeEach
    void init(){
        gender = new ClsnValEn(100, "MALE", new ClsnEn(1, "GENDER"));
        userDOB = mock(Timestamp.class);
        userType = new ClsnValEn(100, "STAFF", new ClsnEn(1, "USERTYPE"));
        actualGuardianInfoEn = new GuardianInfoEn("primguardian", "Doe", gender, userDOB, userType,
                "primguardian.doe@example.org");
    }

    @Test
    void test_constructor_N() {
        actualGuardianInfoEn = new GuardianInfoEn();
        // Assert
        assertNull(actualGuardianInfoEn.getFirstName());
        assertNull(actualGuardianInfoEn.getLastName());
        assertNull(actualGuardianInfoEn.getGender());
        assertNull(actualGuardianInfoEn.getUserDOB());
        assertNull(actualGuardianInfoEn.getUserType());
        assertNull(actualGuardianInfoEn.getUserEmail());
    }

    @Test
    void test_constructor_P() {
        // Assert
        assertEquals("primguardian", actualGuardianInfoEn.getFirstName());
        assertEquals("Doe", actualGuardianInfoEn.getLastName());
        assertEquals("MALE", actualGuardianInfoEn.getGender().getValue());
        assertSame(userDOB, actualGuardianInfoEn.getUserDOB());
        assertEquals("STAFF", actualGuardianInfoEn.getUserType().getValue());
        assertEquals("primguardian.doe@example.org", actualGuardianInfoEn.getUserEmail());
    }
}

