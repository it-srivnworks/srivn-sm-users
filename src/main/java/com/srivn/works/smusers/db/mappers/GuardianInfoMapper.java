package com.srivn.works.smusers.db.mappers;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.srivn.works.smusers.db.dao.users.GuardianInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.util.AppC;

@Mapper
public interface GuardianInfoMapper {
	
	GuardianInfoMapper INSTANCE = Mappers.getMapper(GuardianInfoMapper.class);
	
	@Mapping(target = "userDOB", ignore = true)
	@Mapping(target="userType",  ignore = true)
	@Mapping(target="gender", source="gender", qualifiedByName = "IntToStrGender")
	public GuardianInfo EnToDTO(GuardianInfoEn en);
	
	@Mapping(target = "userDOB", ignore = true)
	@Mapping(target="userType",  ignore = true)
	@Mapping(target="gender", source="gender", qualifiedByName = "StrToIntGender")
	public GuardianInfoEn DTOToEn(GuardianInfo dto);
	
	
	@Mapping(target = "userDOB", ignore = true)
	@Mapping(target="userType",  ignore = true)
	@Mapping(target="gender", source="gender", qualifiedByName = "StrToIntGender")
	public GuardianInfoEn DTOToUpdateEn(GuardianInfo dto,@MappingTarget GuardianInfoEn en);
	
	
	
	@Named("StrToIntGender")
    public static int StrToInt(String str) {
        if(StringUtils.isEmpty(str)){
            return 0;
        }else{
            return AppC.Gender.valueOf(str).getCode();
        }
    }
	
	@Named("IntToStrGender")
    public static String IntToStr(int i) {
			return AppC.Gender.getKey(i);
    }
}
