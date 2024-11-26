package com.andrewsalygin.applicationservice.service;

import com.andrewsalygin.applicationservice.dto.doctor.DoctorSpecializationDTO;
import com.andrewsalygin.applicationservice.model.Specialization;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final BlockingQueue<List<Specialization>> specializationsQueue = new LinkedBlockingQueue<>();

    private final BlockingQueue<List<DoctorSpecializationDTO>> doctorSpecializationsQueue = new LinkedBlockingQueue<>();

    private final KafkaAnswerProducerService kafkaAnswerProducerService;

    @KafkaListener(topics = "specialization", groupId = "application-service")
    private void consumeSpecialization(Integer specializationId) {
        kafkaAnswerProducerService.sendDoctors(specializationId);
    }

    @KafkaListener(topics = "specializations", groupId = "application-service")
    private void consumeSpecializations(List<Specialization> specializations) {
        try {
            specializationsQueue.put(specializations);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @KafkaListener(topics = "doctorSpecializations", groupId = "application-service")
    private void consumeDoctorSpecializations(List<DoctorSpecializationDTO> specializations) {
        try {
            doctorSpecializationsQueue.put(specializations);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public List<Specialization> waitForSpecializations() throws InterruptedException {
        return specializationsQueue.poll(10, TimeUnit.SECONDS);
    }

    public List<DoctorSpecializationDTO> waitForDoctorSpecializations() throws InterruptedException {
        return doctorSpecializationsQueue.poll(10, TimeUnit.SECONDS);
    }
}
