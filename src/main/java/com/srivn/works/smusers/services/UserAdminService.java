package com.srivn.works.smusers.services;

import java.sql.Timestamp;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.srivn.works.smusers.db.dto.personal.AddressInfo;
import com.srivn.works.smusers.db.dto.users.GuardianInfo;
import com.srivn.works.smusers.db.dto.users.StaffInfo;
import com.srivn.works.smusers.db.dto.users.UserInfo;
import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.StaffInfoEn;
import com.srivn.works.smusers.db.entity.users.UserInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.mappers.CustomGuardianInfoMapper;
import com.srivn.works.smusers.db.mappers.CustomStaffInfoMapper;
import com.srivn.works.smusers.db.repo.personal.AddressInfoRepo;
import com.srivn.works.smusers.db.repo.personal.ContactInfoRepo;
import com.srivn.works.smusers.db.repo.users.GuardianInfoRepo;
import com.srivn.works.smusers.db.repo.users.StaffInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserLoginInfoRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.util.AppC;
import com.srivn.works.smusers.util.AppMsg;
import com.srivn.works.smusers.util.AppUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Service
@RequiredArgsConstructor
public class UserAdminService {

	private static final Logger log = LoggerFactory.getLogger(UserAdminService.class);

	private final UserInfoRepo userRepo;

	private final UserLoginInfoRepo userLoginRepo;
	
	private final AddressInfoRepo addressRepo;
	
	private final ContactInfoRepo contactRepo;
	
	private final DataTransactionService dataTranService;

	/* Heavy query due to JPA JOIN on all user child tables */
	public List<UserInfo> getUserInfoAll() {
		List<UserInfo> uiList = userRepo.findAll().stream().map(en -> {
			UserInfo ui = new UserInfo();
			BeanUtils.copyProperties(en, ui);
			return ui;
		}).collect(Collectors.toList());
		return uiList;
	}

	public SMMessage checkIfEmailExist(String userEmail) {
		if (userLoginRepo.checkUserEmail(userEmail) > 0) {
			return SMMessage.builder().appCode(AppMsg.Msg.MSG_EXIST_002.getCode())
					.message(AppMsg.Msg.MSG_EXIST_002.getMsg()).build();
		} else {
			throw new SMException(AppMsg.Err.ERR__DNF_001.getCode(), AppMsg.Err.ERR__DNF_001.getMsgP("userEmail"));
		}
	}

	protected UserLoginInfoEn getLoginInfo(String userEmail) {
		UserLoginInfoEn ulEn = new UserLoginInfoEn(userEmail, AppUtil.generatePwd(), Timestamp.from(Instant.now()),
				AppC.Status.NEW.getCode());
		return ulEn;
	}
}
