package com.srivn.works.smusers.controls;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srivn.works.smusers.db.dao.users.GuardianInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.services.UserAdminService;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UsersControl {

	
	@Autowired
	UserAdminService userAdminService;
	
	private static final Logger logger = LoggerFactory.getLogger(UsersControl.class);

	@PostMapping(value = "/guardian/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewGuardianInfo(@RequestBody GuardianInfo gInfo) {
		try {
			return new ResponseEntity<>(userAdminService.addNewGuardianInfo(gInfo), HttpStatus.OK);
		} catch (SMException sme) {
			return new ResponseEntity<>(sme, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
