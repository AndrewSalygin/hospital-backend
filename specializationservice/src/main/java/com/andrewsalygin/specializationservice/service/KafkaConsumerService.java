package com.andrewsalygin.specializationservice.service;

import com.andrewsalygin.specializationservice.dto.StringWrapper;
import com.andrewsalygin.specializationservice.model.DoctorShortInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final BlockingQueue<List<DoctorShortInfo>> doctorInfoQueue = new LinkedBlockingQueue<>();

    private final KafkaAnswerProducerService kafkaAnswerProducerService;

    @KafkaListener(topics = "specializationsDoctors", groupId = "specialization-service")
    private void consumeSpecializationsDoctors(List<DoctorShortInfo> specializationsDoctors) {
        try {
            doctorInfoQueue.put(specializationsDoctors);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @KafkaListener(topics = "notify.specializations", groupId = "specialization-service")
    private void consumeNotifySpecializations(StringWrapper notify) {
        kafkaAnswerProducerService.sendSpecializations();
    }

    @KafkaListener(topics = "doctors", groupId = "specialization-service")
    private void consumeDoctorId(Integer doctorId) {
        kafkaAnswerProducerService.sendDoctorSpecializations(doctorId);
    }

    public List<DoctorShortInfo> waitForDoctorInfo() throws InterruptedException {
        return doctorInfoQueue.poll(10, TimeUnit.SECONDS);
    }
}
