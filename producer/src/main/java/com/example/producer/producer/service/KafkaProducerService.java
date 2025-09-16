package com.example.producer.producer.service;


import org.example.dto.MessageDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, MessageDto> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(MessageDto message){
        kafkaTemplate.send("test-topic",message);
        System.out.println("Se ha enviado el mensaje: " + message);
    }
}
