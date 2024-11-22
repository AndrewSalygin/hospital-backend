package com.andrewsalygin.controller.patients;

import com.andrewsalygin.hospital.api.AdminPatientsApi;
import com.andrewsalygin.hospital.model.CreatePatientJournalNote;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.PatientWithoutId;
import com.andrewsalygin.service.interfaces.PatientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AdminPatientsController implements AdminPatientsApi {

    private final PatientsService patientsService;

    @Override
    public ResponseEntity<IdResponse> registerPatient(PatientWithoutId patientWithoutId) {
        return patientsService.registerPatient(patientWithoutId);
    }

    @Override
    public ResponseEntity<Void> editPatient(Integer id, PatientWithoutId patientWithoutId) {
        return patientsService.editPatient(id, patientWithoutId);
    }

    @Override
    public ResponseEntity<Void> detachPatient(Integer id) {
        return patientsService.detachPatient(id);
    }

    @Override
    public ResponseEntity<Void> attachPatient(Integer id) {
        return patientsService.attachPatient(id);
    }

    @Override
    public ResponseEntity<IdResponse> addPatientNote(CreatePatientJournalNote createPatientJournalNote) {
        return patientsService.addPatientNote(createPatientJournalNote);
    }

    @Override
    public ResponseEntity<Void> deletePatientNote(Integer medicalHistoryNoteId) {
        return patientsService.deletePatientNote(medicalHistoryNoteId);
    }

    @Override
    public ResponseEntity<Void> changePatientNote(
        Integer medicalHistoryNoteId,
        CreatePatientJournalNote createPatientJournalNote
    ) {
        return patientsService.changePatientNote(medicalHistoryNoteId, createPatientJournalNote);
    }
}
