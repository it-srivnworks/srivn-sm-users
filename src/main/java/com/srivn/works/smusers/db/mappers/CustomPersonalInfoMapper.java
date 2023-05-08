package com.srivn.works.smusers.db.mappers;

import com.srivn.works.smusers.db.dto.personal.AddressInfo;
import com.srivn.works.smusers.db.dto.personal.ContactInfo;
import com.srivn.works.smusers.db.dto.users.StaffInfo;
import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import com.srivn.works.smusers.db.entity.users.StaffInfoEn;
import com.srivn.works.smusers.db.repo.users.ClsnValRepo;
import com.srivn.works.smusers.util.AppC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class CustomPersonalInfoMapper {

	private final ClsnValRepo clsnValRepo;
	
	
	public AddressInfoEn DTOToEnAddress(AddressInfo dto) {
		AddressInfoEn en = PersonalInfoMapper.INSTANCE.DTOToEnAddress(dto);
		en.setCountry(clsnValRepo.findByValue(dto.getCountry()));
		return en;
	}

	public ContactInfoEn DTOToEnContact(ContactInfo dto) {
		ContactInfoEn en = PersonalInfoMapper.INSTANCE.DTOToEnContact(dto);
		return en;
	}
}
