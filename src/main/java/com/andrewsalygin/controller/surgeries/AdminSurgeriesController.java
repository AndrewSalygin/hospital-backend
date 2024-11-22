package com.andrewsalygin.controller.surgeries;

import com.andrewsalygin.hospital.api.AdminSurgeriesApi;
import com.andrewsalygin.hospital.model.DoctorSurgeryCount;
import com.andrewsalygin.hospital.model.DoctorWithWorkingHours;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.SurgeryFullInfoWithoutId;
import com.andrewsalygin.hospital.model.TotalCost;
import com.andrewsalygin.service.interfaces.SurgeriesService;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AdminSurgeriesController implements AdminSurgeriesApi {

    private final SurgeriesService surgeriesService;

    @Override
    public ResponseEntity<Void> addMedicationToSurgery(Integer surgeryId, Integer medicationId, Integer amount) {
        return surgeriesService.addMedicationToSurgery(surgeryId, medicationId, amount);
    }

    @Override
    public ResponseEntity<IdResponse> createSurgery(SurgeryFullInfoWithoutId surgeryFullInfoWithoutId) {
        return surgeriesService.createSurgery(surgeryFullInfoWithoutId);
    }

    @Override
    public ResponseEntity<Void> deleteSurgery(Integer surgeryId) {
        return surgeriesService.deleteSurgery(surgeryId);
    }

    @Override
    public ResponseEntity<Void> updateSurgery(Integer surgeryId, SurgeryFullInfoWithoutId surgeryFullInfoWithoutId) {
        return surgeriesService.updateSurgery(surgeryId, surgeryFullInfoWithoutId);
    }

    @Override
    public ResponseEntity<List<DoctorWithWorkingHours>> getDoctorsForSurgery(Integer surgeryId) {
        return surgeriesService.getDoctorsForSurgery(surgeryId);
    }

    @Override
    public ResponseEntity<Void> addDoctorToSurgery(
        Integer surgeryId,
        Integer doctorId,
        Float workingHours,
        Float scheduledWorkingHours
    ) {
        return surgeriesService.addDoctorToSurgery(surgeryId, doctorId, workingHours, scheduledWorkingHours);
    }

    @Override
    public ResponseEntity<Void> updateDoctorWorkingHours(
        Integer surgeryId,
        Integer doctorId,
        Float workingHours,
        Float scheduledWorkingHours
    ) {
        return surgeriesService.updateDoctorWorkingHours(surgeryId, doctorId, workingHours, scheduledWorkingHours);
    }

    @Override
    public ResponseEntity<List<DoctorSurgeryCount>> countSurgeriesByDoctor(
        LocalDate startDate,
        LocalDate endDate
    ) {
        return surgeriesService.countSurgeriesByDoctor(startDate, endDate);
    }

    @Override
    public ResponseEntity<TotalCost> calculateTotalMedicationCost(Integer surgeryId) {
        return surgeriesService.calculateTotalMedicationCost(surgeryId);
    }

    @Override
    public ResponseEntity<Void> removeMedicationFromSurgery(Integer surgeryId, Integer medicationId) {
        return surgeriesService.removeMedicationFromSurgery(surgeryId, medicationId);
    }

    @Override
    public ResponseEntity<Void> removeDoctorFromSurgery(Integer surgeryId, Integer doctorId) {
        return surgeriesService.removeDoctorFromSurgery(surgeryId, doctorId);
    }
}
