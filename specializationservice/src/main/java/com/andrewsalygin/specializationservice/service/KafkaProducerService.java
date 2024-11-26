package com.andrewsalygin.specializationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendSpecialization(Integer specializationId) {
        kafkaTemplate.send("specialization", specializationId);
    }
}