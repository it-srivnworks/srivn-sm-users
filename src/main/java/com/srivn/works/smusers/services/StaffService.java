package com.srivn.works.smusers.services;

import com.srivn.works.smusers.db.dto.users.StaffInfo;
import com.srivn.works.smusers.db.entity.users.StaffInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.mappers.CustomStaffInfoMapper;
import com.srivn.works.smusers.db.repo.users.StaffInfoRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.util.AppMsg;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffService {

    private static final Logger log = LoggerFactory.getLogger(StaffService.class);

    private final UserAdminService userAdminService;
    private final PersonalInfoService piService;
    private final StaffInfoRepo staffRepo;
    private final CustomStaffInfoMapper cStaffMapper;

    /*
     * STAFF
     */
    public SMMessage addNewStaffInfo(StaffInfo sInfo) {
        try {
            if (userAdminService.getUserLoginRepo().checkUserEmail(sInfo.getUserEmail()) == 0) {
                StaffInfoEn en = cStaffMapper.DTOToEn(sInfo);
                UserLoginInfoEn ulEn = UserLoginInfoEn.createNew(sInfo.getUserEmail());
                if (sInfo.getAddressInfo() != null)
                    en.setAddressInfo(piService.addAddressInfo(sInfo.getAddressInfo()));
                if (sInfo.getContactInfo() != null)
                    en.setContactInfo(piService.addContactInfo(sInfo.getContactInfo()));
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
            UserLoginInfoEn ulEn = userAdminService.getUserLoginInfo(userEmail);
            StaffInfoEn en = staffRepo.findByUserEmail(userEmail);
            en = cStaffMapper.DTOToUpdateEn(sInfo, en);
            if (sInfo.getAddressInfo() != null)
                en.setAddressInfo(piService.updateAddressInfo(sInfo.getAddressInfo(), en.getAddressInfo()));
            if (sInfo.getContactInfo() != null)
                en.setContactInfo(piService.updateContactInfo(sInfo.getContactInfo(), en.getContactInfo()));
            userAdminService.getDataTranService().addSTFDetailsAndLogin(en, ulEn);
            return SMMessage.builder().appCode(AppMsg.Msg.MSG_UPDATE_003.getCode())
                    .message(AppMsg.Msg.MSG_UPDATE_003.getMsgP()).build();
        } catch (SMException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP());

        }
    }

    public SMMessage deleteStaffInfo(String userEmail) {
        try {
            UserLoginInfoEn ulEn = userAdminService.getUserLoginInfo(userEmail);
            StaffInfoEn en = staffRepo.findByUserEmail(userEmail);
            userAdminService.getDataTranService().deleteSTFDetailsAndLogin(en, ulEn);
            return SMMessage.builder().appCode(AppMsg.Msg.MSG_DELETE_004.getCode())
                    .message(AppMsg.Msg.MSG_DELETE_004.getMsgP()).build();
        } catch (SMException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP());

        }
    }

    public List<StaffInfo> getStaffInfoAll() {
        List<StaffInfo> siList = staffRepo.findAll().stream().map(cStaffMapper::EnToDTO).collect(Collectors.toList());
        if(siList.size() > 0){
            return siList;
        }else{
            throw new SMException(AppMsg.Err.ERR_DNF_001.getCode(), AppMsg.Err.ERR_DNF_001.getMsgP("Records"));
        }

    }

    public StaffInfo getStaffInfoByEmail(String userEmail) {
        StaffInfoEn en = staffRepo.findByUserEmail(userEmail);
        if(en != null){
            return cStaffMapper.EnToDTO(en);
        }else{
            throw new SMException(AppMsg.Err.ERR_DNF_001.getCode(), AppMsg.Err.ERR_DNF_001.getMsgP("userEmail"));
        }
    }
}
