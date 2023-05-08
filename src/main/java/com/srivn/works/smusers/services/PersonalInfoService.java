package com.srivn.works.smusers.services;

import com.srivn.works.smusers.db.dto.personal.AddressInfo;
import com.srivn.works.smusers.db.dto.personal.ContactInfo;
import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import com.srivn.works.smusers.db.mappers.CustomPersonalInfoMapper;
import com.srivn.works.smusers.db.mappers.PersonalInfoMapper;
import com.srivn.works.smusers.db.repo.personal.AddressInfoRepo;
import com.srivn.works.smusers.db.repo.personal.ContactInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalInfoService {

    private final AddressInfoRepo addressRepo;

    private final ContactInfoRepo contactRepo;

    private final CustomPersonalInfoMapper cPersonalInfoMapper;

    AddressInfoEn addAddressInfo(AddressInfo ai) {
        AddressInfoEn aiD = addressRepo.findIfAddressExist(ai.getHouseNumber(), ai.getStreet(),
                ai.getCity(), ai.getZipCode());
        if (aiD != null) {
            return aiD;
        } else {
            return addressRepo.save(cPersonalInfoMapper.DTOToEnAddress(ai));
        }

    }

    AddressInfoEn updateAddressInfo(AddressInfo ai, AddressInfoEn aiE) {
        if (Objects.equals(aiE.getZipCode(), ai.getZipCode())
                && Objects.equals(aiE.getHouseNumber(), ai.getHouseNumber())
                && Objects.equals(aiE.getStreet(), ai.getStreet()) && Objects.equals(aiE.getCity(), ai.getCity())) {
            return aiE;
        } else {
            return addressRepo.save(cPersonalInfoMapper.DTOToEnAddress(ai));
        }

    }

    ContactInfoEn addContactInfo(ContactInfo ci) {
        long iD = Long.parseLong(ci.getPrimaryNo().replace("+", "").replace("-", "").trim());
        Optional<ContactInfoEn> ciN = contactRepo.findById(iD);
        if (ciN.isPresent()) {
            return ciN.get();
        } else {
            ContactInfoEn ciEn = new ContactInfoEn(iD,ci.getPrimaryNo(),ci.getSecondaryNo());
            return contactRepo.save(ciEn);
        }
    }

    ContactInfoEn updateContactInfo(ContactInfo ci, ContactInfoEn ciE) {
        if (Objects.equals(ci.getPrimaryNo(), ciE.getPrimaryNo())) {
            return ciE;
        } else {
            return contactRepo.save(cPersonalInfoMapper.DTOToEnContact(ci));
        }

    }
}
