package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureFullInfoWithTreatmentDaysDTO;
import com.andrewsalygin.dto.medication.MedicationsFullInfoWithTreatmentDaysDTO;
import com.andrewsalygin.dto.treatment.TreatmentFullInfoDTO;
import com.andrewsalygin.dto.treatment.TreatmentPriceDTO;
import com.andrewsalygin.repository.interfaces.TreatmentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcTreatmentRepository implements TreatmentRepository {

    private final JdbcClient client;

    @Override
    public Integer addTreatment(String treatmentName, Integer doctorId) {
        return client.sql("INSERT INTO treatment (treatmentName, doctorId) OUTPUT INSERTED.treatmentId VALUES (?, ?)")
            .params(treatmentName, doctorId)
            .query(Integer.class)
            .single();
    }

    @Override
    public void deleteTreatment(Integer treatmentId) {
        client.sql("UPDATE treatment SET isDeleted = 1 WHERE treatmentId = ?")
            .param(treatmentId)
            .update();
    }

    @Override
    public List<MedicalProcedureFullInfoWithTreatmentDaysDTO> getMedicalProceduresWithTreatment(Integer treatmentId) {
        return client.sql("SELECT tmp.medicalProcedureId, mp.medicalProcedureName, mp.price, tmp.amount, tmp.doctorInstructions " +
                "FROM treatmentMedicalProcedure tmp " +
                "INNER JOIN medicalProcedure mp ON tmp.medicalProcedureId = mp.medicalProcedureId " +
                "WHERE tmp.treatmentId = ?")
            .param(treatmentId)
            .query(MedicalProcedureFullInfoWithTreatmentDaysDTO.class)
            .list();
    }

    @Override
    public void addMedicalProcedureToTreatment(
        Integer treatmentId,
        Integer procedureId,
        Integer amount,
        String doctorInstructions
    ) {
        client.sql("INSERT INTO treatmentMedicalProcedure (treatmentId, medicalProcedureId, amount, doctorInstructions) VALUES (?, ?, ?, ?)")
            .param(treatmentId)
            .param(procedureId)
            .param(amount)
            .param(doctorInstructions)
            .update();
    }

    @Override
    public List<MedicationsFullInfoWithTreatmentDaysDTO> getMedicationsWithTreatment(Integer treatmentId) {
        return client.sql("SELECT m.medicationId, medicationName, medicationForm, dosage, manufacturer, " +
                "countryOfManufacture, dateOfManufacture, expireDate, isPrescription, price, availableCount, amount, " +
                "doctorInstructions " +
                "FROM treatmentMedication tm " +
                "INNER JOIN medication m ON tm.medicationId = m.medicationId " +
                "WHERE tm.treatmentId = ?")
            .param(treatmentId)
            .query(MedicationsFullInfoWithTreatmentDaysDTO.class)
            .list();
    }

    @Override
    public void addMedicationToTreatment(
        Integer treatmentId,
        Integer medicationId,
        Integer amount,
        String doctorInstructions
    ) {
        client.sql("INSERT INTO TreatmentMedication (treatmentId, medicationId, amount, doctorInstructions) VALUES (?, ?, ?, ?)")
            .param(treatmentId)
            .param(medicationId)
            .param(amount)
            .param(doctorInstructions)
            .update();
    }

    @Override
    public List<TreatmentPriceDTO> getTreatmentPrice(Integer treatmentId) {
        return client.sql("DECLARE @totalMedicationCost float; " +
                "DECLARE @totalProcedureCost float; " +
                "DECLARE @totalCost float; " +
                "EXECUTE dbo.calculateTreatmentCosts " +
                "    @treatmentId = ?, " +
                "    @totalMedicationCost = @totalMedicationCost OUTPUT, " +
                "    @totalProcedureCost = @totalProcedureCost OUTPUT, " +
                "    @totalCost = @totalCost OUTPUT; " +
                "SELECT  @totalMedicationCost AS totalMedicationsCost, " +
                "        @totalProcedureCost AS totalMedicalProceduresCost, " +
                "        @totalCost AS totalCost;")
            .param(treatmentId)
            .query(TreatmentPriceDTO.class)
            .list();
    }

    @Override
    public void deleteMedicalProcedureFromTreatment(Integer treatmentId, Integer procedureId) {
        client.sql("DELETE FROM treatmentMedicalProcedure WHERE treatmentId = ? AND medicalProcedureId = ?")
            .param(treatmentId)
            .param(procedureId)
            .update();
    }

    @Override
    public void deleteMedicationFromTreatment(Integer treatmentId, Integer medicationId) {
        client.sql("DELETE FROM treatmentMedication WHERE treatmentId = ? AND medicationId = ?")
            .param(treatmentId)
            .param(medicationId)
            .update();
    }

    @Override
    public void updateTreatmentMedicalProcedure(
        Integer treatmentId,
        Integer medicalProcedureId,
        Integer amount,
        String doctorInstructions
    ) {
        client.sql("UPDATE treatmentMedicalProcedure SET amount = ?, doctorInstructions = ? WHERE treatmentId = ? AND " +
                "medicalProcedureId = ?")
            .param(amount)
            .param(doctorInstructions)
            .param(treatmentId)
            .param(medicalProcedureId)
            .update();
    }

    @Override
    public void updateTreatmentMedication(
        Integer treatmentId,
        Integer medicationId,
        Integer amount,
        String doctorInstructions
    ) {
        client.sql("UPDATE TreatmentMedication SET amount = ?, doctorInstructions = ? WHERE treatmentId = ? AND " +
                "medicationId = ?")
            .param(amount)
            .param(doctorInstructions)
            .param(treatmentId)
            .param(medicationId)
            .update();
    }

    @Override
    public List<TreatmentFullInfoDTO> getAllTreatments(Integer limit, Integer offset) {
        String query;
        if (limit == -1) {
            query = "SELECT treatmentId, treatmentName, doctorId, isDeleted FROM treatment " +
                "ORDER BY treatmentId " +
                "OFFSET :offset ROWS";
        } else {
            query = "SELECT treatmentId, treatmentName, doctorId, medicationId FROM treatment " +
                "ORDER BY treatmentId " +
                "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";
        }
        return client.sql(query)
            .param("limit", limit)
            .param("offset", offset)
            .query(TreatmentFullInfoDTO.class)
            .list();
    }

    @Override
    public List<TreatmentFullInfoDTO> getDoctorTreatments(Integer doctorId, Integer limit, Integer offset) {
        String query;
        if (limit == -1) {
            query = "SELECT treatmentId, treatmentName, doctorId, isDeleted FROM treatment " +
                "WHERE doctorId = :doctorId " +
                "ORDER BY treatmentId " +
                "OFFSET :offset ROWS";
        } else {
            query = "SELECT treatmentId, treatmentName, doctorId, isDeleted FROM treatment " +
                "WHERE doctorId = :doctorId " +
                "ORDER BY treatmentId " +
                "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";
        }
        return client.sql(query)
            .param("doctorId", doctorId)
            .param("limit", limit)
            .param("offset", offset)
            .query(TreatmentFullInfoDTO.class)
            .list();
    }

    @Override
    public TreatmentFullInfoDTO getTreatmentById(Integer treatmentId) {
        return client.sql("SELECT treatmentId, treatmentName, doctorId, isDeleted FROM treatment WHERE treatmentId = ?")
            .param(treatmentId)
            .query(TreatmentFullInfoDTO.class)
            .single();
    }
}
