package com.blueyonder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blueyonder.service.Producer;

@RestController
public class KafkaController {
	@Autowired
	Producer producer;
	
	@GetMapping("/producerMsg")
	public void getMsgFromClient(@RequestParam("message") String message) {
		producer.sendMsgToTopic(message);
	}
}
