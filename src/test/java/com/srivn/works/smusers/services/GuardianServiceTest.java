package com.srivn.works.smusers.services;

import com.srivn.works.smusers.db.dto.users.GuardianInfo;
import com.srivn.works.smusers.db.dto.users.StaffInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.StaffInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import com.srivn.works.smusers.db.mappers.CustomGuardianInfoMapper;
import com.srivn.works.smusers.db.repo.personal.AddressInfoRepo;
import com.srivn.works.smusers.db.repo.personal.ContactInfoRepo;
import com.srivn.works.smusers.db.repo.users.GuardianInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserLoginInfoRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;

@ContextConfiguration(classes = {UserAdminService.class, GuardianService.class})
@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
class GuardianServiceTest {
    /*********************Components*********************/
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
    private UserAdminService userAdminService;
    @MockBean
    private GuardianInfoRepo guardianRepo;
    @MockBean
    private CustomGuardianInfoMapper cGuardianMapper;
    GuardianService guardianService;

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
        userAdminService = spy(new UserAdminService(userInfoRepo, userLoginInfoRepo, addressInfoRepo, contactInfoRepo, dataTransactionService));
        guardianService = spy(new GuardianService(userAdminService, guardianRepo, cGuardianMapper));

    }

    @Test
    void test_addNewGuardianInfo_P() {
        //Arrange
        when(userAdminService.getUserLoginRepo().checkUserEmail(guardianInfo.getUserEmail())).thenReturn(0);
        when(cGuardianMapper.DTOToEn(guardianInfo)).thenReturn(guardianInfoEn01);
        when(userAdminService.getLoginInfo(guardianInfo.getUserEmail())).thenReturn(userLoginInfoEn);
        //Act
        SMMessage actual = guardianService.addNewGuardianInfo(guardianInfo);
        //Assert
        assertEquals("ADDED", actual.getAppCode());
        verify(cGuardianMapper, times(1)).DTOToEn(guardianInfo);
        verify(userAdminService, times(1)).getLoginInfo(guardianInfo.getUserEmail());
        verify(userAdminService.getDataTranService(), times(1)).addGDNDetailsAndLogin(guardianInfoEn01, userLoginInfoEn);

    }

    @Test
    void test_addNewGuardianInfo_EX_DUP() {
        //Arrange
        when(userAdminService.getUserLoginRepo().checkUserEmail(guardianInfo.getUserEmail())).thenReturn(1);
        //Act
        SMException exception = assertThrows(SMException.class, () -> guardianService.addNewGuardianInfo(guardianInfo));
        //Assert
        assertEquals("DUP", exception.getExType());
    }

    @Test
    void test_addNewGuardianInfo_EX_UNKNOW() {
        //Arrange
        when(userAdminService.getUserLoginRepo().checkUserEmail(guardianInfo.getUserEmail())).thenReturn(0);
        when(cGuardianMapper.DTOToEn(guardianInfo)).thenReturn(guardianInfoEn01);
        when(userAdminService.getLoginInfo(guardianInfo.getUserEmail())).thenThrow(new RuntimeException());
        //Act
        SMException exception = assertThrows(SMException.class, () -> guardianService.addNewGuardianInfo(guardianInfo));
        //Assert
        assertEquals("UNKNOWN", exception.getExType());
    }

    @Test
    void test_updateGuardianInfo_P() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(guardianRepo.findByUserEmail("jane.doe@example.org")).thenReturn(guardianInfoEn01);
        when(cGuardianMapper.DTOToUpdateEn(guardianInfo, guardianInfoEn01)).thenReturn(guardianInfoEn01);
        //Act
        SMMessage actual = guardianService.updateGuardianInfo("jane.doe@example.org", guardianInfo);
        //Assert
        assertEquals("UPDATED", actual.getAppCode());
        verify(userAdminService.getDataTranService(), times(1)).addGDNDetailsAndLogin(guardianInfoEn01, userLoginInfoEn);
    }

    @Test
    void test_updateGuardianInfo_EX_DNF() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(null);
        //Act
        SMException exception = assertThrows(SMException.class, () -> guardianService.updateGuardianInfo("jane.doe@example.org", guardianInfo));
        //Assert
        assertEquals("DNF", exception.getExType());
        verify(guardianRepo, times(0)).findByUserEmail("jane.doe@example.org");
    }

    @Test
    void test_updateGuardianInfo_EX_UNKNOWN() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(guardianRepo.findByUserEmail("jane.doe@example.org")).thenReturn(guardianInfoEn01);
        when(cGuardianMapper.DTOToUpdateEn(guardianInfo, guardianInfoEn01)).thenThrow(new RuntimeException());
        //Act
        SMException exception = assertThrows(SMException.class, () -> guardianService.updateGuardianInfo("jane.doe@example.org", guardianInfo));
        //Assert
        assertEquals("UNKNOWN", exception.getExType());
        verify(userAdminService.getDataTranService(), times(0)).addGDNDetailsAndLogin(guardianInfoEn01, userLoginInfoEn);
    }

    @Test
    void test_deleteGuardianInfo_P() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(guardianRepo.findByUserEmail(("jane.doe@example.org"))).thenReturn(guardianInfoEn01);
        //Act
        SMMessage actual = guardianService.deleteGuardianInfo("jane.doe@example.org");
        //Assert
        assertEquals("DELETED", actual.getAppCode());
        verify(userAdminService.getDataTranService(), times(1)).deleteGDNDetailsAndLogin(guardianInfoEn01, userLoginInfoEn);
    }

    @Test
    void test_deleteGuardianInfo_EX_DNF() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(null);
        //Act
        SMException exception = assertThrows(SMException.class, () -> guardianService.deleteGuardianInfo("jane.doe@example.org"));
        //Assert
        assertEquals("DNF", exception.getExType());
        verify(userAdminService.getDataTranService(), times(0)).deleteGDNDetailsAndLogin(guardianInfoEn01, userLoginInfoEn);
    }

    @Test
    void test_deleteGuardianInfo_EX_UNKNOWN() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(guardianRepo.findByUserEmail(("jane.doe@example.org"))).thenThrow(new RuntimeException());
        //Act
        SMException exception = assertThrows(SMException.class, () -> guardianService.deleteGuardianInfo("jane.doe@example.org"));
        //Assert
        assertEquals("UNKNOWN", exception.getExType());
        verify(userAdminService.getDataTranService(), times(0)).deleteGDNDetailsAndLogin(guardianInfoEn01, userLoginInfoEn);
    }

    @Test
    void test_getGuardianInfoAll_P() {
        //Arrange
        List<GuardianInfoEn> actualEnAll = new ArrayList<GuardianInfoEn>();
        actualEnAll.add(guardianInfoEn01);
        actualEnAll.add(guardianInfoEn02);
        when(guardianRepo.findAll()).thenReturn(actualEnAll);
        //Act
        List<GuardianInfo> actual = guardianService.getGuardianInfoAll();
        //Assert
        assertEquals(2, actual.size());
    }

    @Test
    void test_getGuardianInfoByEmail_P() {
        //Arrange
        when(guardianRepo.findByUserEmail("jane.doe@example.org")).thenReturn(guardianInfoEn01);
        when(cGuardianMapper.EnToDTO(guardianInfoEn01)).thenReturn(guardianInfo);
        // Act
        GuardianInfo actual = guardianService.getGuardianInfoByEmail("jane.doe@example.org");
        // Assert
        assertEquals("jane.doe@example.org", actual.getUserEmail());
    }
}

