package com.srivn.works.smusers.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.srivn.works.smusers.db.dto.users.UserInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.UserInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import com.srivn.works.smusers.db.repo.personal.AddressInfoRepo;
import com.srivn.works.smusers.db.repo.personal.ContactInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserLoginInfoRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.util.AppC;
import com.srivn.works.smusers.util.AppMsg;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserAdminService.class})
@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
class UserAdminServiceTest {

    @MockBean
    private UserInfoRepo userInfoRepo;
    @MockBean
    private UserLoginInfoRepo userLoginInfoRepo;
    @MockBean
    private AddressInfoRepo addressInfoRepo;
    @MockBean
    private ContactInfoRepo contactInfoRepo;
    @MockBean
    private DataTransactionService dataTransactionService;
    @Autowired
    private UserAdminService userAdminService;


    ClsnValEn gender;
    Timestamp userDOB;
    ClsnValEn userType;
    UserInfoEn userInfoEn01;
    UserInfoEn userInfoEn02;
    @BeforeEach
    void init(){
        gender = new ClsnValEn(100, "MALE", new ClsnEn(1, "GENDER"));
        userDOB = mock(Timestamp.class);
        userType = new ClsnValEn(100, "GUARDIAN", new ClsnEn(1, "USERTYPE"));
        userInfoEn01 = new GuardianInfoEn("Jane", "Doe", gender, userDOB, userType, "jane.doe@example.org");
        userInfoEn02 = new GuardianInfoEn("Jenny", "Doe", gender, userDOB, userType, "jenny.doe@example.org");
    }


    @Test
    void test_getUserInfoAll() {
        // Arrange and Act
        List<UserInfoEn> actualUserEnInfoAll =  new ArrayList<UserInfoEn>();
        actualUserEnInfoAll.add(userInfoEn01);
        actualUserEnInfoAll.add(userInfoEn02);
        when(userInfoRepo.findAll()).thenReturn(actualUserEnInfoAll);
        // Act
        List<UserInfo> userInfoList = userAdminService.getUserInfoAll();
        // Assert
        assertEquals(2,userInfoList.size());
        assertEquals("jane.doe@example.org",userInfoList.get(0).getUserEmail());
    }

    @Test
    void test_checkIfEmailExist_P() {
        // Arrange
        when(userLoginInfoRepo.checkUserEmail("jane.doe@example.org")).thenReturn(1);
        // Act
        SMMessage actualCheckIfEmailExistResult = userAdminService.checkIfEmailExist("jane.doe@example.org");
        // Assert
        assertEquals("EXIST", actualCheckIfEmailExistResult.getAppCode());
        assertEquals("Data %s Exist", actualCheckIfEmailExistResult.getMessage());
        verify(userLoginInfoRepo).checkUserEmail("jane.doe@example.org");
    }

    @Test
    void test_checkIfEmailExist_EX() {
        // Arrange
        when(userLoginInfoRepo.checkUserEmail("not.present@example.org")).thenReturn(0);
        // Act and Assert
        SMException exception = assertThrows(SMException.class, () -> userAdminService.checkIfEmailExist("not.present@example.org"));
        assertEquals(AppMsg.Err.ERR_DNF_001.getCode(), exception.getExType());
        assertEquals(AppMsg.Err.ERR_DNF_001.getMsgP("userEmail"), exception.getMessage());
        verify(userLoginInfoRepo).checkUserEmail("not.present@example.org");
    }

    @Test
    void test_getLoginInfo() {
        // Arrange
        UserLoginInfoEn ulEn = userAdminService.getLoginInfo("jane.doe@example.org");
        // Act and Assert
        assertEquals("jane.doe@example.org", ulEn.getUserEmail());
        assertEquals(12,ulEn.getUserPassword().length());
        assertEquals(Timestamp.from(Instant.now()).getClass(),ulEn.getLastLogin().getClass());
        assertEquals(AppC.Status.NEW.getCode(), ulEn.getCurrentStatus());
    }
}
