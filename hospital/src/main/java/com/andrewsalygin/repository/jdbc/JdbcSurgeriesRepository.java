package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.TotalCostDTO;
import com.andrewsalygin.dto.medication.MedicationWithAmountDTO;
import com.andrewsalygin.dto.surgeries.DoctorSurgeryCountDTO;
import com.andrewsalygin.dto.surgeries.DoctorWithWorkingHoursDTO;
import com.andrewsalygin.dto.surgeries.SurgeryFullInfoDTO;
import com.andrewsalygin.dto.surgeries.SurgeryFullInfoWithoutIdDTO;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.repository.interfaces.SurgeriesRepository;
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
    public List<SurgeryFullInfoDTO> getAllSurgeries(Integer limit, Integer offset) {
        return List.of();
    }

    @Override
    public Integer createSurgery(SurgeryFullInfoWithoutIdDTO surgeryFullInfoWithoutId) {
        return 0;
    }

    @Override
    public void deleteSurgery(Integer surgeryId) {

    }

    @Override
    public void updateSurgery(Integer surgeryId, SurgeryFullInfoWithoutIdDTO surgeryFullInfoWithoutId) {

    }

    @Override
    public List<DoctorWithWorkingHoursDTO> getDoctorsForSurgery(Integer surgeryId) {
        return List.of();
    }

    @Override
    public Integer addDoctorToSurgery(
        Integer surgeryId,
        Integer doctorId,
        Float workingHours,
        Float scheduledWorkingHours
    ) {
        return 0;
    }

    @Override
    public void updateDoctorWorkingHours(
        Integer surgeryId,
        Integer doctorId,
        Float workingHours,
        Float scheduledWorkingHours
    ) {

    }

    @Override
    public List<DoctorSurgeryCountDTO> countSurgeriesByDoctor(OffsetDateTime startDate, OffsetDateTime endDate) {
        return List.of();
    }

    @Override
    public TotalCostDTO calculateTotalMedicationCost(Integer surgeryId) {
        return null;
    }

    @Override
    public void removeMedicationFromSurgery(Integer surgeryId, Integer medicationId) {

    }

    @Override
    public void removeDoctorFromSurgery(Integer surgeryId, Integer doctorId) {

    }

    @Override
    public List<MedicationWithAmountDTO> getMedicationsForSurgery(Integer surgeryId) {
        return List.of();
    }

    @Override
    public void addMedicationToSurgery(Integer surgeryId, Integer medicationId, Integer amount) {

    }

    @Override
    public void updateSurgeryDescription(Integer surgeryId, String surgicalProcedureDescription) {

    }

    @Override
    public List<SurgeryFullInfoDTO> getSurgeriesByPatient(Integer patientId) {
        return List.of();
    }

    @Override
    public SurgeryFullInfoDTO getSurgeryById(Integer surgeryId) {
        return null;
    }
}
