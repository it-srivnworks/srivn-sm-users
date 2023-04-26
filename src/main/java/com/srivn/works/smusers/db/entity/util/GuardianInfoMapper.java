package com.srivn.works.smusers.db.entity.util;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.srivn.works.smusers.db.dto.users.GuardianInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.util.AppC;

@Mapper
public interface GuardianInfoMapper {
	
	GuardianInfoMapper INSTANCE = Mappers.getMapper(GuardianInfoMapper.class);
	
	@Mapping(target = "userDOB", ignore = true)
	@Mapping(target="userType",  ignore = true)
	@Mapping(target="gender", ignore = true)
	public GuardianInfo EnToDTO(GuardianInfoEn en);
	
	@Mapping(target = "userDOB", ignore = true)
	@Mapping(target="userType",  ignore = true)
	@Mapping(target="gender", ignore = true)
	public GuardianInfoEn DTOToEn(GuardianInfo dto);
	
	
	@Mapping(target = "userDOB", ignore = true)
	@Mapping(target="userType",  ignore = true)
	@Mapping(target="gender", ignore = true)
	public GuardianInfoEn DTOToUpdateEn(GuardianInfo dto,@MappingTarget GuardianInfoEn en);
	
	
}
