package com.srivn.works.smusers.services;

import java.sql.Timestamp;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.srivn.works.smusers.db.dao.users.GuardianInfo;
import com.srivn.works.smusers.db.dao.users.UserInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.UserInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.mappers.CustomGuardianInfoMapper;
import com.srivn.works.smusers.db.repo.GuardianInfoRepo;
import com.srivn.works.smusers.db.repo.UserInfoRepo;
import com.srivn.works.smusers.db.repo.UserLoginInfoRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.util.AppC;
import com.srivn.works.smusers.util.AppMsg;
import com.srivn.works.smusers.util.AppUtil;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAdminService {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminService.class);

	private final GuardianInfoRepo grRepo;

	private final UserLoginInfoRepo ulRepo;

	private final CustomGuardianInfoMapper cGuiInfoMapper;

	public SMMessage addNewGuardianInfo(GuardianInfo gInfo) {
		try {
			if (ulRepo.checkUserEmail(gInfo.getUserEmail()) == 0) {
				GuardianInfoEn en = cGuiInfoMapper.DTOToEn(gInfo);
				UserLoginInfoEn ulEn = new UserLoginInfoEn(gInfo.getUserEmail(), AppUtil.generatePwd(),
						Timestamp.from(Instant.now()), AppC.Status.NEW.getCode());
				en.setUserLoginInfo(ulEn);
				grRepo.save(en);
				return SMMessage.builder().appCode(AppMsg.Msg.MSG_ADD_001.getCode())
						.message(AppMsg.Msg.MSG_ADD_001.getMsg()).build();
			} else {
				throw new SMException(AppMsg.Err.ERR_DUP_002.getCode(),
						AppMsg.Err.ERR_DUP_002.getMsgWithParam("userEmail"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgWithParam());

		}
	}

	public List<GuardianInfo> getGuardianInfoAll() {
		List<GuardianInfo> uiList = grRepo.findAll().stream().map(en -> {
			return cGuiInfoMapper.EnToDTO(en);
		}).collect(Collectors.toList());
		return uiList;

	}

	public GuardianInfo getGuardianInfobyEmail(String userEmail) {
		return cGuiInfoMapper.EnToDTO(grRepo.getUsergetByUserEmail(userEmail));
	}
	
	public SMMessage checkIfEmailExist(String userEmail) {
		if (ulRepo.checkUserEmail(userEmail) > 0) {
			return SMMessage.builder().appCode(AppMsg.Msg.MSG_EXIST_002.getCode())
					.message(AppMsg.Msg.MSG_EXIST_002.getMsg()).build();
		} else {
			throw new SMException(AppMsg.Err.ERR__DNF_001.getCode(),
					AppMsg.Err.ERR__DNF_001.getMsgWithParam("userEmail"));
		}
	}
}
