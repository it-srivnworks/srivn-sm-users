package com.srivn.works.smusers.services;

import com.srivn.works.smusers.db.dto.personal.AddressInfo;
import com.srivn.works.smusers.db.dto.personal.ContactInfo;
import com.srivn.works.smusers.db.dto.users.StaffInfo;
import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import com.srivn.works.smusers.db.entity.users.StaffInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.entity.util.ClsnEn;
import com.srivn.works.smusers.db.entity.util.ClsnValEn;
import com.srivn.works.smusers.db.mappers.CustomPersonalInfoMapper;
import com.srivn.works.smusers.db.mappers.CustomStaffInfoMapper;
import com.srivn.works.smusers.db.repo.personal.AddressInfoRepo;
import com.srivn.works.smusers.db.repo.personal.ContactInfoRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ContextConfiguration(classes = {PersonalInfoService.class})
@ActiveProfiles({"test"})
@ExtendWith(SpringExtension.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonalInfoServiceTest {
    @MockBean
    private AddressInfoRepo addressInfoRepo;
    @MockBean
    private ContactInfoRepo contactInfoRepo;
    @MockBean
    private CustomPersonalInfoMapper cPersonalInfoMapper;

    private PersonalInfoService piService;

    /*********************DATA*********************/
    AddressInfo addressInfo;
    ContactInfo contactInfo;

    ClsnValEn country;
    AddressInfoEn addressInfoEn;
    ContactInfoEn contactInfoEn;

    @BeforeEach
    void init() {
        addressInfo = new AddressInfo("42", "Street", "Oxford", "MD", "INDIA", "21654");
        contactInfo = new ContactInfo("123", "456");
        country = new ClsnValEn(100, "INDIA", new ClsnEn(1, "COUNTRY"));

        contactInfoEn = new ContactInfoEn(Long.valueOf(123), "123", "456");
        addressInfoEn = new AddressInfoEn("42", "Street", "Oxford", "MD", country, "21654");

        piService = new PersonalInfoService(addressInfoRepo,contactInfoRepo,cPersonalInfoMapper);
    }

    @Test
    void test_addAddressInfo_EXIST() {
        //Arrange
        when(addressInfoRepo.findIfAddressExist("42", "Street", "Oxford", "21654")).thenReturn(addressInfoEn);
        //Act
        AddressInfoEn actual = piService.addAddressInfo(addressInfo);
        //Assert
        assertEquals(addressInfoEn, actual);
        verify(addressInfoRepo, times(0)).save(addressInfoEn);
    }

    @Test
    void test_addAddressInfo_NOT_EXIST() {
        //Arrange
        when(addressInfoRepo.findIfAddressExist("42", "Street", "Oxford", "21654")).thenReturn(null);
        when(cPersonalInfoMapper.DTOToEnAddress(addressInfo)).thenReturn(addressInfoEn);
        //Act
        AddressInfoEn actual = piService.addAddressInfo(addressInfo);
        //Assert
        verify(addressInfoRepo, times(1)).save(addressInfoEn);
    }

    @Test
    void test_updateAddressInfo_EXIST() {
        //Arrange
        //Act
        AddressInfoEn actual = piService.updateAddressInfo(addressInfo, addressInfoEn);
        //Assert
        assertEquals(addressInfoEn, actual);
        verify(addressInfoRepo, times(0)).save(addressInfoEn);
    }

    @Test
    void test_updateAddressInfo_NOT_EXIST() {
        //Arrange
        addressInfo.setZipCode("111");
        when(cPersonalInfoMapper.DTOToEnAddress(addressInfo)).thenReturn(addressInfoEn);
        //Act
        AddressInfoEn actual = piService.updateAddressInfo(addressInfo, addressInfoEn);
        //Assert
        verify(addressInfoRepo, times(1)).save(addressInfoEn);
    }

    @Test
    void test_addContactInfo_EXIST() {
        //Arrange
        when(contactInfoRepo.findById(123L)).thenReturn(Optional.of(contactInfoEn));
        //Act
        ContactInfoEn actual = piService.addContactInfo(contactInfo);
        //Assert
        verify(contactInfoRepo, times(0)).save(contactInfoEn);
    }

    @Test
    void test_addContactInfo_NOT_EXIST() {
        //Arrange
        when(contactInfoRepo.findById(Long.valueOf(123))).thenReturn(Optional.empty());
        when(contactInfoRepo.save(contactInfoEn)).thenReturn(contactInfoEn);
        //Act
        ContactInfoEn actual = piService.addContactInfo(contactInfo);
        //Assert
        verify(contactInfoRepo, times(1)).save(contactInfoEn);
    }

    @Test
    void test_updateContactInfo_EXIST() {
        //Arrange
        //Act
        ContactInfoEn actual = piService.updateContactInfo(contactInfo, contactInfoEn);
        //Assert
        assertEquals(contactInfoEn, actual);
        verify(contactInfoRepo, times(0)).save(contactInfoEn);
    }

    @Test
    void test_updateContactInfo_NOT_EXIST() {
        //Arrange
        contactInfo.setPrimaryNo("111");
        when(cPersonalInfoMapper.DTOToEnContact(contactInfo)).thenReturn(contactInfoEn);
        //Act
        ContactInfoEn actual = piService.updateContactInfo(contactInfo, contactInfoEn);
        //Assert
        verify(contactInfoRepo, times(1)).save(contactInfoEn);
    }
}
