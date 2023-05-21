package com.srivn.works.smusers.controls;

import com.srivn.works.smusers.db.dto.users.StudentInfo;
import com.srivn.works.smusers.db.dto.users.UserLoginInfo;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/users/adm/")
public class UsersAdminControl {

    private static final Logger log = LoggerFactory.getLogger(UsersAdminControl.class);

    @Autowired
    UserAdminService userAdminService;

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginInfo> authenticateUser(@RequestBody UserLoginInfo uLInfo) {
        return new ResponseEntity<>(userAdminService.authenticateUser(uLInfo), HttpStatus.OK);
    }

    @GetMapping(value = "/confirmRegistration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SMMessage> confirmRegistration(WebRequest request, @RequestParam("token") String token) {
        return new ResponseEntity<>(userAdminService.confirmRegistration(request.getLocale(), token), HttpStatus.OK);
    }

}

