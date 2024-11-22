package com.andrewsalygin.controller;

import com.andrewsalygin.hospital.api.TreatmentsApi;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfoWithTreatmentDays;
import com.andrewsalygin.hospital.model.MedicationsFullInfoWithTreatmentDays;
import com.andrewsalygin.hospital.model.TreatmentFullInfo;
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
    public ResponseEntity<IdResponse> addTreatment(String treatmentName, Integer doctorId) {
        return treatmentService.addTreatment(treatmentName, doctorId);
    }

    @Override
    public ResponseEntity<Void> deleteTreatment(Integer treatmentId) {
        return treatmentService.deleteTreatment(treatmentId);
    }

    @Override
    public ResponseEntity<Void> updateTreatmentMedicalProcedure(
        Integer treatmentId,
        Integer medicalProcedureId,
        Integer amount,
        String doctorInstructions
    ) {
        return treatmentService.updateTreatmentMedicalProcedure(treatmentId, medicalProcedureId, amount, doctorInstructions);
    }

    @Override
    public ResponseEntity<Void> updateTreatmentMedication(
        Integer treatmentId,
        Integer medicationId,
        Integer amount,
        String doctorInstructions
    ) {
        return treatmentService.updateTreatmentMedication(treatmentId, medicationId, amount, doctorInstructions);
    }

    @Override
    public ResponseEntity<List<MedicalProcedureFullInfoWithTreatmentDays>> getMedicalProceduresWithTreatment(Integer treatmentId) {
        return treatmentService.getMedicalProceduresWithTreatment(treatmentId);
    }

    @Override
    public ResponseEntity<Void> addMedicalProcedureToTreatment(
        Integer treatmentId,
        Integer procedureId,
        Integer amount,
        String doctorInstructions
    ) {
        return treatmentService.addMedicalProcedureToTreatment(treatmentId, procedureId, amount, doctorInstructions);
    }

    @Override
    public ResponseEntity<List<MedicationsFullInfoWithTreatmentDays>> getMedicationsWithTreatment(Integer treatmentId) {
        return treatmentService.getMedicationsWithTreatment(treatmentId);
    }

    @Override
    public ResponseEntity<Void> addMedicationToTreatment(
        Integer treatmentId,
        Integer medicationId,
        Integer amount,
        String doctorInstructions
    ) {
        return treatmentService.addMedicationToTreatment(treatmentId, medicationId, amount, doctorInstructions);
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

    @Override
    public ResponseEntity<List<TreatmentFullInfo>> getAllTreatments(Integer limit, Integer offset) {
        return treatmentService.getAllTreatments(limit, offset);
    }

    @Override
    public ResponseEntity<List<TreatmentFullInfo>> getDoctorTreatments(
        Integer doctorId,
        Integer limit,
        Integer offset
    ) {
        return treatmentService.getDoctorTreatments(doctorId, limit, offset);
    }

    @Override
    public ResponseEntity<TreatmentFullInfo> getTreatmentById(Integer treatmentId) {
        return treatmentService.getTreatmentById(treatmentId);
    }
}
