package com.srivn.works.smusers.services;

import com.srivn.works.smusers.db.dto.users.UserInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.UserInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.entity.users.VerifTokenEn;
import com.srivn.works.smusers.db.entity.util.ClsnEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import com.srivn.works.smusers.db.repo.personal.AddressInfoRepo;
import com.srivn.works.smusers.db.repo.personal.ContactInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserLoginInfoRepo;
import com.srivn.works.smusers.db.repo.users.VerifTokenRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.util.AppMsg;
import com.srivn.works.smusers.util.AppUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    private VerifTokenRepo verifTokenRepo;
    @MockBean
    private DataTransactionService dataTransactionService;

    private UserAdminService userAdminService;


    ClsnValEn gender;
    Timestamp userDOB;
    ClsnValEn userType;
    UserInfoEn userInfoEn01;
    UserInfoEn userInfoEn02;
    UserLoginInfoEn userLoginInfoEn;
    VerifTokenEn verifTokenEn;
    User user;
    private static MockedStatic<AppUtil> mockedSettings;
    @BeforeAll
    static void atStart() {
        mockedSettings = mockStatic(AppUtil.class);
    }
    @AfterAll
    static void atEnd() {
        mockedSettings.close();
    }

    @BeforeEach
    void init(){
        gender = new ClsnValEn(100, "MALE", new ClsnEn(1, "GENDER"));
        userDOB = mock(Timestamp.class);
        userType = new ClsnValEn(100, "GUARDIAN", new ClsnEn(1, "USERTYPE"));
        userInfoEn01 = new GuardianInfoEn("Jane", "Doe", gender, userDOB, userType, "jane.doe@example.org");
        userInfoEn02 = new GuardianInfoEn("Jenny", "Doe", gender, userDOB, userType, "jenny.doe@example.org");
        userLoginInfoEn = new UserLoginInfoEn("jane.doe@example.org", "iloveyou", mock(Timestamp.class), 1);
        user = new User(userLoginInfoEn.getUserEmail(), userLoginInfoEn.getUserPassword(), new ArrayList<>());
        //
        userAdminService = spy(new UserAdminService(userInfoRepo, userLoginInfoRepo, addressInfoRepo, contactInfoRepo, verifTokenRepo ,dataTransactionService));
        verifTokenEn = new VerifTokenEn("ABC123", userLoginInfoEn);
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
    void test_loadUserByUsername_P() {
        // Arrange
        when(userLoginInfoRepo.findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        // Act
        UserDetails user01 = userAdminService.loadUserByUserName("jane.doe@example.org");
        // Assert
        assertEquals(user01.getUsername(), "jane.doe@example.org");
    }

    @Test
    void test_loadUserByUsername_EX() {
        // Arrange
        when(userLoginInfoRepo.findByUserEmail("jane.doe@example.org")).thenReturn(null);
        // Act
        SMException exception = assertThrows(SMException.class, () -> userAdminService.loadUserByUserName("jane.doe@example.org"));
        // Assert
        assertEquals("DNF", exception.getExType());
    }

}

