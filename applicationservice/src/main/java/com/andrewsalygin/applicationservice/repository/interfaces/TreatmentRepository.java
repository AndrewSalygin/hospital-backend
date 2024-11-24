package com.andrewsalygin.applicationservice.repository.interfaces;

import com.andrewsalygin.applicationservice.dto.medicalProcedure.MedicalProcedureFullInfoWithTreatmentDaysDTO;
import com.andrewsalygin.applicationservice.dto.medication.MedicationsFullInfoWithTreatmentDaysDTO;
import com.andrewsalygin.applicationservice.dto.treatment.TreatmentFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.treatment.TreatmentPriceDTO;
import java.util.List;

public interface TreatmentRepository {

    Integer addTreatment(String treatmentName, Integer doctorId);

    void deleteTreatment(Integer treatmentId);

    List<MedicalProcedureFullInfoWithTreatmentDaysDTO> getMedicalProceduresWithTreatment(Integer treatmentId);

    void addMedicalProcedureToTreatment(
        Integer treatmentId,
        Integer procedureId,
        Integer amount,
        String doctorInstructions
    );

    List<MedicationsFullInfoWithTreatmentDaysDTO> getMedicationsWithTreatment(Integer treatmentId);

    void addMedicationToTreatment(Integer treatmentId, Integer medicationId, Integer amount, String doctorInstructions);

    List<TreatmentPriceDTO> getTreatmentPrice(Integer treatmentId);

    void deleteMedicalProcedureFromTreatment(Integer treatmentId, Integer procedureId);

    void deleteMedicationFromTreatment(Integer treatmentId, Integer medicationId);

    void updateTreatmentMedicalProcedure(
        Integer treatmentId,
        Integer medicalProcedureId,
        Integer amount,
        String doctorInstructions
    );

    void updateTreatmentMedication(
        Integer treatmentId,
        Integer medicationId,
        Integer amount,
        String doctorInstructions
    );

    List<TreatmentFullInfoDTO> getAllTreatments(Integer limit, Integer offset);

    List<TreatmentFullInfoDTO> getDoctorTreatments(Integer doctorId, Integer limit, Integer offset);

    TreatmentFullInfoDTO getTreatmentById(Integer treatmentId);
}
