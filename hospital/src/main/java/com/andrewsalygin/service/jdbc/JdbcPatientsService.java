package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.dto.patient.PatientFullInfoDTO;
import com.andrewsalygin.dto.patient.PatientShortInfoDTO;
import com.andrewsalygin.dto.patient.PatientWithoutIdDTO;
import com.andrewsalygin.hospital.model.CreatePatientJournalNote;
import com.andrewsalygin.hospital.model.DiseaseFullInfo;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.PatientFullInfo;
import com.andrewsalygin.hospital.model.PatientJournalNote;
import com.andrewsalygin.hospital.model.PatientShortInfo;
import com.andrewsalygin.hospital.model.PatientWithDisease;
import com.andrewsalygin.hospital.model.PatientWithoutId;
import com.andrewsalygin.hospital.model.RecipeFullInfo;
import com.andrewsalygin.repository.PatientRepository;
import com.andrewsalygin.service.PatientsService;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        PatientFullInfoDTO patient = patientRepository.getPatient(id);

        PatientFullInfo patientFullInfo = modelMapper.map(patient, PatientFullInfo.class);
        return new ResponseEntity<>(patientFullInfo, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<List<PatientShortInfo>> getPatients(Integer limit, Integer offset) {
        List<PatientShortInfoDTO> patients = patientRepository.getPatients(limit, offset);

        Type listType = new TypeToken<List<PatientShortInfo>>() {
        }.getType();
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

    @Override
    public ResponseEntity<Void> deletePatient(Integer id) {
        patientRepository.deletePatient(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<IdResponse> addPatientNote(CreatePatientJournalNote createMeetingJournalNote) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletePatientNote(Integer medicalHistoryNoteId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> changePatientNote(
        Integer medicalHistoryNoteId,
        CreatePatientJournalNote createPatientJournalNote
    ) {
        return null;
    }

    @Override
    public ResponseEntity<List<RecipeFullInfo>> getRecipesPatient(Integer patientId) {
        return null;
    }

    @Override
    public ResponseEntity<IdResponse> addRecipeToPatient(
        Integer patientId,
        Integer medicationId,
        LocalDate expirationDate,
        Integer medicalHistoryNoteId
    ) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addDiseaseToPatient(
        Integer patientId,
        Integer diseaseId,
        Boolean dispensaryAccounting
    ) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteDiseaseFromPatient(Integer patientId, Integer diseaseId) {
        return null;
    }

    @Override
    public ResponseEntity<List<DiseaseFullInfo>> getDiseasesPatient(Integer patientId) {
        return null;
    }

    @Override
    public ResponseEntity<List<PatientWithDisease>> getPatientsDiseases(Integer limit, Integer offset) {
        return null;
    }

    @Override
    public ResponseEntity<List<PatientJournalNote>> getPatientMeetings(
        Integer patientId,
        Integer limit,
        Integer offset
    ) {
        return null;
    }
}
