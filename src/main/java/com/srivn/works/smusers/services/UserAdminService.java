package com.srivn.works.smusers.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.srivn.works.smusers.db.dao.users.GuardianInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.UserInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.mappers.GuardianInfoMapper;
import com.srivn.works.smusers.db.repo.ClsnValRepo;
import com.srivn.works.smusers.db.repo.GuardianInfoRepo;
import com.srivn.works.smusers.db.repo.UserLoginInfoRepo;
import com.srivn.works.smusers.util.AppC;
import com.srivn.works.smusers.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAdminService {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminService.class);

	private final ClsnValRepo clsnValRepo;

	private final UserLoginInfoRepo userLoginInfoRepo;
	private final GuardianInfoRepo guardianRepo;

	private GuardianInfoMapper guiInfoMapper = Mappers.getMapper(GuardianInfoMapper.class);;

	public void addNewGuardianInfo(GuardianInfo gInfo) {
		GuardianInfoEn en = guiInfoMapper.DTOToEn(gInfo);
		UserLoginInfoEn ulEn = new UserLoginInfoEn(gInfo.getUserEmail(), AppUtil.generatePwd(),Timestamp.from(Instant.now()), AppC.Status.NEW.getCode());
		en.setUserLoginInfo(ulEn);
		guardianRepo.save(en);
	}
}
