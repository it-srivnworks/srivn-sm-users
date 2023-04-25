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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAdminService {

	private static final Logger log = LoggerFactory.getLogger(UserAdminService.class);

	private final GuardianInfoRepo grRepo;
	private final StaffInfoRepo stRepo;
	private final UserInfoRepo uiRepo;

	private final UserLoginInfoRepo ulRepo;

	private final ContactInfoRepo ciRepo;

	private final AddressInfoRepo aiRepo;

	private final DataTransactionService dataTransactionS;

	private final CustomGuardianInfoMapper cGuiInfoMapper;
	private final CustomStaffInfoMapper cStaffInfoMapper;

	/*
	 * GUARDIAN INFO
	 */
	public SMMessage addNewGuardianInfo(GuardianInfo gInfo) {
		try {
			if (ulRepo.checkUserEmail(gInfo.getUserEmail()) == 0) {
				GuardianInfoEn en = cGuiInfoMapper.DTOToEn(gInfo);
				UserLoginInfoEn ulEn = getLoginInfo(gInfo.getUserEmail());
				dataTransactionS.addGDNDetailsAndLogin(en, ulEn);
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
			UserLoginInfoEn ulEn = ulRepo.findByUserEmail(userEmail);
			if (ulEn != null) {
				GuardianInfoEn en = grRepo.findByUserEmail(userEmail);
				en = cGuiInfoMapper.DTOToUpdateEn(gInfo, en);
				ulEn.setLastLogin(Timestamp.from(Instant.now()));
				dataTransactionS.addGDNDetailsAndLogin(en, ulEn);
				return SMMessage.builder().appCode(AppMsg.Msg.MSG_UPDATE_003.getCode())
						.message(AppMsg.Msg.MSG_UPDATE_003.getMsgP()).build();
			} else {
				throw new SMException(AppMsg.Err.ERR__DNF_001.getCode(), AppMsg.Err.ERR__DNF_001.getMsgP("userEmail"));
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
			UserLoginInfoEn ulEn = ulRepo.findByUserEmail(userEmail);
			if (ulEn != null) {
				GuardianInfoEn en = grRepo.findByUserEmail(userEmail);
				dataTransactionS.deleteGDNDetailsAndLogin(en, ulEn);
				return SMMessage.builder().appCode(AppMsg.Msg.MSG_DELETE_004.getCode())
						.message(AppMsg.Msg.MSG_DELETE_004.getMsgP()).build();
			} else {
				throw new SMException(AppMsg.Err.ERR__DNF_001.getCode(), AppMsg.Err.ERR__DNF_001.getMsgP("userEmail"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP());

		}
	}

	public List<GuardianInfo> getGuardianInfoAll() {
		List<GuardianInfo> uiList = grRepo.findAll().stream().map(en -> {
			return cGuiInfoMapper.EnToDTO(en);
		}).collect(Collectors.toList());
		return uiList;

	}

	public GuardianInfo getGuardianInfoByEmail(String userEmail) {
		return cGuiInfoMapper.EnToDTO(grRepo.findByUserEmail(userEmail));
	}

	/*
	 * STAFF
	 */

	public SMMessage addNewStaffInfo(StaffInfo sInfo) {
		try {
			if (ulRepo.checkUserEmail(sInfo.getUserEmail()) == 0) {
				StaffInfoEn en = cStaffInfoMapper.DTOToEn(sInfo);
				UserLoginInfoEn ulEn = getLoginInfo(sInfo.getUserEmail());
				en.setAddressInfo(addAddressInfo(en.getAddressInfo()));
				en.setContactInfo(addContactInfo(en.getContactInfo()));
				dataTransactionS.addSTFDetailsAndLogin(en, ulEn);
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

	public SMMessage updateStaffInfo(String userEmail, StaffInfo sInfo) {
		try {
			UserLoginInfoEn ulEn = ulRepo.findByUserEmail(userEmail);
			if (ulEn != null) {
				StaffInfoEn en = stRepo.findByUserEmail(userEmail);
				en = cStaffInfoMapper.DTOToUpdateEn(sInfo, en);
				ulEn.setLastLogin(Timestamp.from(Instant.now()));

				en.setAddressInfo(updateAddressInfo(sInfo.getAddressInfo(), en.getAddressInfo()));
				dataTransactionS.addSTFDetailsAndLogin(en, ulEn);
				return SMMessage.builder().appCode(AppMsg.Msg.MSG_UPDATE_003.getCode())
						.message(AppMsg.Msg.MSG_UPDATE_003.getMsgP()).build();
			} else {
				throw new SMException(AppMsg.Err.ERR__DNF_001.getCode(), AppMsg.Err.ERR__DNF_001.getMsgP("userEmail"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP());

		}
	}

	public SMMessage deleteStaffInfo(String userEmail) {
		try {
			UserLoginInfoEn ulEn = ulRepo.findByUserEmail(userEmail);
			if (ulEn != null) {
				StaffInfoEn en = stRepo.findByUserEmail(userEmail);
				dataTransactionS.deleteSTFDetailsAndLogin(en, ulEn);
				return SMMessage.builder().appCode(AppMsg.Msg.MSG_DELETE_004.getCode())
						.message(AppMsg.Msg.MSG_DELETE_004.getMsgP()).build();
			} else {
				throw new SMException(AppMsg.Err.ERR__DNF_001.getCode(), AppMsg.Err.ERR__DNF_001.getMsgP("userEmail"));
			}
		} catch (SMException e) {
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP());

		}
	}

	private AddressInfoEn addAddressInfo(AddressInfoEn ai) {
		AddressInfoEn aiD = aiRepo.findIfAddressExist(ai.getHouseNumber(), ai.getStreet(), ai.getCity(),
				ai.getZipCode());
		if (aiD != null) {
			return aiD;
		} else {
			return aiRepo.save(ai);
		}

	}

	private AddressInfoEn updateAddressInfo(AddressInfo ai, AddressInfoEn aiE) {
		if (Objects.equals(aiE.getZipCode(), ai.getZipCode())
				&& Objects.equals(aiE.getHouseNumber(), ai.getHouseNumber())
				&& Objects.equals(aiE.getStreet(), ai.getStreet()) && Objects.equals(aiE.getCity(), ai.getCity())) {
			return aiE;
		} else {
			return aiRepo.save(cStaffInfoMapper.DTOToEnAddress(ai));
		}

	}

	private ContactInfoEn addContactInfo(ContactInfoEn ci) {
		long iD = Long.parseLong(ci.getPrimaryNo().replace("+", "").replace("-", "").trim());
		Optional<ContactInfoEn> ciN = ciRepo.findById(iD);
		if (ciN.isPresent()) {
			return ciN.get();
		} else {
			ci.setID(iD);
			return ciRepo.save(ci);
		}
	}

	public List<StaffInfo> getStaffInfoAll() {
		List<StaffInfo> siList = stRepo.findAll().stream().map(en -> {
			return cStaffInfoMapper.EnToDTO(en);
		}).collect(Collectors.toList());
		return siList;

	}

	public StaffInfo getStaffInfoByEmail(String userEmail) {
		return cStaffInfoMapper.EnToDTO(stRepo.findByUserEmail(userEmail));
	}

	/*
	 * USER INFO
	 */

	/* Heavy query due to JPA JOIN on all user child tables */
	public List<UserInfo> getUserInfoAll() {

		List<UserInfo> uiList = uiRepo.findAll().stream().map(en -> {
			UserInfo ui = new UserInfo();
			BeanUtils.copyProperties(en, ui);
			return ui;
		}).collect(Collectors.toList());
		return uiList;
	}

	public SMMessage checkIfEmailExist(String userEmail) {
		if (ulRepo.checkUserEmail(userEmail) > 0) {
			return SMMessage.builder().appCode(AppMsg.Msg.MSG_EXIST_002.getCode())
					.message(AppMsg.Msg.MSG_EXIST_002.getMsg()).build();
		} else {
			throw new SMException(AppMsg.Err.ERR__DNF_001.getCode(), AppMsg.Err.ERR__DNF_001.getMsgP("userEmail"));
		}
	}

	private UserLoginInfoEn getLoginInfo(String userEmail) {
		UserLoginInfoEn ulEn = new UserLoginInfoEn(userEmail, AppUtil.generatePwd(), Timestamp.from(Instant.now()),
				AppC.Status.NEW.getCode());
		return ulEn;
	}
}
