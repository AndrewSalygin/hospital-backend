package com.andrewsalygin.applicationservice.service.interfaces;

import com.andrewsalygin.applicationservice.model.IdResponse;
import com.andrewsalygin.applicationservice.model.MedicalProcedureFullInfoWithTreatmentDays;
import com.andrewsalygin.applicationservice.model.MedicationsFullInfoWithTreatmentDays;
import com.andrewsalygin.applicationservice.model.TreatmentFullInfo;
import com.andrewsalygin.applicationservice.model.TreatmentPrice;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface TreatmentService {

    ResponseEntity<IdResponse> addTreatment(String treatmentName, Integer doctorId);

    ResponseEntity<Void> deleteTreatment(Integer treatmentId);

    ResponseEntity<List<MedicalProcedureFullInfoWithTreatmentDays>> getMedicalProceduresWithTreatment(Integer treatmentId);

    ResponseEntity<Void> addMedicalProcedureToTreatment(
        Integer treatmentId,
        Integer procedureId,
        Integer amount,
        String doctorInstructions);

    ResponseEntity<List<MedicationsFullInfoWithTreatmentDays>> getMedicationsWithTreatment(Integer treatmentId);

    ResponseEntity<Void> addMedicationToTreatment(
        Integer treatmentId,
        Integer medicationId,
        Integer amount,
        String doctorInstructions
    );


    ResponseEntity<List<TreatmentPrice>> getTreatmentPrice(Integer treatmentId);

    ResponseEntity<Void> deleteMedicalProcedureFromTreatment(Integer treatmentId, Integer procedureId);

    ResponseEntity<Void> deleteMedicationFromTreatment(Integer treatmentId, Integer medicationId);

    ResponseEntity<Void> updateTreatmentMedicalProcedure(
        Integer treatmentId,
        Integer medicalProcedureId,
        Integer amount,
        String doctorInstructions
    );

    ResponseEntity<Void> updateTreatmentMedication(
        Integer treatmentId,
        Integer medicationId,
        Integer amount,
        String doctorInstructions
    );

    ResponseEntity<List<TreatmentFullInfo>> getAllTreatments(Integer limit, Integer offset);

    ResponseEntity<List<TreatmentFullInfo>> getDoctorTreatments(Integer doctorId, Integer limit, Integer offset);

    ResponseEntity<TreatmentFullInfo> getTreatmentById(Integer treatmentId);
}
