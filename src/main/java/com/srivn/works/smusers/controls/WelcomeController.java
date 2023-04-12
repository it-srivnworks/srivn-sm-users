package com.srivn.works.smusers.controls;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("welcome")
public class WelcomeController {

	@GetMapping("/tester")
	public ResponseEntity<?> tester() {
		System.out.println("Tester");
		return new ResponseEntity<>("Howdy!!", HttpStatus.OK);
	}

}
