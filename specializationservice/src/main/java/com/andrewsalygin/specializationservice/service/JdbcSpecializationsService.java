package com.andrewsalygin.specializationservice.service;

import com.andrewsalygin.specializationservice.client.ApplicationClient;
import com.andrewsalygin.specializationservice.dto.DoctorSpecializationDTO;
import com.andrewsalygin.specializationservice.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.specializationservice.model.DoctorShortInfo;
import com.andrewsalygin.specializationservice.model.IdResponse;
import com.andrewsalygin.specializationservice.model.Specialization;
import com.andrewsalygin.specializationservice.repository.JdbcSpecializationsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcSpecializationsService {

    private final JdbcSpecializationsRepository specializationsRepository;

    private final ApplicationClient applicationClient;

    private final KafkaProducerService kafkaProducerService;

    private final KafkaConsumerService kafkaConsumerService;

    private final ModelMapper modelMapper;

    @Value("${application.kafka}")
    private boolean isKafka;

    public ResponseEntity<List<Specialization>> getSpecializations() {
        List<Specialization> resultFromRepository = specializationsRepository.getSpecializations();

        Type listType = new TypeToken<List<Specialization>>() {
        }.getType();
        List<Specialization> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<List<DoctorShortInfo>> getSpecializationsDoctors(Integer specializationId) {
        List<DoctorShortInfo> doctors;
        if (isKafka) {
            try {
                kafkaProducerService.sendSpecialization(specializationId);
                doctors = kafkaConsumerService.waitForDoctorInfo();
            } catch (InterruptedException e) {
                doctors = applicationClient.getDoctorsForSpecialization(specializationId).getBody();
            }
        } else {
            doctors = applicationClient.getDoctorsForSpecialization(specializationId).getBody();
        }

        Type listType = new TypeToken<List<DoctorShortInfo>>() {
        }.getType();
        List<DoctorShortInfo> result = modelMapper.map(doctors, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<IdResponse> addSpecialization(String specializationName) {
        IdResponse idResponse = new IdResponse();
        idResponse.setId(specializationsRepository.addSpecialization(specializationName));
        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteSpecialization(Integer specializationId) {
        specializationsRepository.deleteSpecialization(specializationId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public ResponseEntity<List<DoctorSpecializationDTO>> getDoctorSpecializations(Integer doctorId) {
        return new ResponseEntity<>(specializationsRepository.getDoctorSpecializations(doctorId), HttpStatus.OK);
    }
}
