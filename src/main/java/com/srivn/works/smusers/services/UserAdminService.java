package com.srivn.works.smusers.services;

import java.sql.Timestamp;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.srivn.works.smusers.db.dao.users.GuardianInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.mappers.CustomGuardianInfoMapper;
import com.srivn.works.smusers.db.repo.ClsnValRepo;
import com.srivn.works.smusers.db.repo.GuardianInfoRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.util.AppC;
import com.srivn.works.smusers.util.AppMsg;
import com.srivn.works.smusers.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAdminService {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminService.class);

	private final GuardianInfoRepo guardianRepo;

	private final ClsnValRepo clsnValRepo;

	private final CustomGuardianInfoMapper cGuiInfoMapper;

	public SMMessage addNewGuardianInfo(GuardianInfo gInfo) {
		try {
			GuardianInfoEn en = cGuiInfoMapper.DTOToEn(gInfo);
			UserLoginInfoEn ulEn = new UserLoginInfoEn(gInfo.getUserEmail(), AppUtil.generatePwd(),
					Timestamp.from(Instant.now()), AppC.Status.NEW.getCode());
			en.setUserLoginInfo(ulEn);
			guardianRepo.save(en);
			return SMMessage.builder().code(AppMsg.Msg.MSG_ADD_001.getCode()).message(AppMsg.Msg.MSG_ADD_001.getMsg())
					.build();
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgWithParam());

		}
	}
}
