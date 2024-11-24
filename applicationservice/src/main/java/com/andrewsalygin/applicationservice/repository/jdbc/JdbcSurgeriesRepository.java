package com.andrewsalygin.applicationservice.repository.jdbc;

import com.andrewsalygin.applicationservice.repository.interfaces.SurgeriesRepository;
import com.andrewsalygin.applicationservice.dto.TotalCostDTO;
import com.andrewsalygin.applicationservice.dto.medication.MedicationWithAmountDTO;
import com.andrewsalygin.applicationservice.dto.surgeries.DoctorSurgeryCountDTO;
import com.andrewsalygin.applicationservice.dto.surgeries.DoctorWithWorkingHoursDTO;
import com.andrewsalygin.applicationservice.dto.surgeries.SurgeryFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.surgeries.SurgeryFullInfoWithoutIdDTO;
import com.andrewsalygin.applicationservice.model.IdResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcSurgeriesRepository implements SurgeriesRepository {

    private final JdbcClient client;

    @Override
    public List<SurgeryFullInfoDTO> getAllSurgeries(Integer limit, Integer offset) {
        String query;
        if (limit == -1) {
            query = "SELECT surgeryId, patientId, scheduledDateTime, startTime, endTime, surgeryType, " +
                "surgicalProcedureDescription, emergency, isDeleted " +
                "FROM surgery " +
                "ORDER BY surgeryId " +
                "OFFSET :offset ROWS";
        } else {
            query = "SELECT surgeryId, patientId, scheduledDateTime, startTime, endTime, surgeryType, " +
                "surgicalProcedureDescription, emergency, isDeleted " +
                "FROM surgery " +
                "ORDER BY surgeryId " +
                "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";
        }
        return client.sql(query)
            .param("offset", offset)
            .param("limit", limit)
            .query(SurgeryFullInfoDTO.class)
            .list();
    }

    @Override
    public Integer createSurgery(SurgeryFullInfoWithoutIdDTO surgeryFullInfoWithoutId) {
        return client.sql("INSERT INTO surgery (patientId, scheduledDateTime, startTime, endTime, " +
                "surgeryType, surgicalProcedureDescription, emergency) OUTPUT INSERTED.surgeryId VALUES (?, ?, ?, ?, ?, ?, ?)")
            .params(
                surgeryFullInfoWithoutId.getPatientId(),
                surgeryFullInfoWithoutId.getScheduledDateTime(),
                surgeryFullInfoWithoutId.getStartTime(),
                surgeryFullInfoWithoutId.getEndTime(),
                surgeryFullInfoWithoutId.getSurgeryType(),
                surgeryFullInfoWithoutId.getSurgicalProcedureDescription(),
                surgeryFullInfoWithoutId.getEmergency()
            )
            .query(Integer.class)
            .single();
    }

    @Override
    public void deleteSurgery(Integer surgeryId) {
        client.sql("UPDATE surgery SET isDeleted = 1 WHERE surgeryId = ?")
            .param(surgeryId)
            .update();
    }

    @Override
    public void updateSurgery(Integer surgeryId, SurgeryFullInfoWithoutIdDTO surgeryFullInfoWithoutId) {
        client.sql("UPDATE surgery SET patientId = ?, scheduledDateTime = ?, startTime = ?, endTime = ?, " +
                "surgeryType = ?, surgicalProcedureDescription = ?, emergency = ? WHERE surgeryId = ?")
            .params(
                surgeryFullInfoWithoutId.getPatientId(),
                surgeryFullInfoWithoutId.getScheduledDateTime(),
                surgeryFullInfoWithoutId.getStartTime(),
                surgeryFullInfoWithoutId.getEndTime(),
                surgeryFullInfoWithoutId.getSurgeryType(),
                surgeryFullInfoWithoutId.getSurgicalProcedureDescription(),
                surgeryFullInfoWithoutId.getEmergency(),
                surgeryId
            )
            .update();
    }

    @Override
    public List<DoctorWithWorkingHoursDTO> getDoctorsForSurgery(Integer surgeryId) {
        return client.sql("SELECT ds.doctorId, ds.workingHours, ds.scheduledWorkingHours " +
                "FROM doctorSurgeryJournal ds WHERE ds.surgeryId = ?")
            .param(surgeryId)
            .query(DoctorWithWorkingHoursDTO.class)
            .list();
    }

    @Override
    public void addDoctorToSurgery(
        Integer surgeryId,
        Integer doctorId,
        Float workingHours,
        Float scheduledWorkingHours
    ) {
        client.sql("INSERT INTO doctorSurgeryJournal (surgeryId, doctorId, workingHours, scheduledWorkingHours) " +
                "VALUES (?, ?, ?, ?)")
            .params(surgeryId, doctorId, workingHours, scheduledWorkingHours)
            .update();
    }

    @Override
    public void updateDoctorWorkingHours(
        Integer surgeryId,
        Integer doctorId,
        Float workingHours,
        Float scheduledWorkingHours
    ) {
        client.sql("UPDATE doctorSurgeryJournal SET workingHours = ?, scheduledWorkingHours = ? " +
                "WHERE surgeryId = ? AND doctorId = ?")
            .params(workingHours, scheduledWorkingHours, surgeryId, doctorId)
            .update();
    }

    @Override
    public List<DoctorSurgeryCountDTO> countSurgeriesByDoctor(LocalDate startDate, LocalDate endDate) {
        return client.sql("DECLARE @startDate DATETIME = ?; DECLARE @endDate DATETIME = ? " +
                "EXEC countSurgeriesByDoctor @startDate, @endDate")
            .params(startDate, endDate)
            .query(DoctorSurgeryCountDTO.class)
            .list();
    }

    @Override
    public TotalCostDTO calculateTotalMedicationCost(Integer surgeryId) {
        return client.sql("DECLARE @totalCost FLOAT; EXEC calculateTotalMedicationCost @surgeryId = ?, " +
                "@totalCost = @totalCost OUTPUT; SELECT @totalCost AS totalMedicationCost")
            .param(surgeryId)
            .query(TotalCostDTO.class)
            .single();
    }

    @Override
    public void removeMedicationFromSurgery(Integer surgeryId, Integer medicationId) {
        client.sql("DELETE FROM medicationList WHERE surgeryId = ? AND medicationId = ?")
            .params(surgeryId, medicationId)
            .update();
    }

    @Override
    public void removeDoctorFromSurgery(Integer surgeryId, Integer doctorId) {
        client.sql("DELETE FROM doctorSurgeryJournal WHERE surgeryId = ? AND doctorId = ?")
            .params(surgeryId, doctorId)
            .update();
    }

    @Override
    public List<MedicationWithAmountDTO> getMedicationsForSurgery(Integer surgeryId) {
        return client.sql("SELECT ml.medicationId, ml.amount FROM medicationList ml " +
                "WHERE ml.surgeryId = ?")
            .param(surgeryId)
            .query(MedicationWithAmountDTO.class)
            .list();
    }

    @Override
    public void addMedicationToSurgery(Integer surgeryId, Integer medicationId, Integer amount) {
        client.sql("INSERT INTO medicationList (surgeryId, medicationId, amount) VALUES (?, ?, ?)")
            .params(surgeryId, medicationId, amount)
            .update();
    }

    @Override
    public void updateSurgeryDescription(Integer surgeryId, String surgicalProcedureDescription) {
        client.sql("UPDATE surgery SET surgicalProcedureDescription = ? WHERE surgeryId = ?")
            .params(surgicalProcedureDescription, surgeryId)
            .update();
    }

    @Override
    public List<SurgeryFullInfoDTO> getSurgeriesByPatient(Integer patientId) {
        return client.sql("SELECT surgeryId, patientId, scheduledDateTime, startTime, endTime, surgeryType, " +
                "surgicalProcedureDescription, emergency, isDeleted FROM surgery WHERE patientId = ?")
            .param(patientId)
            .query(SurgeryFullInfoDTO.class)
            .list();
    }

    @Override
    public SurgeryFullInfoDTO getSurgeryById(Integer surgeryId) {
        return client.sql("SELECT surgeryId, patientId, scheduledDateTime, startTime, endTime, surgeryType, " +
                "surgicalProcedureDescription, emergency FROM surgery WHERE surgeryId = ?")
            .param(surgeryId)
            .query(SurgeryFullInfoDTO.class)
            .single();
    }
}
