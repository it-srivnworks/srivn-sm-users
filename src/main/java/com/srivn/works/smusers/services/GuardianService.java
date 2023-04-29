package com.srivn.works.smusers.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.srivn.works.smusers.db.dto.users.GuardianInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.mappers.CustomGuardianInfoMapper;
import com.srivn.works.smusers.db.repo.users.GuardianInfoRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.util.AppMsg;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuardianService {

	private static final Logger log = LoggerFactory.getLogger(GuardianService.class);

	private final UserAdminService userAdminService;
	private final GuardianInfoRepo guardianRepo;
	private final CustomGuardianInfoMapper cGuardianMapper;

	/*
	 * GUARDIAN INFO
	 */
	public SMMessage addNewGuardianInfo(GuardianInfo gInfo) {
		try {
			if (userAdminService.getUserLoginRepo().checkUserEmail(gInfo.getUserEmail()) == 0) {
				GuardianInfoEn en = cGuardianMapper.DTOToEn(gInfo);
				UserLoginInfoEn ulEn = userAdminService.getLoginInfo(gInfo.getUserEmail());
				userAdminService.getDataTranService().addGDNDetailsAndLogin(en, ulEn);
				return SMMessage.builder().appCode(AppMsg.Msg.MSG_ADD_001.getCode())
						.message(AppMsg.Msg.MSG_ADD_001.getMsgP()).build();
			} else {
				throw new SMException(AppMsg.Err.ERR_DUP_002.getCode(), AppMsg.Err.ERR_DUP_002.getMsgP("userEmail"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP());

		}
	}

	public SMMessage updateGuardianInfo(String userEmail, GuardianInfo gInfo) {
		try {
			UserLoginInfoEn ulEn = userAdminService.getUserLoginRepo().findByUserEmail(userEmail);
			if (ulEn != null) {
				GuardianInfoEn en = guardianRepo.findByUserEmail(userEmail);
				en = cGuardianMapper.DTOToUpdateEn(gInfo, en);
				ulEn.setLastLogin(Timestamp.from(Instant.now()));
				userAdminService.getDataTranService().addGDNDetailsAndLogin(en, ulEn);
				return SMMessage.builder().appCode(AppMsg.Msg.MSG_UPDATE_003.getCode())
						.message(AppMsg.Msg.MSG_UPDATE_003.getMsgP()).build();
			} else {
				throw new SMException(AppMsg.Err.ERR_DNF_001.getCode(), AppMsg.Err.ERR_DNF_001.getMsgP("userEmail"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP());

		}
	}

	public SMMessage deleteGuardianInfo(String userEmail) {
		try {
			UserLoginInfoEn ulEn = userAdminService.getUserLoginRepo().findByUserEmail(userEmail);
			if (ulEn != null) {
				GuardianInfoEn en = guardianRepo.findByUserEmail(userEmail);
				userAdminService.getDataTranService().deleteGDNDetailsAndLogin(en, ulEn);
				return SMMessage.builder().appCode(AppMsg.Msg.MSG_DELETE_004.getCode())
						.message(AppMsg.Msg.MSG_DELETE_004.getMsgP()).build();
			} else {
				throw new SMException(AppMsg.Err.ERR_DNF_001.getCode(), AppMsg.Err.ERR_DNF_001.getMsgP("userEmail"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP());

		}
	}

	public List<GuardianInfo> getGuardianInfoAll() {
		List<GuardianInfo> uiList = guardianRepo.findAll().stream().map(en -> {
			return cGuardianMapper.EnToDTO(en);
		}).collect(Collectors.toList());
		return uiList;

	}

	public GuardianInfo getGuardianInfoByEmail(String userEmail) {
		return cGuardianMapper.EnToDTO(guardianRepo.findByUserEmail(userEmail));
	}

}
