package com.srivn.works.smusers.controls;

import com.srivn.works.smusers.exception.SMMessage;
import com.srivn.works.smusers.services.GuardianService;
import com.srivn.works.smusers.services.StaffService;
import com.srivn.works.smusers.services.UserAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/users")
public class UsersAdminControl {

    private static final Logger log = LoggerFactory.getLogger(UsersAdminControl.class);

    @Autowired
    UserAdminService userAdminService;

    @GetMapping(value = "/confirmRegistration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SMMessage> confirmRegistration(WebRequest request, @RequestParam("token") String token) {
        return new ResponseEntity<>(userAdminService.confirmRegistration(request.getLocale(), token), HttpStatus.OK);
    }
}

