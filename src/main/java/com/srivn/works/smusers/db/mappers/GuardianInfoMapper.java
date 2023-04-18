package com.srivn.works.smusers.db.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.srivn.works.smusers.db.dao.users.GuardianInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;

@Mapper
public interface GuardianInfoMapper {

	@Mapping(target = "userType", ignore = true)
	public GuardianInfo EnToDTO(GuardianInfoEn en);
	
	@Mapping(target = "userType", ignore = true)
	@Mapping(target = "userDOB", ignore = true)
	public GuardianInfoEn DTOToEn(GuardianInfo dto);
}
