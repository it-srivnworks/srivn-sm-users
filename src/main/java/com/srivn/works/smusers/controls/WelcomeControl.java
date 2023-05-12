package com.srivn.works.smusers.controls;

import com.srivn.works.smusers.config.GcpPubSubConfig.PubsubOutboundGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("welcome")
public class WelcomeControl {

	@Autowired
	private PubsubOutboundGateway messagingGateway;

	@GetMapping("/tester")
	public ResponseEntity<?> tester() {
		System.out.println("Tester");
		return new ResponseEntity<>("Howdy!!", HttpStatus.OK);
	}

	@GetMapping("/tester/pubsub")
	public ResponseEntity<?> testerPubSub(@RequestParam String message) {
		System.out.println("testerPubSub");
		messagingGateway.sendToPubsub(message);
		return new ResponseEntity<>("Howdy!!", HttpStatus.OK);
	}
}
