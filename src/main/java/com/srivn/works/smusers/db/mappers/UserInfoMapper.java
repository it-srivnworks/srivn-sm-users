package com.srivn.works.smusers.db.mappers;

import com.srivn.works.smusers.db.dto.personal.ContactInfo;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserInfoMapper {

	UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

	public ContactInfoEn DTOToEnContact(ContactInfo dto);
}
