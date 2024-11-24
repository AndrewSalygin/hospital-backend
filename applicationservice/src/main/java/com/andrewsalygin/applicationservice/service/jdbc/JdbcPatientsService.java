package com.andrewsalygin.applicationservice.service.jdbc;

import com.andrewsalygin.applicationservice.dto.disease.DiseaseFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientShortInfoDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientWithDiseaseDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientWithoutIdDTO;
import com.andrewsalygin.applicationservice.dto.patientJournal.PatientJournalNoteDTO;
import com.andrewsalygin.applicationservice.dto.recipe.RecipeFullInfoDTO;
import com.andrewsalygin.applicationservice.model.CreatePatientJournalNote;
import com.andrewsalygin.applicationservice.model.DiseaseFullInfo;
import com.andrewsalygin.applicationservice.model.IdResponse;
import com.andrewsalygin.applicationservice.model.PatientFullInfo;
import com.andrewsalygin.applicationservice.model.PatientJournalNote;
import com.andrewsalygin.applicationservice.model.PatientShortInfo;
import com.andrewsalygin.applicationservice.model.PatientWithDisease;
import com.andrewsalygin.applicationservice.model.PatientWithoutId;
import com.andrewsalygin.applicationservice.model.RecipeFullInfo;
import com.andrewsalygin.applicationservice.repository.interfaces.PatientRepository;
import com.andrewsalygin.applicationservice.service.interfaces.PatientsService;
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
@Transactional
@RequiredArgsConstructor
public class JdbcPatientsService implements PatientsService {

    private final PatientRepository patientRepository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<Void> attachPatient(Integer id) {
        patientRepository.attachPatient(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> detachPatient(Integer id) {
        patientRepository.detachPatient(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> editPatient(Integer id, PatientWithoutId patientWithoutId) {
        PatientWithoutIdDTO patient = modelMapper.map(patientWithoutId, PatientWithoutIdDTO.class);
        patientRepository.editPatient(id, patient);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PatientFullInfo> getPatient(Integer id) {
        PatientFullInfoDTO patient = patientRepository.getPatient(id);

        PatientFullInfo patientFullInfo = modelMapper.map(patient, PatientFullInfo.class);
        return new ResponseEntity<>(patientFullInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PatientShortInfo>> getPatients(Integer limit, Integer offset) {
        List<PatientShortInfoDTO> patients = patientRepository.getPatients(limit, offset);

        Type listType = new TypeToken<List<PatientShortInfo>>() {
        }.getType();
        List<PatientShortInfo> patientShortInfos = modelMapper.map(patients, listType);

        return new ResponseEntity<>(patientShortInfos, HttpStatus.OK);
    }

    @Override
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
    public ResponseEntity<IdResponse> addPatientNote(CreatePatientJournalNote createPatientJournalNote) {
        if (createPatientJournalNote.getInitialAdmission() && createPatientJournalNote.getDischarge()) {
            throw new RuntimeException("Пациент должен или выписываться, или иметь первоначальный приём");
        }
        Integer resultFromRepository = patientRepository.addPatientNote(createPatientJournalNote);

        IdResponse idResponse = new IdResponse();
        idResponse.setId(resultFromRepository);

        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletePatientNote(Integer medicalHistoryNoteId) {
        patientRepository.deletePatientNote(medicalHistoryNoteId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> changePatientNote(
        Integer medicalHistoryNoteId,
        CreatePatientJournalNote createPatientJournalNote
    ) {
        patientRepository.changePatientNote(medicalHistoryNoteId, createPatientJournalNote);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RecipeFullInfo>> getRecipesPatient(Integer patientId) {
        List<RecipeFullInfoDTO> resultFromRepository = patientRepository.getRecipesPatient(patientId);

        Type listType = new TypeToken<List<RecipeFullInfo>>() {
        }.getType();
        List<RecipeFullInfo> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<IdResponse> addRecipeToPatient(
        Integer patientId,
        Integer medicationId,
        LocalDate expirationDate,
        Integer medicalHistoryNoteId
    ) {
        IdResponse idResponse = new IdResponse();
        idResponse.setId(patientRepository.addRecipeToPatient(
            patientId,
            medicationId,
            expirationDate,
            medicalHistoryNoteId
        ));
        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addDiseaseToPatient(
        Integer patientId,
        Integer diseaseId,
        Boolean dispensaryAccounting
    ) {
        patientRepository.addDiseaseToPatient(patientId, diseaseId, dispensaryAccounting);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteDiseaseFromPatient(Integer patientId, Integer diseaseId) {
        patientRepository.deleteDiseaseFromPatient(patientId, diseaseId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DiseaseFullInfo>> getDiseasesPatient(Integer patientId) {
        List<DiseaseFullInfoDTO> resultFromRepository = patientRepository.getDiseasesPatient(patientId);

        Type listType = new TypeToken<List<DiseaseFullInfo>>() {
        }.getType();
        List<DiseaseFullInfo> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PatientWithDisease>> getPatientsDiseases(Integer limit, Integer offset) {
        List<PatientWithDiseaseDTO> resultFromRepository = patientRepository.getPatientsDiseases(limit, offset);

        Type listType = new TypeToken<List<PatientWithDisease>>() {
        }.getType();
        List<PatientWithDisease> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<PatientJournalNote>> getPatientMeetings(
        Integer patientId,
        Integer limit,
        Integer offset
    ) {
        List<PatientJournalNoteDTO> resultFromRepository = patientRepository.getPatientMeetings(patientId, limit, offset);

        Type listType = new TypeToken<List<PatientJournalNote>>() {
        }.getType();
        List<PatientJournalNote> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
