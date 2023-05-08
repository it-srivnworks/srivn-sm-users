package com.srivn.works.smusers.db.mappers;

import com.srivn.works.smusers.db.dto.personal.AddressInfo;
import com.srivn.works.smusers.db.dto.personal.ContactInfo;
import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonalInfoMapper {

    PersonalInfoMapper INSTANCE = Mappers.getMapper(PersonalInfoMapper.class);

    @Mapping(target="country",  ignore = true)
    public AddressInfoEn DTOToEnAddress(AddressInfo dto);

    public ContactInfoEn DTOToEnContact(ContactInfo dto);

}
