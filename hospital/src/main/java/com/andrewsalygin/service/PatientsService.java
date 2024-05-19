package com.andrewsalygin.service;

import com.andrewsalygin.hospital.model.*;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.List;

public interface PatientsService {

    ResponseEntity<Void> attachPatient(Integer id);

    ResponseEntity<Void> detachPatient(Integer id);

    ResponseEntity<Void> editPatient(Integer id, PatientWithoutId patientWithoutId);

    ResponseEntity<PatientFullInfo> getPatient(Integer id);

    ResponseEntity<List<PatientShortInfo>> getPatients(Integer limit, Integer offset);

    ResponseEntity<IdResponse> registerPatient(PatientWithoutId patientWithoutId);

    ResponseEntity<Void> deletePatient(Integer id);

    ResponseEntity<IdResponse> addPatientNote(CreatePatientJournalNote createPatientJournalNote);

    ResponseEntity<Void> deletePatientNote(Integer medicalHistoryNoteId);

    ResponseEntity<Void> changePatientNote(Integer medicalHistoryNoteId, CreatePatientJournalNote createPatientJournalNote);

    ResponseEntity<List<RecipeFullInfo>> getRecipesPatient(Integer patientId);

    ResponseEntity<IdResponse> addRecipeToPatient(Integer patientId, Integer medicationId, LocalDate expirationDate, Integer medicalHistoryNoteId);

    ResponseEntity<Void> addDiseaseToPatient(Integer patientId, Integer diseaseId, Boolean dispensaryAccounting);

    ResponseEntity<Void> deleteDiseaseFromPatient(Integer patientId, Integer diseaseId);

    ResponseEntity<List<DiseaseFullInfo>> getDiseasesPatient(Integer patientId);

    ResponseEntity<List<PatientWithDisease>> getPatientsDiseases(Integer limit, Integer offset);

    ResponseEntity<List<PatientJournalNote>> getPatientMeetings(Integer patientId, Integer limit, Integer offset);
}
