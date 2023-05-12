package com.srivn.works.smusers.services;

import com.srivn.works.smusers.db.dto.users.UserInfo;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.entity.users.VerifTokenEn;
import com.srivn.works.smusers.db.repo.personal.AddressInfoRepo;
import com.srivn.works.smusers.db.repo.personal.ContactInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserLoginInfoRepo;
import com.srivn.works.smusers.db.repo.users.VerifTokenRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.util.AppMsg;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter
@Service
@RequiredArgsConstructor
public class UserAdminService {

    private static final Logger log = LoggerFactory.getLogger(UserAdminService.class);

    private final UserInfoRepo userInfoRepo;

    private final UserLoginInfoRepo userLoginRepo;

    private final AddressInfoRepo addressRepo;

    private final ContactInfoRepo contactRepo;

    private final VerifTokenRepo verifTokenRepo;

    private final DataTransactionService dataTranService;


    /* Heavy query due to JPA JOIN on all user child tables */
    public List<UserInfo> getUserInfoAll() {
        List<UserInfo> uiList = userInfoRepo.findAll().stream().map(en -> {
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
            throw new SMException(AppMsg.Err.ERR_DNF_001.getCode(), AppMsg.Err.ERR_DNF_001.getMsgP("userEmail"));
        }
    }

    protected UserLoginInfoEn getUserLoginInfo(String userEmail) throws SMException{
        UserLoginInfoEn ulEn = userLoginRepo.findByUserEmail(userEmail);
        if (ulEn != null) {
            return ulEn;
        } else {
            throw new SMException(AppMsg.Err.ERR_DNF_001.getCode(), AppMsg.Err.ERR_DNF_001.getMsgP("userEmail"));
        }
    }

    public void createVerificationToken(UserLoginInfoEn userLoginInfoEn,String token) {
        VerifTokenEn myToken = new VerifTokenEn(token, userLoginInfoEn);
        verifTokenRepo.save(myToken);
    }

    public SMMessage confirmRegistration(Locale locale,String token){
        return SMMessage.builder().appCode(AppMsg.Msg.MSG_EXIST_002.getCode())
                .message(AppMsg.Msg.MSG_EXIST_002.getMsg()).build();
    }

}
