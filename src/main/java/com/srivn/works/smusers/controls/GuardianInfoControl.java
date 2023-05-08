package com.srivn.works.smusers.controls;

import com.srivn.works.smusers.db.dto.users.GuardianInfo;
import com.srivn.works.smusers.services.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/guardians")
public class GuardianInfoControl{

	@Autowired
	GuardianService guardianService;
	
	@PostMapping(value = "/ADD", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewGuardianInfo(@RequestBody GuardianInfo gInfo) {
			return new ResponseEntity<>(guardianService.addNewGuardianInfo(gInfo), HttpStatus.OK);
	}
	
	@PutMapping(value = "/UPDATE/{userEmail}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateGuardianInfo(@PathVariable String userEmail, @RequestBody GuardianInfo gInfo) {
			return new ResponseEntity<>(guardianService.updateGuardianInfo(userEmail, gInfo), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/DELETE/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteGuardianInfo(@PathVariable String userEmail) {
			return new ResponseEntity<>(guardianService.deleteGuardianInfo(userEmail), HttpStatus.OK);
	}
	
	@GetMapping(value = "/ALL", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getGuardianInfoAll() {
			return new ResponseEntity<>(guardianService.getGuardianInfoAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getGuardianInfoByEmail(@PathVariable String userEmail) {
			return new ResponseEntity<>(guardianService.getGuardianInfoByEmail(userEmail), HttpStatus.OK);
	}
	
}
