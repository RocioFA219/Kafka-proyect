package com.example.producer.producer.controller;

import com.example.producer.producer.service.KafkaProducerService;
import org.example.dto.MessageDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final KafkaProducerService kafkaProducerService;

    public MessageController(KafkaProducerService kafkaProducerService){
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public String sendMessage(@RequestBody MessageDto message){
        kafkaProducerService.sendMessage(message);
        return "Mensaje enviado: " + message;
    }
}
