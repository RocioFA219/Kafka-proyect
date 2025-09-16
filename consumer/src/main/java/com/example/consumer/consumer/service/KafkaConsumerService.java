package com.example.consumer.consumer.service;


import org.example.dto.MessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "consumer")
    public void listen(MessageDto message){
        System.out.println("Mensaje recibido :" + message);
    }
}
