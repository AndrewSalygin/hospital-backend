package com.andrewsalygin.service;

import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.PatientFullInfo;
import com.andrewsalygin.hospital.model.PatientShortInfo;
import com.andrewsalygin.hospital.model.PatientWithoutId;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface PatientsService {

    ResponseEntity<Void> attachPatient(Integer id);

    ResponseEntity<Void> detachPatient(Integer id);

    ResponseEntity<Void> editPatient(Integer id, PatientWithoutId patientWithoutId);

    ResponseEntity<PatientFullInfo> getPatient(Integer id);

    ResponseEntity<List<PatientShortInfo>> getPatients(Integer limit, Integer offset);

    ResponseEntity<IdResponse> registerPatient(PatientWithoutId patientWithoutId);
}
