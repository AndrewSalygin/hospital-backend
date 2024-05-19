package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureFullInfoWithTreatmentDaysDTO;
import com.andrewsalygin.dto.medication.MedicationsFullInfoWithTreatmentDaysDTO;
import com.andrewsalygin.dto.treatment.TreatmentPriceDTO;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.repository.interfaces.TreatmentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcTreatmentRepository implements TreatmentRepository {

    private final JdbcClient client;

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
    public ResponseEntity<List<MedicalProcedureFullInfoWithTreatmentDaysDTO>> getMedicalProceduresWithTreatment(Integer treatmentId) {
        return null;
    }

    @Override
    public ResponseEntity<IdResponse> addMedicalProcedureToTreatment(Integer treatmentId, Integer procedureId) {
        return null;
    }

    @Override
    public ResponseEntity<List<MedicationsFullInfoWithTreatmentDaysDTO>> getMedicationsWithTreatment(Integer treatmentId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addMedicationToTreatment(Integer treatmentId, Integer medicationId) {
        return null;
    }

    @Override
    public ResponseEntity<List<TreatmentPriceDTO>> getTreatmentPrice(Integer treatmentId) {
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
