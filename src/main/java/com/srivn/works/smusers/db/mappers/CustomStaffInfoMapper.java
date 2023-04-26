package com.srivn.works.smusers.db.mappers;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.srivn.works.smusers.db.dto.personal.AddressInfo;
import com.srivn.works.smusers.db.dto.users.StaffInfo;
import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.users.StaffInfoEn;
import com.srivn.works.smusers.db.repo.users.ClsnValRepo;
import com.srivn.works.smusers.util.AppC;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomStaffInfoMapper {

	private final ClsnValRepo clsnValRepo;
	
	
	public StaffInfo EnToDTO(StaffInfoEn en) {
		StaffInfo dto = StaffInfoMapper.INSTANCE.EnToDTO(en);
		dto.setUserDOB(en.getUserDOB().toString().substring(0, 10));
		dto.setUserType(en.getUserType().getValue());
		dto.setDept(en.getDept().getValue());
		return dto;
	}
	
	public StaffInfoEn DTOToEn(StaffInfo dto) {
		StaffInfoEn en = StaffInfoMapper.INSTANCE.DTOToEn(dto);
		en.setUserDOB(Timestamp.valueOf(dto.getUserDOB()+AppC.TS_DEF));
		en.setUserType(clsnValRepo.findByValue(dto.getUserType()));
		en.setDept(clsnValRepo.findByValue(dto.getUserType()));
		return en;
	}
	
	public StaffInfoEn DTOToUpdateEn(StaffInfo dto,StaffInfoEn en) {
		StaffInfoEn enU = StaffInfoMapper.INSTANCE.DTOToUpdateEn(dto,en);
		enU.setUserDOB(Timestamp.valueOf(dto.getUserDOB()+AppC.TS_DEF));
		enU.setUserType(clsnValRepo.findByValue(dto.getUserType()));
		en.setDept(clsnValRepo.findByValue(dto.getUserType()));
		return enU;
	}
	
	public AddressInfoEn DTOToEnAddress(AddressInfo dto) {
		return  StaffInfoMapper.INSTANCE.DTOToEnAddress(dto);
	}
	
}
