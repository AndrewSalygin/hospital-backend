package com.andrewsalygin.service.interfaces;

import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfoWithTreatmentDays;
import com.andrewsalygin.hospital.model.MedicationsFullInfoWithTreatmentDays;
import com.andrewsalygin.hospital.model.TreatmentPrice;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface TreatmentService {

    ResponseEntity<IdResponse> addTreatment(Integer doctorId, Integer medicationId, Integer treatmentTime);

    ResponseEntity<Void> deleteTreatment(Integer treatmentId);

    ResponseEntity<Void> updateTreatment(Integer treatmentId, Integer treatmentTime);

    ResponseEntity<List<MedicalProcedureFullInfoWithTreatmentDays>> getMedicalProceduresWithTreatment(Integer treatmentId);

    ResponseEntity<IdResponse> addMedicalProcedureToTreatment(Integer treatmentId, Integer procedureId);

    ResponseEntity<List<MedicationsFullInfoWithTreatmentDays>> getMedicationsWithTreatment(Integer treatmentId);

    ResponseEntity<Void> addMedicationToTreatment(Integer treatmentId, Integer medicationId);

    ResponseEntity<List<TreatmentPrice>> getTreatmentPrice(Integer treatmentId);

    ResponseEntity<Void> deleteMedicalProcedureFromTreatment(Integer treatmentId, Integer procedureId);

    ResponseEntity<Void> deleteMedicationFromTreatment(Integer treatmentId, Integer medicationId);
}
