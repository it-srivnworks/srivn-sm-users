
package com.srivn.works.smusers.db.mappers;

import com.srivn.works.smusers.db.dto.users.StudentInfo;
import com.srivn.works.smusers.db.entity.users.StudentInfoEn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentInfoMapper {

    StudentInfoMapper INSTANCE = Mappers.getMapper(StudentInfoMapper.class);

    @Mapping(target = "userDOB", ignore = true)
    @Mapping(target="userType",  ignore = true)
    @Mapping(target="gender", ignore = true)
    @Mapping(target="addressInfo.country",  ignore = true)
    @Mapping(target="healthInfo.bloodGroup",  ignore = true)
    @Mapping(target="pguardian",  ignore = true)
    @Mapping(target="sguardian",  ignore = true)
    public StudentInfo EnToDTO(StudentInfoEn en);

    @Mapping(target = "userDOB", ignore = true)
    @Mapping(target="userType",  ignore = true)
    @Mapping(target="gender", ignore = true)
    @Mapping(target="addressInfo.country",  ignore = true)
    @Mapping(target="healthInfo.bloodGroup",  ignore = true)
    @Mapping(target="pguardian",  ignore = true)
    @Mapping(target="sguardian",  ignore = true)
    public StudentInfoEn DTOToEn(StudentInfo dto);

    @Mapping(target = "userDOB", ignore = true)
    @Mapping(target="userType",  ignore = true)
    @Mapping(target="gender", ignore = true)
    @Mapping(target="addressInfo.country",  ignore = true)
    @Mapping(target="healthInfo.bloodGroup",  ignore = true)
    @Mapping(target="pguardian",  ignore = true)
    @Mapping(target="sguardian",  ignore = true)
    public StudentInfoEn DTOToUpdateEn(StudentInfo dto, @MappingTarget StudentInfoEn en);
}
