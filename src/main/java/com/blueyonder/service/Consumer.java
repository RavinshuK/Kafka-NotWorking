package com.blueyonder.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
	
	@KafkaListener(topics="Mytopic", groupId="team2")
	public void listenToTopic(String receivedMessage) {
		System.out.println("Message received: "+receivedMessage);
	}

}
