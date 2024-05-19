package com.andrewsalygin.repository;

import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureFullInfoWithTreatmentDaysDTO;
import com.andrewsalygin.dto.medication.MedicationsFullInfoWithTreatmentDaysDTO;
import com.andrewsalygin.dto.treatment.TreatmentPriceDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface TreatmentRepository {

    ResponseEntity<IdResponse> addTreatment(Integer doctorId, Integer medicationId, Integer treatmentTime);

    ResponseEntity<Void> deleteTreatment(Integer treatmentId);

    ResponseEntity<Void> updateTreatment(Integer treatmentId, Integer treatmentTime);

    ResponseEntity<List<MedicalProcedureFullInfoWithTreatmentDaysDTO>> getMedicalProceduresWithTreatment(Integer treatmentId);

    ResponseEntity<IdResponse> addMedicalProcedureToTreatment(Integer treatmentId, Integer procedureId);

    ResponseEntity<List<MedicationsFullInfoWithTreatmentDaysDTO>> getMedicationsWithTreatment(Integer treatmentId);

    ResponseEntity<Void> addMedicationToTreatment(Integer treatmentId, Integer medicationId);

    ResponseEntity<List<TreatmentPriceDTO>> getTreatmentPrice(Integer treatmentId);

    ResponseEntity<Void> deleteMedicalProcedureFromTreatment(Integer treatmentId, Integer procedureId);

    ResponseEntity<Void> deleteMedicationFromTreatment(Integer treatmentId, Integer medicationId);
}
