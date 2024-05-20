package com.andrewsalygin.repository.interfaces;

import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureFullInfoWithTreatmentDaysDTO;
import com.andrewsalygin.dto.medication.MedicationsFullInfoWithTreatmentDaysDTO;
import com.andrewsalygin.dto.treatment.TreatmentPriceDTO;
import java.util.List;

public interface TreatmentRepository {

    Integer addTreatment(Integer doctorId, Integer medicationId, Integer treatmentTime);

    void deleteTreatment(Integer treatmentId);

    void updateTreatment(Integer treatmentId, Integer treatmentTime);

    List<MedicalProcedureFullInfoWithTreatmentDaysDTO> getMedicalProceduresWithTreatment(Integer treatmentId);

    Integer addMedicalProcedureToTreatment(Integer treatmentId, Integer procedureId);

    List<MedicationsFullInfoWithTreatmentDaysDTO> getMedicationsWithTreatment(Integer treatmentId);

    void addMedicationToTreatment(Integer treatmentId, Integer medicationId);

    List<TreatmentPriceDTO> getTreatmentPrice(Integer treatmentId);

    void deleteMedicalProcedureFromTreatment(Integer treatmentId, Integer procedureId);

    void deleteMedicationFromTreatment(Integer treatmentId, Integer medicationId);
}
