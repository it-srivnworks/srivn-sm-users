package com.srivn.works.smusers.services;

import com.srivn.works.smusers.db.dto.personal.AddressInfo;
import com.srivn.works.smusers.db.dto.personal.ContactInfo;
import com.srivn.works.smusers.db.dto.users.StaffInfo;
import com.srivn.works.smusers.db.dto.users.UserInfo;
import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import com.srivn.works.smusers.db.entity.users.StaffInfoEn;
import com.srivn.works.smusers.db.entity.users.UserInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import com.srivn.works.smusers.db.mappers.CustomStaffInfoMapper;
import com.srivn.works.smusers.db.repo.personal.AddressInfoRepo;
import com.srivn.works.smusers.db.repo.personal.ContactInfoRepo;
import com.srivn.works.smusers.db.repo.users.StaffInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserLoginInfoRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserAdminService.class, StaffService.class})
@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StaffServiceTest {
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
    private StaffInfoRepo staffRepo;
    @MockBean
    private CustomStaffInfoMapper cStaffMapper;

    private StaffService staffService;

    /*********************DATA*********************/
    AddressInfo addressInfo;
    ContactInfo contactInfo;
    StaffInfo staffInfo;
    ClsnValEn gender;
    Timestamp userDOB;
    ClsnValEn userType;
    ClsnValEn country;
    ClsnValEn dept;
    ClsnValEn title;
    AddressInfoEn addressInfoEn;
    ContactInfoEn contactInfoEn;
    StaffInfoEn staffInfoEn, staffInfoEn02;
    UserLoginInfoEn userLoginInfoEn;

    @BeforeEach
    void init() {
        addressInfo = new AddressInfo("42", "Street", "Oxford", "MD", "INDIA", "21654");
        contactInfo = new ContactInfo("123", "456");
        staffInfo = new StaffInfo("Jane", "Doe", "MALE", "1990-01-01", "STAFF", "jane.doe@example.org", "ELEC", "MR");

        gender = new ClsnValEn(100, "MALE", new ClsnEn(1, "GENDER"));
        userDOB = mock(Timestamp.class);
        userType = new ClsnValEn(100, "STAFF", new ClsnEn(1, "USERTYPE"));
        contactInfoEn = new ContactInfoEn(1L, "123", "456");
        addressInfoEn = new AddressInfoEn("42", "Street", "Oxford", "MD", country, "21654");
        country = new ClsnValEn(100, "INDIA", new ClsnEn(1, "COUNTRY"));
        dept = new ClsnValEn(100, "ELEC", new ClsnEn(1, "DEPT"));
        title = new ClsnValEn(100, "MR", new ClsnEn(1, "TITLE"));

        staffInfoEn = new StaffInfoEn("Jane", "Doe", gender, userDOB, userType, "jane.doe@example.org", contactInfoEn, addressInfoEn, dept, title);
        staffInfoEn02 = new StaffInfoEn("Jenny", "Doe", gender, userDOB, userType, "jenny.doe@example.org", contactInfoEn, addressInfoEn, dept, title);
        staffInfo = new StaffInfo("Jane", "Doe", "MALE", "1990-01-01", "STAFF", "jane.doe@example.org", "ELEC", "MR");
        userLoginInfoEn = new UserLoginInfoEn("jane.doe@example.org", "iloveyou", mock(Timestamp.class), 1);

        userAdminService = spy(new UserAdminService(userInfoRepo, userLoginInfoRepo, addressInfoRepo, contactInfoRepo, dataTransactionService));
        staffService = spy(new StaffService(userAdminService, staffRepo, cStaffMapper));
    }

    @Test
    void test_addNewStaffInfo_P() {
        //Arrange
        when(cStaffMapper.DTOToEn(staffInfo)).thenReturn(staffInfoEn);
        when(userAdminService.getLoginInfo(staffInfo.getUserEmail())).thenReturn(userLoginInfoEn);
        //  Act
        SMMessage actual = staffService.addNewStaffInfo(staffInfo);
        //Assert
        assertEquals("ADDED", actual.getAppCode());
        verify(cStaffMapper).DTOToEn(staffInfo);
        verify(userAdminService).getLoginInfo(staffInfo.getUserEmail());
        verify(userAdminService.getDataTranService()).addSTFDetailsAndLogin(staffInfoEn, userLoginInfoEn);
    }

    @Test
    void test_addNewStaffInfo_EX_DUP() {
        //Arrange
        when(userAdminService.getUserLoginRepo().checkUserEmail(staffInfo.getUserEmail())).thenReturn(1);
        // Act
        SMException exception = assertThrows(SMException.class, () -> staffService.addNewStaffInfo(staffInfo));
        //Assert
        assertEquals("DUP", exception.getExType());
    }

    @Test
    void test_addNewStaffInfo_EX_UNKNOWN() {
        //Arrange
        when(userAdminService.getUserLoginRepo().checkUserEmail(staffInfo.getUserEmail())).thenReturn(0);
        when(cStaffMapper.DTOToEn(staffInfo)).thenReturn(staffInfoEn);
        when(userAdminService.getLoginInfo(staffInfo.getUserEmail())).thenThrow(new RuntimeException());
        // Act
        SMException exception = assertThrows(SMException.class, () -> staffService.addNewStaffInfo(staffInfo));
        //Assert
        assertEquals("UNKNOWN", exception.getExType());
    }

    @Test
    void test_updateStaffInfo_P() {
        //Arrange

        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(staffRepo.findByUserEmail("jane.doe@example.org")).thenReturn(staffInfoEn);
        when(cStaffMapper.DTOToUpdateEn(staffInfo, staffInfoEn)).thenReturn(staffInfoEn);
        //  Act
        SMMessage actual = staffService.updateStaffInfo("jane.doe@example.org", staffInfo);
        //Assert
        assertEquals("UPDATED", actual.getAppCode());
    }

    @Test
    void test_updateStaffInfo_withAddrNContact_P() {
        //Arrange
        staffInfo.setAddressInfo(addressInfo);
        staffInfo.setContactInfo(contactInfo);
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(staffRepo.findByUserEmail("jane.doe@example.org")).thenReturn(staffInfoEn);
        when(cStaffMapper.DTOToUpdateEn(staffInfo, staffInfoEn)).thenReturn(staffInfoEn);
        //  Act
        SMMessage actual = staffService.updateStaffInfo("jane.doe@example.org", staffInfo);
        //Assert
        assertEquals("UPDATED", actual.getAppCode());
        verify(staffService, times(1)).updateAddressInfo(staffInfo.getAddressInfo(), staffInfoEn.getAddressInfo());
        verify(staffService, times(1)).updateContactInfo(staffInfo.getContactInfo(), staffInfoEn.getContactInfo());
    }

    @Test
    void test_updateStaffInfo_EX_DNF() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(null);
        // Act
        SMException exception = assertThrows(SMException.class, () -> staffService.updateStaffInfo("jane.doe@example.org", staffInfo));
        //Assert
        assertEquals("DNF", exception.getExType());
    }

    @Test
    void test_updateStaffInfo_EX_UNKNOWN() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(staffRepo.findByUserEmail("jane.doe@example.org")).thenReturn(staffInfoEn);
        when(cStaffMapper.DTOToUpdateEn(staffInfo, staffInfoEn)).thenThrow(new RuntimeException());
        // Act
        SMException exception = assertThrows(SMException.class, () -> staffService.updateStaffInfo("jane.doe@example.org", staffInfo));
        //Assert
        assertEquals("UNKNOWN", exception.getExType());
    }

    @Test
    void test_deleteStaffInfo_P() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(staffRepo.findByUserEmail("jane.doe@example.org")).thenReturn(staffInfoEn);
        //Act
        SMMessage actual = staffService.deleteStaffInfo("jane.doe@example.org");
        //Assert
        assertEquals("DELETED", actual.getAppCode());
    }

    @Test
    void test_deleteStaffInfo_EX_DNF() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(null);
        //Act
        SMException exception = assertThrows(SMException.class, () -> staffService.deleteStaffInfo("jane.doe@example.org"));
        //Assert
        assertEquals("DNF", exception.getExType());
    }

    @Test
    void test_deleteStaffInfo_EX_UNKNOWN() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(staffRepo.findByUserEmail("jane.doe@example.org")).thenReturn(staffInfoEn);
        when(userAdminService.getDataTranService()).thenReturn(null);
        //Act
        SMException exception = assertThrows(SMException.class, () -> staffService.deleteStaffInfo("jane.doe@example.org"));
        //Assert
        assertEquals("UNKNOWN", exception.getExType());
    }

    @Test
    void test_addAddressInfo_P() {
        //Arrange
        when(userAdminService.getAddressRepo().findIfAddressExist("42", "Street", "Oxford", "21654")).thenReturn(addressInfoEn);
        //Act
        AddressInfoEn actual = staffService.addAddressInfo(addressInfoEn);
        //Assert
        assertEquals(addressInfoEn, actual);
        verify(userAdminService.getAddressRepo(), times(0)).save(addressInfoEn);
    }

    @Test
    void test_addAddressInfo_N() {
        //Arrange
        when(userAdminService.getAddressRepo().findIfAddressExist("42", "Street", "Oxford", "21654")).thenReturn(null);
        //Act
        AddressInfoEn actual = staffService.addAddressInfo(addressInfoEn);
        //Assert
        verify(userAdminService.getAddressRepo(), times(1)).save(addressInfoEn);
    }

    @Test
    void test_updateAddressInfo_P() {
        //Arrange
        //Act
        AddressInfoEn actual = staffService.updateAddressInfo(addressInfo, addressInfoEn);
        //Assert
        assertEquals(addressInfoEn, actual);
        verify(userAdminService.getAddressRepo(), times(0)).save(addressInfoEn);
    }

    @Test
    void test_updateAddressInfo_N() {
        //Arrange
        addressInfo.setZipCode("111");
        when(cStaffMapper.DTOToEnAddress(addressInfo)).thenReturn(addressInfoEn);
        //Act
        AddressInfoEn actual = staffService.updateAddressInfo(addressInfo, addressInfoEn);
        //Assert
        verify(userAdminService.getAddressRepo(), times(1)).save(addressInfoEn);
    }

    @Test
    void test_addContactInfo_P() {
        //Arrange
        when(userAdminService.getContactRepo().findById(123L)).thenReturn(Optional.of(contactInfoEn));
        //Act
        ContactInfoEn actual = staffService.addContactInfo(contactInfoEn);
        //Assert
        verify(userAdminService.getContactRepo(), times(0)).save(contactInfoEn);
    }

    @Test
    void test_addContactInfo_N() {
        //Arrange
        when(userAdminService.getContactRepo().findById(1L)).thenReturn(null);
        //Act
        ContactInfoEn actual = staffService.addContactInfo(contactInfoEn);
        //Assert
        verify(userAdminService.getContactRepo(), times(1)).save(contactInfoEn);
    }

    @Test
    void test_updateContactInfo_P() {
        //Arrange
        //Act
        ContactInfoEn actual = staffService.updateContactInfo(contactInfo, contactInfoEn);
        //Assert
        assertEquals(contactInfoEn, actual);
        verify(userAdminService.getContactRepo(), times(0)).save(contactInfoEn);
    }

    @Test
    void test_updateContactInfo_N() {
        //Arrange
        contactInfo.setPrimaryNo("111");
        when(cStaffMapper.DTOToEnContact(contactInfo)).thenReturn(contactInfoEn);
        //Act
        ContactInfoEn actual = staffService.updateContactInfo(contactInfo, contactInfoEn);
        //Assert
        verify(userAdminService.getContactRepo(), times(1)).save(contactInfoEn);
    }

    @Test
    void test_getStaffInfoAll_P() {
        //Arrange
        List<StaffInfoEn> actualStaffInfoEnAll = new ArrayList<StaffInfoEn>();
        actualStaffInfoEnAll.add(staffInfoEn);
        actualStaffInfoEnAll.add(staffInfoEn02);
        when(staffRepo.findAll()).thenReturn(actualStaffInfoEnAll);
        // Act
        List<StaffInfo> actual = staffService.getStaffInfoAll();
        // Assert
        assertEquals(2, actual.size());
    }

    @Test
    void test_getStaffInfoByEmail_P() {
        //Arrange
        when(staffRepo.findByUserEmail("jane.doe@example.org")).thenReturn(staffInfoEn);
        when(cStaffMapper.EnToDTO(staffInfoEn)).thenReturn(staffInfo);
         // Act
        StaffInfo actual = staffService.getStaffInfoByEmail("jane.doe@example.org");
        // Assert
        assertEquals("jane.doe@example.org", actual.getUserEmail());
    }
}

