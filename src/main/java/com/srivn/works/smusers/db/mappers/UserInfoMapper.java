package com.srivn.works.smusers.db.mappers;

import com.srivn.works.smusers.db.dto.personal.ContactInfo;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.srivn.works.smusers.db.dto.users.GuardianInfo;
import com.srivn.works.smusers.db.dto.users.UserInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.UserInfoEn;
import com.srivn.works.smusers.util.AppC;

@Mapper
public interface UserInfoMapper {

	UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

	public ContactInfoEn DTOToEnContact(ContactInfo dto);
}
