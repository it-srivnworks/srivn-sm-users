package com.srivn.works.smusers.services;

import com.srivn.works.smusers.db.dto.users.StudentInfo;
import com.srivn.works.smusers.db.entity.users.StudentInfoEn;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.mappers.CustomStudentInfoMapper;
import com.srivn.works.smusers.db.repo.users.StudentInfoRepo;
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
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StaffService.class);
    private final UserAdminService userAdminService;
    private final PersonalInfoService piService;

    private final StudentInfoRepo studentRepo;
    private final CustomStudentInfoMapper cStudentMapper;

    /*
     * STUDENT
     */
    public SMMessage addNewStudentInfo(StudentInfo sInfo) {
        try {
            if (userAdminService.getUserLoginRepo().checkUserEmail(sInfo.getUserEmail()) == 0) {
                StudentInfoEn en = cStudentMapper.DTOToEn(sInfo);
                UserLoginInfoEn ulEn = UserLoginInfoEn.createNew(sInfo.getUserEmail());
                if (sInfo.getAddressInfo() != null)
                    en.setAddressInfo(piService.addAddressInfo(sInfo.getAddressInfo()));
                if (sInfo.getContactInfo() != null)
                    en.setContactInfo(piService.addContactInfo(sInfo.getContactInfo()));
                userAdminService.getDataTranService().addSTUDetailsAndLogin(en, ulEn);
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

    public SMMessage updateStudentInfo(String userEmail, StudentInfo sInfo) {
        try {
            UserLoginInfoEn ulEn = userAdminService.getUserLoginInfo(userEmail);
            StudentInfoEn en = studentRepo.findByUserEmail(userEmail);
            en = cStudentMapper.DTOToUpdateEn(sInfo, en);
            if (sInfo.getAddressInfo() != null)
                en.setAddressInfo(piService.updateAddressInfo(sInfo.getAddressInfo(), en.getAddressInfo()));
            if (sInfo.getContactInfo() != null)
                en.setContactInfo(piService.updateContactInfo(sInfo.getContactInfo(), en.getContactInfo()));
            userAdminService.getDataTranService().addSTUDetailsAndLogin(en, ulEn);
            return SMMessage.builder().appCode(AppMsg.Msg.MSG_UPDATE_003.getCode())
                    .message(AppMsg.Msg.MSG_UPDATE_003.getMsgP()).build();
        } catch (SMException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP());

        }
    }

    public SMMessage deleteStudentInfo(String userEmail) {
        try {
            UserLoginInfoEn ulEn = userAdminService.getUserLoginInfo(userEmail);
            StudentInfoEn en = studentRepo.findByUserEmail(userEmail);
            userAdminService.getDataTranService().deleteSTUDetailsAndLogin(en, ulEn);
            return SMMessage.builder().appCode(AppMsg.Msg.MSG_DELETE_004.getCode())
                    .message(AppMsg.Msg.MSG_DELETE_004.getMsgP()).build();
        } catch (SMException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP());

        }
    }

    public List<StudentInfo> getStudentInfoAll(){
        List<StudentInfo> siList = studentRepo.findAll().stream().map(cStudentMapper::EnToDTO).collect(Collectors.toList());
        if(siList.size() > 0){
            return siList;
        }else{
            throw new SMException(AppMsg.Err.ERR_DNF_001.getCode(), AppMsg.Err.ERR_DNF_001.getMsgP("Records"));
        }
    }

    public StudentInfo getStudentInfoByEmail(String userEmail){
        StudentInfoEn en = studentRepo.findByUserEmail(userEmail);
        if(en != null){
            return cStudentMapper.EnToDTO(en);
        }else{
            throw new SMException(AppMsg.Err.ERR_DNF_001.getCode(), AppMsg.Err.ERR_DNF_001.getMsgP("userEmail"));
        }
    }
}
