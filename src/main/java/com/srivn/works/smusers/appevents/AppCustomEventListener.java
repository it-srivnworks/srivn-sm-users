package com.srivn.works.smusers.appevents;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srivn.works.smusers.config.jwt.JwtTokenUtil;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.services.GCPPubSubService;
import com.srivn.works.smusers.services.UserAdminService;
import com.srivn.works.smusers.util.AppMsg;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@RequiredArgsConstructor
public class AppCustomEventListener{

    private final UserAdminService userAdminService;
    private final ObjectMapper objectMapper;
    private final GCPPubSubService gcpPubSubService;
    private final JwtTokenUtil jwtTokenUtil;

    private static final Logger log = LoggerFactory.getLogger(AppCustomEventListener.class);

    @Async
    @EventListener
    public void registrationListener(OnUserRegistrationEvent event) throws Exception {
        log.info("registrationListener      : Source : "+event.getSource().getClass().getName());
        //Thread.sleep(5000);
        this.confirmRegistration(event);
        log.info("registrationListener      : completed");
    }

    private void confirmRegistration(OnUserRegistrationEvent event) {
        try {
            String recipientAddress = event.getUserLoginInfoEn().getUserEmail();
            String subject = "Welcome : Registration Confirmation";
            String token = userAdminService.createVerificationToken(event.getUserLoginInfoEn());
            String confirmationUrl = "https://localhost/sm-users/users/confirmRegistration?token=" + token;
            SimpleMailMessage emailMsg = new SimpleMailMessage();
            emailMsg.setTo(recipientAddress);
            emailMsg.setSubject(subject);
            emailMsg.setText(confirmationUrl);
            String emailMsgStr = objectMapper.writeValueAsString(emailMsg);
            log.info("Sending Notification : " + emailMsgStr);
            // gcpPubSubService.sendActivationEmail(emailMsgStr);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new SMException(AppMsg.Err.ERR_UKN_000.getCode(), AppMsg.Err.ERR_UKN_000.getMsgP());
        }

    }
}
