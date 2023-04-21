package com.srivn.works.smusers.db.mappers;

import java.sql.Timestamp;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import com.srivn.works.smusers.db.dao.users.GuardianInfo;
import com.srivn.works.smusers.db.dao.users.UserInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.UserInfoEn;
import com.srivn.works.smusers.db.repo.ClsnValRepo;
import com.srivn.works.smusers.util.AppC;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomUserInfoMapper {

	private final ClsnValRepo clsnValRepo;
	
	UserInfoMapper userInfoMapper = Mappers.getMapper(UserInfoMapper.class);
	
	public UserInfo EnToDTO(UserInfoEn en) {
		UserInfo dto = userInfoMapper.EnToDTO(en);
		dto.setUserDOB(en.getUserDOB().toString().substring(0, 10));
		dto.setUserType(en.getUserType().getValue());
		return dto;
	}

}
