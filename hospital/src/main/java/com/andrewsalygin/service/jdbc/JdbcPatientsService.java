package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.dto.patient.Patient;
import com.andrewsalygin.dto.patient.PatientShort;
import com.andrewsalygin.dto.patient.PatientWithoutIdDTO;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.PatientFullInfo;
import com.andrewsalygin.hospital.model.PatientShortInfo;
import com.andrewsalygin.hospital.model.PatientWithoutId;
import com.andrewsalygin.repository.PatientRepository;
import com.andrewsalygin.service.PatientsService;
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
@RequiredArgsConstructor
public class JdbcPatientsService implements PatientsService {

    private final PatientRepository patientRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ResponseEntity<Void> attachPatient(Integer id) {
        patientRepository.attachPatient(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> detachPatient(Integer id) {
        patientRepository.detachPatient(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> editPatient(Integer id, PatientWithoutId patientWithoutId) {
        PatientWithoutIdDTO patient = modelMapper.map(patientWithoutId, PatientWithoutIdDTO.class);
        patientRepository.editPatient(id, patient);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<PatientFullInfo> getPatient(Integer id) {
        Patient patient = patientRepository.getPatient(id);

        PatientFullInfo patientFullInfo = modelMapper.map(patient, PatientFullInfo.class);
        return new ResponseEntity<>(patientFullInfo, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<List<PatientShortInfo>> getPatients(Integer limit, Integer offset) {
        List<PatientShort> patients = patientRepository.getPatients(limit, offset);

        Type listType = new TypeToken<List<PatientShortInfo>>() {}.getType();
        List<PatientShortInfo> patientShortInfos = modelMapper.map(patients, listType);

        return new ResponseEntity<>(patientShortInfos, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<IdResponse> registerPatient(PatientWithoutId patientWithoutId) {
        PatientWithoutIdDTO patient = modelMapper.map(patientWithoutId, PatientWithoutIdDTO.class);

        Integer returnedId = patientRepository.registerPatient(patient);
        IdResponse idResponse = new IdResponse();
        idResponse.setId(returnedId);

        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }
}
