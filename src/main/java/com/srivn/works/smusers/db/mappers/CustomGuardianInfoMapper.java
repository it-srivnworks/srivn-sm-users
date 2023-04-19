package com.srivn.works.smusers.db.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.srivn.works.smusers.db.dao.users.GuardianInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.repo.ClsnValRepo;
import com.srivn.works.smusers.db.repo.GuardianInfoRepo;
import com.srivn.works.smusers.db.repo.UserLoginInfoRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomGuardianInfoMapper {

	private final ClsnValRepo clsnValRepo;
	
	GuardianInfoMapper bookInfoMapper = Mappers.getMapper(GuardianInfoMapper.class);
	
	public GuardianInfo EnToDTO(GuardianInfoEn en) {
		GuardianInfo dto = bookInfoMapper.EnToDTO(en);
		return dto;
	}
	
	public GuardianInfoEn DTOToEn(GuardianInfo dto) {
		GuardianInfoEn en = bookInfoMapper.DTOToEn(dto);
		en.setUserType(clsnValRepo.findByValue(dto.getUserType()));
		return en;
	}
}
