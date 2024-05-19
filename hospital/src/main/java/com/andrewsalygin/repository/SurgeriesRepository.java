package com.andrewsalygin.repository;

import com.andrewsalygin.dto.surgeries.DoctorSurgeryCountDTO;
import com.andrewsalygin.dto.surgeries.DoctorWithWorkingHoursDTO;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.dto.medication.MedicationWithAmountDTO;
import com.andrewsalygin.dto.surgeries.SurgeryFullInfoDTO;
import com.andrewsalygin.dto.surgeries.SurgeryFullInfoWithoutIdDTO;
import com.andrewsalygin.dto.TotalCostDTO;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface SurgeriesRepository {

    ResponseEntity<List<SurgeryFullInfoDTO>> getAllSurgeries(Integer limit, Integer offset);

    ResponseEntity<IdResponse> createSurgery(SurgeryFullInfoWithoutIdDTO surgeryFullInfoWithoutId);

    ResponseEntity<Void> deleteSurgery(Integer surgeryId);

    ResponseEntity<Void> updateSurgery(Integer surgeryId, SurgeryFullInfoWithoutIdDTO surgeryFullInfoWithoutId);

    ResponseEntity<List<DoctorWithWorkingHoursDTO>> getDoctorsForSurgery(Integer surgeryId);

    ResponseEntity<IdResponse> addDoctorToSurgery(Integer surgeryId, Integer doctorId, Float workingHours, Float scheduledWorkingHours);

    ResponseEntity<Void> updateDoctorWorkingHours(Integer surgeryId, Integer doctorId, Float workingHours, Float scheduledWorkingHours);

    ResponseEntity<List<DoctorSurgeryCountDTO>> countSurgeriesByDoctor(OffsetDateTime startDate, OffsetDateTime endDate);

    ResponseEntity<TotalCostDTO> calculateTotalMedicationCost(Integer surgeryId);

    ResponseEntity<Void> removeMedicationFromSurgery(Integer surgeryId, Integer medicationId);

    ResponseEntity<Void> removeDoctorFromSurgery(Integer surgeryId, Integer doctorId);

    ResponseEntity<List<MedicationWithAmountDTO>> getMedicationsForSurgery(Integer surgeryId);

    ResponseEntity<Void> addMedicationToSurgery(Integer surgeryId, Integer medicationId, Integer amount);

    ResponseEntity<Void> updateSurgeryDescription(Integer surgeryId, String surgicalProcedureDescription);

    ResponseEntity<List<SurgeryFullInfoDTO>> getSurgeriesByPatient(Integer patientId);

    ResponseEntity<SurgeryFullInfoDTO> getSurgeryById(Integer surgeryId);
}
