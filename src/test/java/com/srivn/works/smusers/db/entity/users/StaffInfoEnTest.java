package com.srivn.works.smusers.db.entity.users;

import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StaffInfoEnTest {

    ClsnValEn gender;
    Timestamp userDOB;
    ClsnValEn userType;
    ClsnValEn country;
    ClsnValEn dept;
    ClsnValEn title;
    AddressInfoEn addressInfo;
    ContactInfoEn contactInfo;
    StaffInfoEn actualStaffInfoEn;

    @BeforeEach
    void init() {
        gender = new ClsnValEn(100, "MALE", new ClsnEn(1, "GENDER"));
        userDOB = mock(Timestamp.class);
        userType = new ClsnValEn(100, "STAFF", new ClsnEn(1, "USERTYPE"));
        contactInfo = new ContactInfoEn(1L, "123", "456");
        country = new ClsnValEn(100, "INDIA", new ClsnEn(1, "COUNTRY"));
        dept = new ClsnValEn(100, "ELEC", new ClsnEn(1, "DEPT"));
        title = new ClsnValEn(100, "MR", new ClsnEn(1, "TITLE"));
        addressInfo = new AddressInfoEn("42", "Street", "Oxford", "MD", country, "21654");
        contactInfo = new ContactInfoEn(1L, "123", "456");
        actualStaffInfoEn = new StaffInfoEn("Jane", "Doe", gender, userDOB, userType, "jane.doe@example.org",
                contactInfo, addressInfo, dept, title);
    }

    @Test
    void test_constructor_N() {
        actualStaffInfoEn = new StaffInfoEn();
        // Assert
        assertNull(actualStaffInfoEn.getFirstName());
        assertNull( actualStaffInfoEn.getLastName());
        assertNull(actualStaffInfoEn.getGender());
        assertNull( actualStaffInfoEn.getUserDOB());
        assertNull(actualStaffInfoEn.getUserType());
        assertNull(actualStaffInfoEn.getUserEmail());
        assertNull(actualStaffInfoEn.getContactInfo());
        assertNull(actualStaffInfoEn.getAddressInfo());
        assertNull(actualStaffInfoEn.getDept());
        assertNull(actualStaffInfoEn.getTitle());
    }

    @Test
    void test_constructor_P() {
        // Assert
        assertEquals("Jane", actualStaffInfoEn.getFirstName());
        assertEquals("Doe", actualStaffInfoEn.getLastName());
        assertEquals("MALE", actualStaffInfoEn.getGender().getValue());
        assertSame(userDOB, actualStaffInfoEn.getUserDOB());
        assertEquals("STAFF", actualStaffInfoEn.getUserType().getValue());
        assertEquals("jane.doe@example.org", actualStaffInfoEn.getUserEmail());
        assertEquals("123", actualStaffInfoEn.getContactInfo().getPrimaryNo());
        assertEquals("21654", actualStaffInfoEn.getAddressInfo().getZipCode());
        assertEquals("ELEC", actualStaffInfoEn.getDept().getValue());
        assertEquals("MR", actualStaffInfoEn.getTitle().getValue());
    }
}

