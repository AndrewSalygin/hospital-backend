package com.andrewsalygin.repository;

import com.andrewsalygin.dto.patient.Patient;
import com.andrewsalygin.dto.patient.PatientShort;
import com.andrewsalygin.dto.patient.PatientWithoutIdDTO;
import java.util.List;

public interface PatientRepository {

    void attachPatient(Integer id);

    void detachPatient(Integer id);

    void editPatient(Integer id, PatientWithoutIdDTO patientWithoutId);

    Patient getPatient(Integer id);

    List<PatientShort> getPatients(Integer limit, Integer offset);

    Integer registerPatient(PatientWithoutIdDTO patientWithoutId);

    void deletePatient(Integer id);
}
