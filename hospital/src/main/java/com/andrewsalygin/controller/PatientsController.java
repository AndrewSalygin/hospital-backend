package com.andrewsalygin.controller;

import com.andrewsalygin.hospital.api.PatientsApi;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.PatientFullInfo;
import com.andrewsalygin.hospital.model.PatientShortInfo;
import com.andrewsalygin.hospital.model.PatientWithoutId;
import com.andrewsalygin.service.PatientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class PatientsController implements PatientsApi {

    private final PatientsService patientsService;

    @Override
    public ResponseEntity<Void> attachPatient(Integer id) {
        return patientsService.attachPatient(id);
    }

    @Override
    public ResponseEntity<Void> detachPatient(Integer id) {
        return patientsService.detachPatient(id);
    }

    @Override
    public ResponseEntity<Void> editPatient(Integer id, PatientWithoutId patientWithoutId) {
        return patientsService.editPatient(id, patientWithoutId);
    }

    @Override
    public ResponseEntity<PatientFullInfo> getPatient(Integer id) {
        return patientsService.getPatient(id);
    }

    @Override
    public ResponseEntity<List<PatientShortInfo>> getPatients(Integer limit, Integer offset) {
        return patientsService.getPatients(limit, offset);
    }

    @Override
    public ResponseEntity<IdResponse> registerPatient(PatientWithoutId patientWithoutId) {
        return patientsService.registerPatient(patientWithoutId);
    }
}
