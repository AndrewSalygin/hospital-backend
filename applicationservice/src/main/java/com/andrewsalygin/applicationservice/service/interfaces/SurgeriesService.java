package com.andrewsalygin.applicationservice.service.interfaces;

import com.andrewsalygin.applicationservice.model.DoctorSurgeryCount;
import com.andrewsalygin.applicationservice.model.DoctorWithWorkingHours;
import com.andrewsalygin.applicationservice.model.IdResponse;
import com.andrewsalygin.applicationservice.model.MedicationWithAmount;
import com.andrewsalygin.applicationservice.model.SurgeryFullInfo;
import com.andrewsalygin.applicationservice.model.SurgeryFullInfoWithoutId;
import com.andrewsalygin.applicationservice.model.TotalCost;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public interface SurgeriesService {

    ResponseEntity<List<SurgeryFullInfo>> getAllSurgeries(Integer limit, Integer offset);

    ResponseEntity<IdResponse> createSurgery(SurgeryFullInfoWithoutId surgeryFullInfoWithoutId);

    ResponseEntity<Void> deleteSurgery(Integer surgeryId);

    ResponseEntity<Void> updateSurgery(Integer surgeryId, SurgeryFullInfoWithoutId surgeryFullInfoWithoutId);

    ResponseEntity<List<DoctorWithWorkingHours>> getDoctorsForSurgery(Integer surgeryId);

    ResponseEntity<Void> addDoctorToSurgery(Integer surgeryId, Integer doctorId, Float workingHours, Float scheduledWorkingHours);

    ResponseEntity<Void> updateDoctorWorkingHours(Integer surgeryId, Integer doctorId, Float workingHours, Float scheduledWorkingHours);

    ResponseEntity<List<DoctorSurgeryCount>> countSurgeriesByDoctor(LocalDate startDate, LocalDate endDate);

    ResponseEntity<TotalCost> calculateTotalMedicationCost(Integer surgeryId);

    ResponseEntity<Void> removeMedicationFromSurgery(Integer surgeryId, Integer medicationId);

    ResponseEntity<Void> removeDoctorFromSurgery(Integer surgeryId, Integer doctorId);

    ResponseEntity<List<MedicationWithAmount>> getMedicationsForSurgery(Integer surgeryId);

    ResponseEntity<Void> addMedicationToSurgery(Integer surgeryId, Integer medicationId, Integer amount);

    ResponseEntity<Void> updateSurgeryDescription(Integer surgeryId, String surgicalProcedureDescription);

    ResponseEntity<List<SurgeryFullInfo>> getSurgeriesByPatient(Integer patientId);

    ResponseEntity<SurgeryFullInfo> getSurgeryById(Integer surgeryId);
}
