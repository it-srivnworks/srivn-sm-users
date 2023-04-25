package com.srivn.works.smusers.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.srivn.works.smusers.db.dto.personal.AddressInfo;
import com.srivn.works.smusers.db.dto.users.StaffInfo;
import com.srivn.works.smusers.db.entity.personal.AddressInfoEn;
import com.srivn.works.smusers.db.entity.personal.ContactInfoEn;
import com.srivn.works.smusers.db.entity.users.StaffInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.mappers.CustomStaffInfoMapper;
import com.srivn.works.smusers.db.repo.personal.AddressInfoRepo;
import com.srivn.works.smusers.db.repo.personal.ContactInfoRepo;
import com.srivn.works.smusers.db.repo.users.StaffInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserLoginInfoRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.util.AppMsg;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffService {

	private static final Logger log = LoggerFactory.getLogger(StaffService.class);

	private final UserAdminService userAdminService;
	private final StaffInfoRepo staffRepo;
	private final CustomStaffInfoMapper cStaffMapper;

	/*
	 * STAFF
	 */

	public SMMessage addNewStaffInfo(StaffInfo sInfo) {
		try {
			if (userAdminService.getUserLoginRepo().checkUserEmail(sInfo.getUserEmail()) == 0) {
				StaffInfoEn en = cStaffMapper.DTOToEn(sInfo);
				UserLoginInfoEn ulEn = userAdminService.getLoginInfo(sInfo.getUserEmail());
				en.setAddressInfo(addAddressInfo(en.getAddressInfo()));
				en.setContactInfo(addContactInfo(en.getContactInfo()));
				userAdminService.getDataTranService().addSTFDetailsAndLogin(en, ulEn);
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
			UserLoginInfoEn ulEn = userAdminService.getUserLoginRepo().findByUserEmail(userEmail);
			if (ulEn != null) {
				StaffInfoEn en = staffRepo.findByUserEmail(userEmail);
				en = cStaffMapper.DTOToUpdateEn(sInfo, en);
				ulEn.setLastLogin(Timestamp.from(Instant.now()));

				en.setAddressInfo(updateAddressInfo(sInfo.getAddressInfo(), en.getAddressInfo()));
				userAdminService.getDataTranService().addSTFDetailsAndLogin(en, ulEn);
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
			UserLoginInfoEn ulEn = userAdminService.getUserLoginRepo().findByUserEmail(userEmail);
			if (ulEn != null) {
				StaffInfoEn en = staffRepo.findByUserEmail(userEmail);
				userAdminService.getDataTranService().deleteSTFDetailsAndLogin(en, ulEn);
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
		AddressInfoEn aiD = userAdminService.getAddressRepo().findIfAddressExist(ai.getHouseNumber(), ai.getStreet(),
				ai.getCity(), ai.getZipCode());
		if (aiD != null) {
			return aiD;
		} else {
			return userAdminService.getAddressRepo().save(ai);
		}

	}

	private AddressInfoEn updateAddressInfo(AddressInfo ai, AddressInfoEn aiE) {
		if (Objects.equals(aiE.getZipCode(), ai.getZipCode())
				&& Objects.equals(aiE.getHouseNumber(), ai.getHouseNumber())
				&& Objects.equals(aiE.getStreet(), ai.getStreet()) && Objects.equals(aiE.getCity(), ai.getCity())) {
			return aiE;
		} else {
			return userAdminService.getAddressRepo().save(cStaffMapper.DTOToEnAddress(ai));
		}

	}

	private ContactInfoEn addContactInfo(ContactInfoEn ci) {
		long iD = Long.parseLong(ci.getPrimaryNo().replace("+", "").replace("-", "").trim());
		Optional<ContactInfoEn> ciN = userAdminService.getContactRepo().findById(iD);
		if (ciN.isPresent()) {
			return ciN.get();
		} else {
			ci.setID(iD);
			return userAdminService.getContactRepo().save(ci);
		}
	}

	public List<StaffInfo> getStaffInfoAll() {
		List<StaffInfo> siList = staffRepo.findAll().stream().map(en -> {
			return cStaffMapper.EnToDTO(en);
		}).collect(Collectors.toList());
		return siList;

	}

	public StaffInfo getStaffInfoByEmail(String userEmail) {
		return cStaffMapper.EnToDTO(staffRepo.findByUserEmail(userEmail));
	}
}
