package com.srivn.works.smusers.db.entity.users;

import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import com.srivn.works.smusers.db.entity.personal.HealthInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Timestamp;

import static org.mockito.Mockito.mock;

class StudentInfoEnTest {

    ClsnValEn gender;
    Timestamp userDOB;
    ClsnValEn userType;
    ClsnValEn country;
    AddressInfoEn addressInfo;
    ContactInfoEn contactInfo,emergencyContact;
    ClsnValEn bloodGroup;
    HealthInfoEn healthInfo;

    GuardianInfoEn pguardian,sguardian;

    StudentInfoEn actualStudentInfoEn;

    @BeforeEach
    void init() {
        gender = new ClsnValEn(100, "MALE", new ClsnEn(1, "GENDER"));
        userDOB = mock(Timestamp.class);
        userType = new ClsnValEn(100, "STUDENT", new ClsnEn(1, "USERTYPE"));
        country = new ClsnValEn(100, "INDIA", new ClsnEn(1, "COUNTRY"));
        addressInfo = new AddressInfoEn("42", "Street", "Oxford", "MD", country, "21654");
        contactInfo = new ContactInfoEn(1L, "123", "456");
        emergencyContact = new ContactInfoEn(1L, "111", "222");
        bloodGroup = new ClsnValEn(100, "B+", new ClsnEn(1, "BLOODGROUP"));
        healthInfo = new HealthInfoEn(bloodGroup, "Notes");
        pguardian = new GuardianInfoEn("primguardian", "Doe", gender, userDOB, userType,"primguardian.doe@example.org");
        sguardian = new GuardianInfoEn("secguardian", "Doe", gender, userDOB, userType,"secguardian.doe@example.org");

        actualStudentInfoEn = new StudentInfoEn("Jane", "Doe", gender, userDOB, userType,
                "jane.doe@example.org", contactInfo, emergencyContact, addressInfo, healthInfo, pguardian, sguardian);
    }

}

