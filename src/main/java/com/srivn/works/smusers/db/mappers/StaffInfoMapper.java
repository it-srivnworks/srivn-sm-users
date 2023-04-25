package com.srivn.works.smusers.db.mappers;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.srivn.works.smusers.db.dto.personal.AddressInfo;
import com.srivn.works.smusers.db.dto.users.StaffInfo;
import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.users.StaffInfoEn;
import com.srivn.works.smusers.util.AppC;

@Mapper
public interface StaffInfoMapper{

	StaffInfoMapper INSTANCE = Mappers.getMapper(StaffInfoMapper.class);
	
	@Mapping(target = "userDOB", ignore = true)
	@Mapping(target="userType",  ignore = true)
	@Mapping(target="gender", ignore = true)
	@Mapping(target="addressInfo.country",  ignore = true)
	@Mapping(target="dept", source="dept", ignore = true)
	@Mapping(target="title", ignore = true)
	public StaffInfo EnToDTO(StaffInfoEn en);
	
	@Mapping(target = "userDOB", ignore = true)
	@Mapping(target="userType",  ignore = true)
	@Mapping(target="gender", ignore = true)
	@Mapping(target="addressInfo.country",  ignore = true)
	@Mapping(target="dept", source="dept", ignore = true)
	@Mapping(target="title", ignore = true)
	public StaffInfoEn DTOToEn(StaffInfo dto);
	
	
	@Mapping(target = "userDOB", ignore = true)
	@Mapping(target="userType",  ignore = true)
	@Mapping(target="gender", ignore = true)
	@Mapping(target="addressInfo",  ignore = true)
	@Mapping(target="contactInfo",  ignore = true)
	@Mapping(target="dept", source="dept", ignore = true)
	@Mapping(target="title", ignore = true)
	public StaffInfoEn DTOToUpdateEn(StaffInfo dto,@MappingTarget StaffInfoEn en);
	
	@Mapping(target="country",  ignore = true)
	public AddressInfoEn DTOToEnAddress(AddressInfo dto);
}
