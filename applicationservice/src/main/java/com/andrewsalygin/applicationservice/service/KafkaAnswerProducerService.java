package com.andrewsalygin.applicationservice.service;

import com.andrewsalygin.applicationservice.model.DoctorShortInfo;
import java.util.List;
import com.andrewsalygin.applicationservice.repository.jdbc.JdbcDoctorsRepository;
import com.andrewsalygin.applicationservice.service.jdbc.JdbcDoctorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaAnswerProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final JdbcDoctorsRepository jdbcDoctorsRepository;

    public void sendDoctors(Integer specializationId) {
        List<DoctorShortInfo> doctors = jdbcDoctorsRepository.getDoctorsForSpecialization(specializationId);
        kafkaTemplate.send("specializationsDoctors", doctors);
    }
}
