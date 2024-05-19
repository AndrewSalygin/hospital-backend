package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.DoctorSurgeryCount;
import com.andrewsalygin.hospital.model.DoctorWithWorkingHours;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicationWithAmount;
import com.andrewsalygin.hospital.model.SurgeryFullInfo;
import com.andrewsalygin.hospital.model.SurgeryFullInfoWithoutId;
import com.andrewsalygin.hospital.model.TotalCost;
import com.andrewsalygin.repository.SurgeriesRepository;
import com.andrewsalygin.service.SurgeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcSurgeriesService implements SurgeriesService {

    private final SurgeriesRepository surgeriesRepository;

    @Override
    public ResponseEntity<List<SurgeryFullInfo>> getAllSurgeries(Integer limit, Integer offset) {
        return null;
    }

    @Override
    public ResponseEntity<IdResponse> createSurgery(SurgeryFullInfoWithoutId surgeryFullInfoWithoutId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteSurgery(Integer surgeryId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateSurgery(Integer surgeryId, SurgeryFullInfoWithoutId surgeryFullInfoWithoutId) {
        return null;
    }

    @Override
    public ResponseEntity<List<DoctorWithWorkingHours>> getDoctorsForSurgery(Integer surgeryId) {
        return null;
    }

    @Override
    public ResponseEntity<IdResponse> addDoctorToSurgery(
        Integer surgeryId,
        Integer doctorId,
        Float workingHours,
        Float scheduledWorkingHours
    ) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateDoctorWorkingHours(
        Integer surgeryId,
        Integer doctorId,
        Float workingHours,
        Float scheduledWorkingHours
    ) {
        return null;
    }

    @Override
    public ResponseEntity<List<DoctorSurgeryCount>> countSurgeriesByDoctor(
        OffsetDateTime startDate,
        OffsetDateTime endDate
    ) {
        return null;
    }

    @Override
    public ResponseEntity<TotalCost> calculateTotalMedicationCost(Integer surgeryId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> removeMedicationFromSurgery(Integer surgeryId, Integer medicationId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> removeDoctorFromSurgery(Integer surgeryId, Integer doctorId) {
        return null;
    }

    @Override
    public ResponseEntity<List<MedicationWithAmount>> getMedicationsForSurgery(Integer surgeryId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addMedicationToSurgery(Integer surgeryId, Integer medicationId, Integer amount) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateSurgeryDescription(Integer surgeryId, String surgicalProcedureDescription) {
        return null;
    }

    @Override
    public ResponseEntity<List<SurgeryFullInfo>> getSurgeriesByPatient(Integer patientId) {
        return null;
    }

    @Override
    public ResponseEntity<SurgeryFullInfo> getSurgeryById(Integer surgeryId) {
        return null;
    }
}
