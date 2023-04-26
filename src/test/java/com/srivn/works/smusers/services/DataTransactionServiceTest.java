package com.srivn.works.smusers.services;

import static org.mockito.Mockito.mock;

import com.srivn.works.smusers.SmusersApplication;
import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.StaffInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import com.srivn.works.smusers.db.repo.users.*;
import jakarta.persistence.EntityManagerFactory;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = {SmusersApplication.class})
@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
@DataJpaTest
class DataTransactionServiceTest {
    @MockBean
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    UserInfoRepo userInfoRepo;

    @Autowired
    GuardianInfoRepo guardianInfoRepo;

    @Autowired
    ClsnRepo clsnRepo;
    @Autowired
    ClsnValRepo clsnValRepo;
    @Autowired
    StaffInfoRepo staffInfoRepo;
    @Autowired
    private DataTransactionService dataTransactionService;
    ClsnValEn gender;
    Timestamp userDOB;
    Timestamp lastLogin;
    ClsnValEn userType;

    GuardianInfoEn actualGuardianInfoEn;
    UserLoginInfoEn actualUserLoginInfoEn;
    ClsnValEn dept;
    ClsnValEn title;
    ClsnValEn country;
    AddressInfoEn addressInfo;
    ContactInfoEn contactInfo;
    StaffInfoEn actualStaffInfoEn;

    @BeforeEach
    void init() {
        gender = new ClsnValEn(100, "MALE", new ClsnEn(1, "GENDER"));
        userDOB = mock(Timestamp.class);
        lastLogin = mock(Timestamp.class);
        country = new ClsnValEn(100, "INDIA", new ClsnEn(1, "COUNTRY"));
        userType = new ClsnValEn(100, "STAFF", new ClsnEn(1, "USERTYPE"));
        actualGuardianInfoEn = new GuardianInfoEn("Jane", "Doe", gender, userDOB, userType,
                "jane.doe@example.org");
        actualUserLoginInfoEn = new UserLoginInfoEn("jane.doe@example.org", "iloveyou", lastLogin, 1);
        dept = new ClsnValEn(100, "ELEC", new ClsnEn(1, "DEPT"));
        title = new ClsnValEn(100, "MR", new ClsnEn(1, "TITLE"));
        addressInfo = new AddressInfoEn("42", "Street", "Oxford", "MD", country, "21654");
        contactInfo = new ContactInfoEn(1L, "123", "456");
        actualStaffInfoEn = new StaffInfoEn("Jane", "Doe", gender, userDOB, userType, "jane.doe@example.org",
                contactInfo, addressInfo, dept, title);
    }

    @Test
    @Disabled
    void test_addGDNDetailsAndLogin() {
        // Act
        dataTransactionService.addGDNDetailsAndLogin(actualGuardianInfoEn, actualUserLoginInfoEn);
    }

    @Test
    @Disabled
    void test_deleteGDNDetailsAndLogin() {
        dataTransactionService.deleteGDNDetailsAndLogin(actualGuardianInfoEn, actualUserLoginInfoEn);
    }

    @Test
    @Disabled
    void test_addSTFDetailsAndLogin() {
        // Act
        dataTransactionService.addSTFDetailsAndLogin(actualStaffInfoEn, actualUserLoginInfoEn);
    }

    @Test
    @Disabled
    void test_deleteSTFDetailsAndLogin() {
        // Act
        dataTransactionService.deleteSTFDetailsAndLogin(actualStaffInfoEn, actualUserLoginInfoEn);
    }

}

