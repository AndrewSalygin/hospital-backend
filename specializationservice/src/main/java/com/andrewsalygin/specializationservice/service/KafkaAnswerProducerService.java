package com.andrewsalygin.specializationservice.service;

import com.andrewsalygin.specializationservice.dto.DoctorSpecializationDTO;
import com.andrewsalygin.specializationservice.model.Specialization;
import com.andrewsalygin.specializationservice.repository.JdbcSpecializationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
@RequiredArgsConstructor
public class KafkaAnswerProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final JdbcSpecializationsRepository jdbcSpecializationsRepository;

    public void sendSpecializations() {
        List<Specialization> specializations = jdbcSpecializationsRepository.getSpecializations();
        kafkaTemplate.send("specializations", specializations);
    }

    public void sendDoctorSpecializations(Integer doctorId) {
        List<DoctorSpecializationDTO> doctorSpecializations = jdbcSpecializationsRepository.getDoctorSpecializations(doctorId);
        kafkaTemplate.send("doctorSpecializations", doctorSpecializations);
    }
}
