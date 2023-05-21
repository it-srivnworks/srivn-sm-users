package com.srivn.works.smusers.services;

import com.srivn.works.smusers.config.jwt.JwtTokenUtil;
import com.srivn.works.smusers.db.dto.users.UserInfo;
import com.srivn.works.smusers.db.dto.users.UserLoginInfo;
import com.srivn.works.smusers.db.entity.users.UserLoginInfoEn;
import com.srivn.works.smusers.db.entity.users.VerifTokenEn;
import com.srivn.works.smusers.db.repo.personal.AddressInfoRepo;
import com.srivn.works.smusers.db.repo.personal.ContactInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserInfoRepo;
import com.srivn.works.smusers.db.repo.users.UserLoginInfoRepo;
import com.srivn.works.smusers.db.repo.users.VerifTokenRepo;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.util.AppC;
import com.srivn.works.smusers.util.AppMsg;
import com.srivn.works.smusers.util.AppUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    private final JwtTokenUtil jwtTokenUtil;
    @Value("${sm-users.secret.key}")
    private String secretKey;


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
            return SMMessage.builder().appCode(AppMsg.Msg.MSG_EXIST_002.getCode()).message(AppMsg.Msg.MSG_EXIST_002.getMsg()).build();
        } else {
            throw new SMException(AppMsg.Err.ERR_DNF_001.getCode(), AppMsg.Err.ERR_DNF_001.getMsgP("userEmail"));
        }
    }

    protected UserLoginInfoEn getUserLoginInfo(String userEmail) throws SMException {
        UserLoginInfoEn ulEn = userLoginRepo.findByUserEmail(userEmail);
        if (ulEn != null) {
            return ulEn;
        } else {
            throw new SMException(AppMsg.Err.ERR_DNF_001.getCode(), AppMsg.Err.ERR_DNF_001.getMsgP("userEmail"));
        }
    }

    public UserDetails loadUserByUserName(String userEmail) {
        UserLoginInfoEn ulEn = userLoginRepo.findByUserEmail(userEmail);
        if (ulEn != null) {
            return new User(ulEn.getUserEmail(), ulEn.getUserPassword(), new ArrayList<>());
        } else {
            throw new SMException(AppMsg.Err.ERR_DNF_001.getCode(), AppMsg.Err.ERR_DNF_001.getMsgP("userEmail"));
        }
    }

    public String createVerificationToken(UserLoginInfoEn userLoginInfoEn) {
        final String token = AppUtil.convertStringToHex(AppUtil.encryptToken(userLoginInfoEn.getUserEmail(), this.secretKey));
        VerifTokenEn myToken = new VerifTokenEn(token, userLoginInfoEn);
        verifTokenRepo.save(myToken);
        return token;
    }

    public UserLoginInfo authenticateUser(UserLoginInfo uLInfo) {
        UserLoginInfoEn ulEn = userLoginRepo.findByUserEmail(uLInfo.getEmail());
        if (ulEn == null)
            throw new SMException(AppMsg.Err.ERR_DNF_001.getCode(), AppMsg.Err.ERR_DNF_001.getMsgP("userEmail"));
        if (!uLInfo.getPassword().equals(ulEn.getUserPassword()))
            throw new SMException(AppMsg.Err.ERR_AUTH_0034.getCode(), AppMsg.Err.ERR_AUTH_0034.getMsgP("userEmail"));
        if (ulEn.getCurrentStatus() != AppC.Status.ACTIVE.getCode()){
            throw new SMException(AppMsg.Err.ERR_INACTIVE_0035.getCode(), AppMsg.Err.ERR_INACTIVE_0035.getMsgP(ulEn.getUserEmail()));
        } else {
            User jwtUser = new User(ulEn.getUserEmail(), ulEn.getUserPassword(), new ArrayList<>());
            final String token = jwtTokenUtil.generateToken(jwtUser);
            return UserLoginInfo.builder().email(ulEn.getUserEmail()).token(token).build();
        }
    }

    public SMMessage confirmRegistration(Locale locale, String token) {
        String userEmail = AppUtil.decryptToken(AppUtil.convertHexToString(token), this.secretKey);
        UserLoginInfoEn ulEn = userLoginRepo.findByUserEmail(userEmail);
        VerifTokenEn verifTokenEn = verifTokenRepo.findByUser(ulEn);
        if (verifTokenEn.getToken().equals(token)) {
            if (!verifTokenEn.getExpiryDate().before(new Date())) {
                ulEn.setCurrentStatus(AppC.Status.ACTIVE.getCode());
                userLoginRepo.save(ulEn);
                return SMMessage.builder().appCode(AppMsg.Msg.MSG_ACTIVATED_005.getCode()).message(AppMsg.Msg.MSG_ACTIVATED_005.getMsgP(userEmail)).build();
            } else {
                throw new SMException(AppMsg.Err.ERR_EXPR_004.getCode(), AppMsg.Err.ERR_EXPR_004.getMsgP("userEmail"));
            }
        } else {
            return SMMessage.builder().appCode(AppMsg.Err.ERR_DNF_001.getCode()).message(AppMsg.Err.ERR_DNF_001.getMsg()).build();
        }
    }

}
