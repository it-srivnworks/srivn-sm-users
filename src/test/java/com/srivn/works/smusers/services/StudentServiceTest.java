package com.srivn.works.smusers.services;

import com.srivn.works.smusers.db.dto.personal.AddressInfo;
import com.srivn.works.smusers.db.dto.personal.ContactInfo;
import com.srivn.works.smusers.db.dto.users.StudentInfo;
import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import com.srivn.works.smusers.db.entity.personal.HealthInfoEn;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.StudentInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import com.srivn.works.smusers.db.mappers.CustomPersonalInfoMapper;
import com.srivn.works.smusers.db.mappers.CustomStudentInfoMapper;
import com.srivn.works.smusers.db.repo.personal.AddressInfoRepo;
import com.srivn.works.smusers.db.repo.personal.ContactInfoRepo;
import com.srivn.works.smusers.db.repo.users.StudentInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserLoginInfoRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserAdminService.class, PersonalInfoService.class, StudentService.class})
@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentServiceTest {

    /*********************Components*********************/
    @MockBean
    private UserInfoRepo userRepo;
    @MockBean
    private UserLoginInfoRepo userLoginRepo;
    @MockBean
    private AddressInfoRepo addressRepo;
    @MockBean
    private ContactInfoRepo contactRepo;
    @MockBean
    private DataTransactionService dataTranService;
    //TBI
    private UserAdminService userAdminService;
    //TBI
    private PersonalInfoService piService;
    //
    @MockBean
    private StudentInfoRepo studentRepo;
    @MockBean
    private CustomStudentInfoMapper cStudentMapper;
    @MockBean
    private CustomPersonalInfoMapper cPersonalInfoMapper;
    //
    private StudentService studentService;


    /*********************DATA*********************/
    //DTO
    ContactInfo contactInfo;
    AddressInfo addressInfo;
    StudentInfo studentInfo;
    //EN
    ClsnValEn gender;
    Timestamp userDOB;
    ClsnValEn userType;
    ContactInfoEn contactInfoEn, emergencyContactEn;
    ClsnValEn country;
    AddressInfoEn addressInfoEn;
    ClsnValEn bloodGroup;
    HealthInfoEn healthInfo;
    GuardianInfoEn pguardian, sguardian;
    UserLoginInfoEn userLoginInfoEn;
    StudentInfoEn studentInfoEn, studentInfoEn02;
    /*********************Others*********************/
    private static MockedStatic<UserLoginInfoEn> mockedSettings;
    @BeforeAll
    static void atStart() {
        mockedSettings = mockStatic(UserLoginInfoEn.class);
    }
    @AfterAll
    static void atEnd() {
        mockedSettings.close();
    }

    @BeforeEach
    void init() {
        //DTO
        contactInfo = new ContactInfo("123", "456");
        addressInfo = new AddressInfo("42", "Street", "Oxford", "MD", "INDIA", "21654");
        studentInfo = new StudentInfo("Jane", "Doe", "Gender", "User DOB", "User Type",
                "jane.doe@example.org", "primguardian.doe@example.org", "secguardian.doe@example.org");
        //EN
        gender = new ClsnValEn(100, "MALE", new ClsnEn(1, "GENDER"));
        userDOB = mock(Timestamp.class);
        userType = new ClsnValEn(100, "STUDENT", new ClsnEn(1, "USERTYPE"));
        contactInfoEn = new ContactInfoEn(1L, "123", "456");
        emergencyContactEn = new ContactInfoEn(1L, "111", "222");
        country = new ClsnValEn(100, "INDIA", new ClsnEn(1, "COUNTRY"));
        addressInfoEn = new AddressInfoEn("42", "Street", "Oxford", "MD", country, "21654");
        bloodGroup = new ClsnValEn(100, "B+", new ClsnEn(1, "BLOODGROUP"));
        healthInfo = new HealthInfoEn(bloodGroup, "Notes");
        pguardian = new GuardianInfoEn("primguardian", "Doe", gender, userDOB, userType, "primguardian.doe@example.org");
        sguardian = new GuardianInfoEn("secguardian", "Doe", gender, userDOB, userType, "secguardian.doe@example.org");
        studentInfoEn = new StudentInfoEn("Jane", "Doe", gender, userDOB, userType, "jane.doe@example.org", contactInfoEn, emergencyContactEn, addressInfoEn, healthInfo, pguardian, sguardian);
        studentInfoEn = new StudentInfoEn("Jenny", "Doe", gender, userDOB, userType, "jenny.doe@example.org", contactInfoEn, emergencyContactEn, addressInfoEn, healthInfo, pguardian, sguardian);
        userLoginInfoEn = new UserLoginInfoEn("jane.doe@example.org", "iloveyou", mock(Timestamp.class), 1);
        //Services
        piService = spy(new PersonalInfoService(addressRepo, contactRepo, cPersonalInfoMapper));
        userAdminService = spy(new UserAdminService(userRepo, userLoginRepo, addressRepo, contactRepo, dataTranService));
        studentService = spy(new StudentService(userAdminService, piService, studentRepo, cStudentMapper));
    }

    @Test
    void test_addNewStudentInfo_P01() {
        //Arrange
        when(userAdminService.getUserLoginRepo().checkUserEmail(studentInfo.getUserEmail())).thenReturn(0);
        when(cStudentMapper.DTOToEn(studentInfo)).thenReturn(studentInfoEn);
        when(UserLoginInfoEn.createNew(studentInfo.getUserEmail())).thenReturn(userLoginInfoEn);
        //  Act
        SMMessage actual = studentService.addNewStudentInfo(studentInfo);
        //Assert
        assertEquals("ADDED", actual.getAppCode());
        verify(piService, times(0)).addAddressInfo(studentInfo.getAddressInfo());
        verify(piService, times(0)).addContactInfo(studentInfo.getContactInfo());
        verify(userAdminService.getDataTranService(), times(1)).addSTUDetailsAndLogin(studentInfoEn, userLoginInfoEn);
    }

    @Test
    void test_addNewStudentInfo_P02() {
        //Arrange
        studentInfo.setAddressInfo(addressInfo);
        studentInfo.setContactInfo(contactInfo);
        when(userAdminService.getUserLoginRepo().checkUserEmail(studentInfo.getUserEmail())).thenReturn(0);
        when(cStudentMapper.DTOToEn(studentInfo)).thenReturn(studentInfoEn);
        when(UserLoginInfoEn.createNew(studentInfo.getUserEmail())).thenReturn(userLoginInfoEn);
        when(piService.addAddressInfo(studentInfo.getAddressInfo())).thenReturn(addressInfoEn);
        when(piService.addContactInfo(studentInfo.getContactInfo())).thenReturn(contactInfoEn);
        //  Act
        SMMessage actual = studentService.addNewStudentInfo(studentInfo);
        //Assert
        assertEquals("ADDED", actual.getAppCode());
        verify(piService, times(1)).addAddressInfo(studentInfo.getAddressInfo());
        verify(piService, times(1)).addContactInfo(studentInfo.getContactInfo());
        verify(userAdminService.getDataTranService(), times(1)).addSTUDetailsAndLogin(studentInfoEn, userLoginInfoEn);
        verify(userAdminService.getDataTranService(), times(1)).addSTUDetailsAndLogin(studentInfoEn, userLoginInfoEn);
    }

    @Test
    void test_addNewStudentInfo_EX_DUP() {
        //Arrange
        when(userAdminService.getUserLoginRepo().checkUserEmail(studentInfo.getUserEmail())).thenReturn(1);
        // Act
        SMException exception = assertThrows(SMException.class, () -> studentService.addNewStudentInfo(studentInfo));
        //Assert
        assertEquals("DUP", exception.getExType());
    }

    @Test
    void test_addNewStudentInfo_EX_UNKNOWN() {
        //Arrange
        studentInfo.setAddressInfo(addressInfo);
        studentInfo.setContactInfo(contactInfo);
        when(userAdminService.getUserLoginRepo().checkUserEmail(studentInfo.getUserEmail())).thenReturn(0);
        when(cStudentMapper.DTOToEn(studentInfo)).thenReturn(studentInfoEn);
        when(UserLoginInfoEn.createNew(studentInfo.getUserEmail())).thenReturn(userLoginInfoEn);
        when(piService.addAddressInfo(studentInfo.getAddressInfo())).thenThrow(new RuntimeException());
        // Act
        SMException exception = assertThrows(SMException.class, () -> studentService.addNewStudentInfo(studentInfo));
        //Assert
        assertEquals("UNKNOWN", exception.getExType());
    }

    @Test
    void test_updateStudentInfo_P() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(studentRepo.findByUserEmail("jane.doe@example.org")).thenReturn(studentInfoEn);
        when(cStudentMapper.DTOToUpdateEn(studentInfo, studentInfoEn)).thenReturn(studentInfoEn);
        //  Act
        SMMessage actual = studentService.updateStudentInfo("jane.doe@example.org", studentInfo);
        //Assert
        assertEquals("UPDATED", actual.getAppCode());
    }

    @Test
    void test_updateStudentInfo_withAddrNContact_P() {
        //Arrange
        studentInfo.setAddressInfo(addressInfo);
        studentInfo.setContactInfo(contactInfo);
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(studentRepo.findByUserEmail("jane.doe@example.org")).thenReturn(studentInfoEn);
        when(cStudentMapper.DTOToUpdateEn(studentInfo, studentInfoEn)).thenReturn(studentInfoEn);
        //  Act
        SMMessage actual = studentService.updateStudentInfo("jane.doe@example.org", studentInfo);
        //Assert
        assertEquals("UPDATED", actual.getAppCode());
        verify(piService, times(1)).updateAddressInfo(studentInfo.getAddressInfo(), studentInfoEn.getAddressInfo());
        verify(piService, times(1)).updateContactInfo(studentInfo.getContactInfo(), studentInfoEn.getContactInfo());
    }

    @Test
    void test_updateStudentInfo_EX_DNF() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(null);
        // Act
        SMException exception = assertThrows(SMException.class, () -> studentService.updateStudentInfo("jane.doe@example.org", studentInfo));
        //Assert
        assertEquals("DNF", exception.getExType());
    }

    @Test
    void test_updateStudentInfo_EX_UNKNOWN() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(studentRepo.findByUserEmail("jane.doe@example.org")).thenReturn(studentInfoEn);
        when(cStudentMapper.DTOToUpdateEn(studentInfo, studentInfoEn)).thenThrow(new RuntimeException());
        // Act
        SMException exception = assertThrows(SMException.class, () -> studentService.updateStudentInfo("jane.doe@example.org", studentInfo));
        //Assert
        assertEquals("UNKNOWN", exception.getExType());
    }

    @Test
    void test_deleteStudentInfo_P() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(studentRepo.findByUserEmail("jane.doe@example.org")).thenReturn(studentInfoEn);
        //Act
        SMMessage actual = studentService.deleteStudentInfo("jane.doe@example.org");
        //Assert
        assertEquals("DELETED", actual.getAppCode());
    }

    @Test
    void test_deleteStudentInfo_EX_DNF() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(null);
        //Act
        SMException exception = assertThrows(SMException.class, () -> studentService.deleteStudentInfo("jane.doe@example.org"));
        //Assert
        assertEquals("DNF", exception.getExType());
    }

    @Test
    void test_deleteStudentInfo_EX_UNKNOWN() {
        //Arrange
        when(userAdminService.getUserLoginRepo().findByUserEmail("jane.doe@example.org")).thenReturn(userLoginInfoEn);
        when(studentRepo.findByUserEmail("jane.doe@example.org")).thenReturn(studentInfoEn);
        when(userAdminService.getDataTranService()).thenReturn(null);
        //Act
        SMException exception = assertThrows(SMException.class, () -> studentService.deleteStudentInfo("jane.doe@example.org"));
        //Assert
        assertEquals("UNKNOWN", exception.getExType());
    }

    @Test
    void test_getStudentInfoAll_P() {
        //Arrange
        List<StudentInfoEn> actualStudentInfoEnAll = new ArrayList<StudentInfoEn>();
        actualStudentInfoEnAll.add(studentInfoEn);
        actualStudentInfoEnAll.add(studentInfoEn02);
        when(studentRepo.findAll()).thenReturn(actualStudentInfoEnAll);
        // Act
        List<StudentInfo> actual = studentService.getStudentInfoAll();
        // Assert
        assertEquals(2, actual.size());
    }

    @Test
    void test_getStudentInfoAll_EX() {
        //Arrange
        List<StudentInfoEn> actualStudentInfoEnAll = new ArrayList<StudentInfoEn>();
        when(studentRepo.findAll()).thenReturn(actualStudentInfoEnAll);
        // Act
        SMException exception = assertThrows(SMException.class, () -> studentService.getStudentInfoAll());
        // Assert
        assertEquals("DNF", exception.getExType());
    }

    @Test
    void test_getStudentInfoByEmail_P() {
        //Arrange
        when(studentRepo.findByUserEmail("jane.doe@example.org")).thenReturn(studentInfoEn);
        when(cStudentMapper.EnToDTO(studentInfoEn)).thenReturn(studentInfo);
        // Act
        StudentInfo actual = studentService.getStudentInfoByEmail("jane.doe@example.org");
        // Assert
        assertEquals("jane.doe@example.org", actual.getUserEmail());
    }

    @Test
    void test_getStudentInfoByEmail_EX() {
        //Arrange
        when(studentRepo.findByUserEmail("jane.doe@example.org")).thenReturn(null);
        // Act
        SMException exception = assertThrows(SMException.class, () -> studentService.getStudentInfoByEmail("jane.doe@example.org"));
        // Assert
        assertEquals("DNF", exception.getExType());
    }
}

