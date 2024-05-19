package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.TotalCostDTO;
import com.andrewsalygin.dto.medication.MedicationWithAmountDTO;
import com.andrewsalygin.dto.surgeries.DoctorSurgeryCountDTO;
import com.andrewsalygin.dto.surgeries.DoctorWithWorkingHoursDTO;
import com.andrewsalygin.dto.surgeries.SurgeryFullInfoDTO;
import com.andrewsalygin.dto.surgeries.SurgeryFullInfoWithoutIdDTO;
import com.andrewsalygin.hospital.model.DoctorSurgeryCount;
import com.andrewsalygin.hospital.model.DoctorWithWorkingHours;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicationWithAmount;
import com.andrewsalygin.hospital.model.SurgeryFullInfo;
import com.andrewsalygin.hospital.model.SurgeryFullInfoWithoutId;
import com.andrewsalygin.hospital.model.TotalCost;
import com.andrewsalygin.repository.SurgeriesRepository;
import com.andrewsalygin.service.SurgeriesService;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcSurgeriesRepository implements SurgeriesRepository {

    private final JdbcClient client;

    @Override
    public ResponseEntity<List<SurgeryFullInfoDTO>> getAllSurgeries(Integer limit, Integer offset) {
        return null;
    }

    @Override
    public ResponseEntity<IdResponse> createSurgery(SurgeryFullInfoWithoutIdDTO surgeryFullInfoWithoutId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteSurgery(Integer surgeryId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateSurgery(Integer surgeryId, SurgeryFullInfoWithoutIdDTO surgeryFullInfoWithoutId) {
        return null;
    }

    @Override
    public ResponseEntity<List<DoctorWithWorkingHoursDTO>> getDoctorsForSurgery(Integer surgeryId) {
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
    public ResponseEntity<List<DoctorSurgeryCountDTO>> countSurgeriesByDoctor(
        OffsetDateTime startDate,
        OffsetDateTime endDate
    ) {
        return null;
    }

    @Override
    public ResponseEntity<TotalCostDTO> calculateTotalMedicationCost(Integer surgeryId) {
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
    public ResponseEntity<List<MedicationWithAmountDTO>> getMedicationsForSurgery(Integer surgeryId) {
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
    public ResponseEntity<List<SurgeryFullInfoDTO>> getSurgeriesByPatient(Integer patientId) {
        return null;
    }

    @Override
    public ResponseEntity<SurgeryFullInfoDTO> getSurgeryById(Integer surgeryId) {
        return null;
    }
}
