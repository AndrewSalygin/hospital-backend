package com.andrewsalygin.controller.patients;

import com.andrewsalygin.hospital.api.AdminApi;
import com.andrewsalygin.hospital.api.PatientsApi;
import com.andrewsalygin.hospital.model.CreateMeetingJournalNote;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.PatientWithoutId;
import org.springframework.http.ResponseEntity;

public class AdminPatientsController implements AdminApi, PatientsApi {

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
    public ResponseEntity<IdResponse> addPatientNote(CreateMeetingJournalNote createMeetingJournalNote) {
        return PatientsApi.super.addPatientNote(createMeetingJournalNote);
    }

    @Override
    public ResponseEntity<Void> deletePatientNote(Integer medicalHistoryNoteId) {
        return PatientsApi.super.deletePatientNote(medicalHistoryNoteId);
    }

    @Override
    public ResponseEntity<Void> changePatientNote(
        Integer medicalHistoryNoteId,
        CreateMeetingJournalNote createMeetingJournalNote
    ) {
        return PatientsApi.super.changePatientNote(medicalHistoryNoteId, createMeetingJournalNote);
    }
}
