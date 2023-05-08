package com.srivn.works.smusers.controls;

import com.srivn.works.smusers.db.dto.users.StaffInfo;
import com.srivn.works.smusers.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/staff")
public class StaffInfoControl {

	@Autowired
	StaffService staffService;
	
	/*
	 * STAFF
	 * */
	@PostMapping(value = "/ADD", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewStaffInfo(@RequestBody StaffInfo sInfo) {
		return new ResponseEntity<>(staffService.addNewStaffInfo(sInfo), HttpStatus.OK);
	}
	
	@PutMapping(value = "/UPDATE/{userEmail}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateStaffInfo(@PathVariable String userEmail, @RequestBody StaffInfo sInfo) {
			return new ResponseEntity<>(staffService.updateStaffInfo(userEmail, sInfo), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/DELETE/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deletStaffInfo(@PathVariable String userEmail) {
			return new ResponseEntity<>(staffService.deleteStaffInfo(userEmail), HttpStatus.OK);
	}
	@GetMapping(value = "/ALL", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStaffInfoAll() {
			return new ResponseEntity<>(staffService.getStaffInfoAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStaffInfoByEmail(@PathVariable String userEmail) {
			return new ResponseEntity<>(staffService.getStaffInfoByEmail(userEmail), HttpStatus.OK);
	}
}
