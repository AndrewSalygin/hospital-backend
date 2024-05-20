package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.dto.patient.PatientShortInfoDTO;
import com.andrewsalygin.dto.specialization.SpecializationDTO;
import com.andrewsalygin.hospital.model.DoctorShortInfo;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.PatientShortInfo;
import com.andrewsalygin.hospital.model.Specialization;
import com.andrewsalygin.repository.interfaces.SpecializationsRepository;
import com.andrewsalygin.service.interfaces.SpecializationsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcSpecializationsService implements SpecializationsService {

    private final SpecializationsRepository specializationsRepository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<Specialization>> getSpecializations() {
        List<SpecializationDTO> resultFromRepository = specializationsRepository.getSpecializations();

        Type listType = new TypeToken<List<Specialization>>() {
        }.getType();
        List<Specialization> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DoctorShortInfo>> getSpecializationsDoctors(Integer specializationId) {
        List<DoctorShortInfoDTO> resultFromRepository =
            specializationsRepository.getSpecializationsDoctors(specializationId);

        Type listType = new TypeToken<List<DoctorShortInfo>>() {
        }.getType();
        List<DoctorShortInfo> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<IdResponse> addSpecialization(String specializationName) {
        IdResponse idResponse = new IdResponse();
        idResponse.setId(specializationsRepository.addSpecialization(specializationName));
        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteSpecialization(Integer specializationId) {
        specializationsRepository.deleteSpecialization(specializationId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
