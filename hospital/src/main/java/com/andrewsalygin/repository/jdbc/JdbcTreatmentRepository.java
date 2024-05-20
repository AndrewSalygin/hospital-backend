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
    public Integer addTreatment(Integer doctorId, Integer medicationId, Integer treatmentTime) {
        return 0;
    }

    @Override
    public void deleteTreatment(Integer treatmentId) {

    }

    @Override
    public void updateTreatment(Integer treatmentId, Integer treatmentTime) {

    }

    @Override
    public List<MedicalProcedureFullInfoWithTreatmentDaysDTO> getMedicalProceduresWithTreatment(Integer treatmentId) {
        return List.of();
    }

    @Override
    public Integer addMedicalProcedureToTreatment(Integer treatmentId, Integer procedureId) {
        return 0;
    }

    @Override
    public List<MedicationsFullInfoWithTreatmentDaysDTO> getMedicationsWithTreatment(Integer treatmentId) {
        return List.of();
    }

    @Override
    public void addMedicationToTreatment(Integer treatmentId, Integer medicationId) {

    }

    @Override
    public List<TreatmentPriceDTO> getTreatmentPrice(Integer treatmentId) {
        return List.of();
    }

    @Override
    public void deleteMedicalProcedureFromTreatment(Integer treatmentId, Integer procedureId) {

    }

    @Override
    public void deleteMedicationFromTreatment(Integer treatmentId, Integer medicationId) {

    }
}
