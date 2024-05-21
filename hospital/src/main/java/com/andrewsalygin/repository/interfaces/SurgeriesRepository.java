package com.andrewsalygin.repository.interfaces;

import com.andrewsalygin.dto.TotalCostDTO;
import com.andrewsalygin.dto.medication.MedicationWithAmountDTO;
import com.andrewsalygin.dto.surgeries.DoctorSurgeryCountDTO;
import com.andrewsalygin.dto.surgeries.DoctorWithWorkingHoursDTO;
import com.andrewsalygin.dto.surgeries.SurgeryFullInfoDTO;
import com.andrewsalygin.dto.surgeries.SurgeryFullInfoWithoutIdDTO;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public interface SurgeriesRepository {

    List<SurgeryFullInfoDTO> getAllSurgeries(Integer limit, Integer offset);

    Integer createSurgery(SurgeryFullInfoWithoutIdDTO surgeryFullInfoWithoutId);

    void deleteSurgery(Integer surgeryId);

    void updateSurgery(Integer surgeryId, SurgeryFullInfoWithoutIdDTO surgeryFullInfoWithoutId);

    List<DoctorWithWorkingHoursDTO> getDoctorsForSurgery(Integer surgeryId);

    void addDoctorToSurgery(Integer surgeryId, Integer doctorId, Float workingHours, Float scheduledWorkingHours);

    void updateDoctorWorkingHours(Integer surgeryId, Integer doctorId, Float workingHours, Float scheduledWorkingHours);

    List<DoctorSurgeryCountDTO> countSurgeriesByDoctor(LocalDate startDate, LocalDate endDate);

    TotalCostDTO calculateTotalMedicationCost(Integer surgeryId);

    void removeMedicationFromSurgery(Integer surgeryId, Integer medicationId);

    void removeDoctorFromSurgery(Integer surgeryId, Integer doctorId);

    List<MedicationWithAmountDTO> getMedicationsForSurgery(Integer surgeryId);

    void addMedicationToSurgery(Integer surgeryId, Integer medicationId, Integer amount);

    void updateSurgeryDescription(Integer surgeryId, String surgicalProcedureDescription);

    List<SurgeryFullInfoDTO> getSurgeriesByPatient(Integer patientId);

    SurgeryFullInfoDTO getSurgeryById(Integer surgeryId);
}
