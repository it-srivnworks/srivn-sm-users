package com.srivn.works.smusers.services;

import com.srivn.works.smusers.db.dto.users.GuardianInfo;
import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.StaffInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.sql.Timestamp;

import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;

@ContextConfiguration(classes = {DataTransactionService.class})
@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
class DataTransactionServiceTest {
    @Autowired
    private DataTransactionService dataTransactionService;

    @MockBean
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    private EntityManager entityManager;

    /*********************Data*********************/
    GuardianInfo guardianInfo;

    ClsnValEn gender;
    Timestamp userDOB;
    ClsnValEn userType;
    UserLoginInfoEn userLoginInfoEn;
    GuardianInfoEn guardianInfoEn01, guardianInfoEn02;

    @BeforeEach
    void init() {
        guardianInfo = new GuardianInfo("Jane", "Doe", "MALE", "1990-01-01", "STAFF", "jane.doe@example.org");
        gender = new ClsnValEn(100, "MALE", new ClsnEn(1, "GENDER"));
        userDOB = mock(Timestamp.class);
        userType = new ClsnValEn(100, "STAFF", new ClsnEn(1, "USERTYPE"));
        guardianInfoEn01 = new GuardianInfoEn("Jane", "Doe", gender, userDOB, userType, "jane.doe@example.org");
        guardianInfoEn02 = new GuardianInfoEn("Jenny", "Doe", gender, userDOB, userType, "jenny.doe@example.org");
        userLoginInfoEn = new UserLoginInfoEn("jane.doe@example.org", "iloveyou", mock(Timestamp.class), 1);
        //
    }

    @Test
     void test_addGDNDetailsAndLogin() {
    }

    @Test
    void test_addGDNDetailsAndLogin2() {
   }


}

