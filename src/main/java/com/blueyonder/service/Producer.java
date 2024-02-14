package com.blueyonder.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	//Create reference to kafka template
	KafkaTemplate<String,String> kafkaTemplate;
	
	public void sendMsgToTopic(String msg) {
		kafkaTemplate.send("Mytopic",msg);
	}
	

}
