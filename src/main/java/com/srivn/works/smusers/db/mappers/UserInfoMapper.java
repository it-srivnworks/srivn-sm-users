package com.srivn.works.smusers.db.mappers;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.srivn.works.smusers.db.dao.users.GuardianInfo;
import com.srivn.works.smusers.db.dao.users.UserInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.UserInfoEn;
import com.srivn.works.smusers.util.AppC;

@Mapper
public interface UserInfoMapper {

	@Mapping(target = "userDOB", ignore = true)
	@Mapping(target="userType",  ignore = true)
	@Mapping(target="gender", source="gender", qualifiedByName = "IntToStrGender")
	public UserInfo EnToDTO(UserInfoEn en);
	
	@Named("IntToStrGender")
    public static String IntToStr(int i) {
			return AppC.Gender.getKey(i);
    }
}
