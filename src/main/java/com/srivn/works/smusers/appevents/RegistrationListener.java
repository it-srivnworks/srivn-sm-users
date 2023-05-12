package com.srivn.works.smusers.appevents;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.services.GCPPubSubService;
import com.srivn.works.smusers.services.UserAdminService;
import com.srivn.works.smusers.util.AppMsg;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnUserRegistrationEvent> {

    private final UserAdminService userAdminService;
    private final ObjectMapper objectMapper;
    private final GCPPubSubService gcpPubSubService;

    private static final Logger log = LoggerFactory.getLogger(RegistrationListener.class);

    @Override
    public void onApplicationEvent(OnUserRegistrationEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnUserRegistrationEvent event) {
        try {
            String token = UUID.randomUUID().toString();
            String recipientAddress = event.getUserLoginInfoEn().getUserEmail();
            String subject = "Welcome : Registration Confirmation";
            String confirmationUrl = "https://localhost/sm-users/regitrationConfirm?token=" + token;
            userAdminService.createVerificationToken(event.getUserLoginInfoEn(), token);

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
