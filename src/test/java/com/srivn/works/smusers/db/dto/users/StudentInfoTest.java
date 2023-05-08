package com.srivn.works.smusers.db.dto.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class StudentInfoTest {


    @Test
    void test_constructor() {
        // Arrange and Act
        StudentInfo actualStudentInfo = new StudentInfo("Jane", "Doe", "Gender", "User DOB", "User Type",
                "jane.doe@example.org", "primguardian.doe@example.org", "secguardian.doe@example.org");

        // Assert
        assertNull(actualStudentInfo.getAddressInfo());
        assertEquals("User Type", actualStudentInfo.getUserType());
        assertEquals("jane.doe@example.org", actualStudentInfo.getUserEmail());
        assertEquals("User DOB", actualStudentInfo.getUserDOB());
        assertEquals("primguardian.doe@example.org", actualStudentInfo.getPguardian());
        assertEquals("secguardian.doe@example.org", actualStudentInfo.getSguardian());
        assertEquals("Doe", actualStudentInfo.getLastName());
        assertEquals("Gender", actualStudentInfo.getGender());
        assertEquals("Jane", actualStudentInfo.getFirstName());
    }
}

