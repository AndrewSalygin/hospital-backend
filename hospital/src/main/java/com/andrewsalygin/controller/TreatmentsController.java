package com.andrewsalygin.controller;

import com.andrewsalygin.hospital.api.TreatmentsApi;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfoWithTreatmentDays;
import com.andrewsalygin.hospital.model.MedicationsFullInfoWithTreatmentDays;
import com.andrewsalygin.hospital.model.TreatmentPrice;
import com.andrewsalygin.service.interfaces.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class TreatmentsController implements TreatmentsApi {

    private final TreatmentService treatmentService;

    @Override
    public ResponseEntity<IdResponse> addTreatment(Integer doctorId, Integer medicationId, Integer treatmentTime) {
        return treatmentService.addTreatment(doctorId, medicationId, treatmentTime);
    }

    @Override
    public ResponseEntity<Void> deleteTreatment(Integer treatmentId) {
        return treatmentService.deleteTreatment(treatmentId);
    }

    @Override
    public ResponseEntity<Void> updateTreatment(Integer treatmentId, Integer treatmentTime) {
        return treatmentService.updateTreatment(treatmentId, treatmentTime);
    }

    @Override
    public ResponseEntity<List<MedicalProcedureFullInfoWithTreatmentDays>> getMedicalProceduresWithTreatment(Integer treatmentId) {
        return treatmentService.getMedicalProceduresWithTreatment(treatmentId);
    }

    @Override
    public ResponseEntity<IdResponse> addMedicalProcedureToTreatment(Integer treatmentId, Integer procedureId) {
        return treatmentService.addMedicalProcedureToTreatment(treatmentId, procedureId);
    }

    @Override
    public ResponseEntity<List<MedicationsFullInfoWithTreatmentDays>> getMedicationsWithTreatment(Integer treatmentId) {
        return treatmentService.getMedicationsWithTreatment(treatmentId);
    }

    @Override
    public ResponseEntity<Void> addMedicationToTreatment(Integer treatmentId, Integer medicationId) {
        return treatmentService.addMedicationToTreatment(treatmentId, medicationId);
    }

    @Override
    public ResponseEntity<List<TreatmentPrice>> getTreatmentPrice(Integer treatmentId) {
        return treatmentService.getTreatmentPrice(treatmentId);
    }

    @Override
    public ResponseEntity<Void> deleteMedicalProcedureFromTreatment(Integer treatmentId, Integer procedureId) {
        return treatmentService.deleteMedicalProcedureFromTreatment(treatmentId, procedureId);
    }

    @Override
    public ResponseEntity<Void> deleteMedicationFromTreatment(Integer treatmentId, Integer medicationId) {
        return treatmentService.deleteMedicationFromTreatment(treatmentId, medicationId);
    }
}
