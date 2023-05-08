package com.srivn.works.smusers.db.mappers;

import com.srivn.works.smusers.db.dto.users.GuardianInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GuardianInfoMapper{
	
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
