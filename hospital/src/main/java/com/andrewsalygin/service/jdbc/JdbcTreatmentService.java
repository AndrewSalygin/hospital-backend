package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfoWithTreatmentDays;
import com.andrewsalygin.hospital.model.MedicationsFullInfoWithTreatmentDays;
import com.andrewsalygin.hospital.model.TreatmentPrice;
import com.andrewsalygin.repository.interfaces.TreatmentRepository;
import com.andrewsalygin.service.interfaces.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcTreatmentService implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Override
    public ResponseEntity<IdResponse> addTreatment(Integer doctorId, Integer medicationId, Integer treatmentTime) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteTreatment(Integer treatmentId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateTreatment(Integer treatmentId, Integer treatmentTime) {
        return null;
    }

    @Override
    public ResponseEntity<List<MedicalProcedureFullInfoWithTreatmentDays>> getMedicalProceduresWithTreatment(Integer treatmentId) {
        return null;
    }

    @Override
    public ResponseEntity<IdResponse> addMedicalProcedureToTreatment(Integer treatmentId, Integer procedureId) {
        return null;
    }

    @Override
    public ResponseEntity<List<MedicationsFullInfoWithTreatmentDays>> getMedicationsWithTreatment(Integer treatmentId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addMedicationToTreatment(Integer treatmentId, Integer medicationId) {
        return null;
    }

    @Override
    public ResponseEntity<List<TreatmentPrice>> getTreatmentPrice(Integer treatmentId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteMedicalProcedureFromTreatment(Integer treatmentId, Integer procedureId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteMedicationFromTreatment(Integer treatmentId, Integer medicationId) {
        return null;
    }
}
