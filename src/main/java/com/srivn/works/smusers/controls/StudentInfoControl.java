package com.srivn.works.smusers.controls;

import com.srivn.works.smusers.db.dto.users.StudentInfo;
import com.srivn.works.smusers.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/student")
public class StudentInfoControl {

	@Autowired
	StudentService studentService;
	
	/*
	 * STUDENT
	 * */
	@PostMapping(value = "/ADD", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewStudentInfo(@RequestBody StudentInfo sInfo) {
		return new ResponseEntity<>(studentService.addNewStudentInfo(sInfo), HttpStatus.OK);
	}

	@PutMapping(value = "/UPDATE/{userEmail}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateStudentInfo(@PathVariable String userEmail, @RequestBody StudentInfo sInfo) {
		return new ResponseEntity<>(studentService.updateStudentInfo(userEmail, sInfo), HttpStatus.OK);
	}

	@DeleteMapping(value = "/DELETE/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteStudentInfo(@PathVariable String userEmail) {
		return new ResponseEntity<>(studentService.deleteStudentInfo(userEmail), HttpStatus.OK);
	}
	@GetMapping(value = "/ALL", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudentInfoAll() {
		return new ResponseEntity<>(studentService.getStudentInfoAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/{userEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudentInfoByEmail(@PathVariable String userEmail) {
		return new ResponseEntity<>(studentService.getStudentInfoByEmail(userEmail), HttpStatus.OK);
	}
}
