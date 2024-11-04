package com.uol.compass.notify.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.uol.compass.notify.dto.UserNotification;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "user-topic", groupId = "notify_group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeMessage(UserNotification notification) {
        System.out.printf("Username: %s    Operation: %s\n", notification.getUsername(), notification.getType());
    }
}
