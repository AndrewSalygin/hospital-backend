package com.andrewsalygin.applicationservice.controller.patients;

import com.andrewsalygin.applicationservice.api.PatientsApi;
import com.andrewsalygin.applicationservice.model.DiseaseFullInfo;
import com.andrewsalygin.applicationservice.model.IdResponse;
import com.andrewsalygin.applicationservice.model.PatientFullInfo;
import com.andrewsalygin.applicationservice.model.PatientJournalNote;
import com.andrewsalygin.applicationservice.model.PatientShortInfo;
import com.andrewsalygin.applicationservice.model.PatientWithDisease;
import com.andrewsalygin.applicationservice.model.RecipeFullInfo;
import com.andrewsalygin.applicationservice.service.interfaces.PatientsService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PatientsController implements PatientsApi {

    private final PatientsService patientsService;

    @Override
    public ResponseEntity<List<PatientShortInfo>> getPatients(Integer limit, Integer offset) {
        return patientsService.getPatients(limit, offset);
    }

    @Override
    public ResponseEntity<PatientFullInfo> getPatient(Integer id) {
        return patientsService.getPatient(id);
    }

    @Override
    public ResponseEntity<List<RecipeFullInfo>> getRecipesPatient(Integer patientId) {
        return patientsService.getRecipesPatient(patientId);
    }

    @Override
    public ResponseEntity<IdResponse> addRecipeToPatient(
        Integer patientId,
        Integer medicationId,
        LocalDate expirationDate,
        Integer medicalHistoryNoteId
    ) {
        return patientsService.addRecipeToPatient(patientId, medicationId, expirationDate, medicalHistoryNoteId);
    }

    @Override
    public ResponseEntity<Void> addDiseaseToPatient(
        Integer patientId,
        Integer diseaseId,
        Boolean dispensaryAccounting
    ) {
        return patientsService.addDiseaseToPatient(patientId, diseaseId, dispensaryAccounting);
    }

    @Override
    public ResponseEntity<Void> deleteDiseaseFromPatient(Integer patientId, Integer diseaseId) {
        return patientsService.deleteDiseaseFromPatient(patientId, diseaseId);
    }

    @Override
    public ResponseEntity<List<DiseaseFullInfo>> getDiseasesPatient(Integer patientId) {
        return patientsService.getDiseasesPatient(patientId);
    }

    @Override
    public ResponseEntity<List<PatientWithDisease>> getPatientsDiseases(Integer limit, Integer offset) {
        return patientsService.getPatientsDiseases(limit, offset);
    }

    @Override
    public ResponseEntity<List<PatientJournalNote>> getPatientMeetings(
        Integer patientId,
        Integer limit,
        Integer offset
    ) {
        return patientsService.getPatientMeetings(patientId, limit, offset);
    }
}
