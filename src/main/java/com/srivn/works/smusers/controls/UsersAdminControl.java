package com.srivn.works.smusers.controls;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srivn.works.smusers.db.dto.users.GuardianInfo;
import com.srivn.works.smusers.db.dto.users.StaffInfo;
import com.srivn.works.smusers.db.entity.users.GuardianInfoEn;
import com.srivn.works.smusers.exception.SMException;
import com.srivn.works.smusers.services.UserAdminService;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UsersAdminControl {

	
	@Autowired
	UserAdminService userAdminService;
	
	private static final Logger logger = LoggerFactory.getLogger(UsersAdminControl.class);

	@PostMapping(value = "/guardian/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewGuardianInfo(@RequestBody GuardianInfo gInfo) {
			return new ResponseEntity<>(userAdminService.addNewGuardianInfo(gInfo), HttpStatus.OK);
	}
	
	@PutMapping(value = "/guardian/{userEmail}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateGuardianInfo(@PathVariable String userEmail, @RequestBody GuardianInfo gInfo) {
			return new ResponseEntity<>(userAdminService.updateGuardianInfo(userEmail, gInfo), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/guardian/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteGuardianInfo(@PathVariable String userEmail) {
			return new ResponseEntity<>(userAdminService.deleteGuardianInfo(userEmail), HttpStatus.OK);
	}
	
	@GetMapping(value = "/guardian/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getGuardianInfoAll() {
			return new ResponseEntity<>(userAdminService.getGuardianInfoAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/guardian/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getGuardianInfoByEmail(@PathVariable String userEmail) {
			return new ResponseEntity<>(userAdminService.getGuardianInfoByEmail(userEmail), HttpStatus.OK);
	}
	
	/*
	 * STAFF
	 * */
	@PostMapping(value = "/staff/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewStaffInfo(@RequestBody StaffInfo sInfo) {
			return new ResponseEntity<>(userAdminService.addNewStaffInfo(sInfo), HttpStatus.OK);
	}
	
	@PutMapping(value = "/staff/{userEmail}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateStaffInfo(@PathVariable String userEmail, @RequestBody StaffInfo sInfo) {
			return new ResponseEntity<>(userAdminService.updateStaffInfo(userEmail, sInfo), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/staff/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deletStaffInfo(@PathVariable String userEmail) {
			return new ResponseEntity<>(userAdminService.deleteStaffInfo(userEmail), HttpStatus.OK);
	}
	@GetMapping(value = "/staff/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStaffInfoAll() {
			return new ResponseEntity<>(userAdminService.getStaffInfoAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/staff/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStaffInfoByEmail(@PathVariable String userEmail) {
			return new ResponseEntity<>(userAdminService.getStaffInfoByEmail(userEmail), HttpStatus.OK);
	}
	
	/*
	 * ALL USER
	 * */
	
	@GetMapping(value = "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUserInfoAll() {
			return new ResponseEntity<>(userAdminService.getUserInfoAll(), HttpStatus.OK);
	}
}

