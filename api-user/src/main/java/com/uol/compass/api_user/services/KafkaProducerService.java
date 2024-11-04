package com.uol.compass.api_user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.uol.compass.api_user.dto.UserNotification;

@Service
public class KafkaProducerService {

	private static final String TOPIC = "user-topic";

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(String type, String username) {
		kafkaTemplate.send(TOPIC, new UserNotification(type, username));
	}
}

