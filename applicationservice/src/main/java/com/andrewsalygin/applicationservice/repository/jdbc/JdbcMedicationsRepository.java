package com.andrewsalygin.applicationservice.repository.jdbc;

import com.andrewsalygin.applicationservice.repository.interfaces.MedicationsRepository;
import com.andrewsalygin.applicationservice.dto.medication.MedicationFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.medication.MedicationWithoutIdDTO;
import com.andrewsalygin.applicationservice.dto.medication.NewInfoMedicationDTO;
import com.andrewsalygin.applicationservice.model.IdResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcMedicationsRepository implements MedicationsRepository {

    private final JdbcClient client;

    @Override
    public void setNewMedicationFeatures(Integer medicationId, NewInfoMedicationDTO newInfoMedication) {
        client.sql("EXEC dbo.updateMedicationStock " +
                "    @medicationId = ?, " +
                "    @newCount = ?, " +
                "    @newPrice = ?, " +
                "    @dateOfManufacture = ?, " +
                "    @expireDate = ?; " +
                "SELECT * FROM dbo.medication;")
            .param(medicationId)
            .param(newInfoMedication.getAvailableCount())
            .param(newInfoMedication.getPrice())
            .param(newInfoMedication.getDateOfManufacture())
            .param(newInfoMedication.getExpireDate())
            .update();
    }

    @Override
    public Integer addMedication(MedicationWithoutIdDTO medicationWithoutId) {
        return client.sql("INSERT INTO medication (medicationName, medicationForm, dosage, manufacturer, " +
                "countryOfManufacture, dateOfManufacture, expireDate, isPrescription, price, availableCount) " +
                "OUTPUT INSERTED.medicationId VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
            .param(medicationWithoutId.getMedicationName())
            .param(medicationWithoutId.getMedicationForm())
            .param(medicationWithoutId.getDosage())
            .param(medicationWithoutId.getManufacturer())
            .param(medicationWithoutId.getCountryOfManufacture())
            .param(medicationWithoutId.getDateOfManufacture())
            .param(medicationWithoutId.getExpireDate())
            .param(medicationWithoutId.getIsPrescription())
            .param(medicationWithoutId.getPrice())
            .param(medicationWithoutId.getAvailableCount())
            .query(Integer.class)
            .single();
    }

    @Override
    public void deleteMedication(Integer medicationId) {
        client.sql("UPDATE medication SET isDeleted = 1 WHERE medicationId = ?")
            .param(medicationId)
            .update();
    }

    @Override
    public List<MedicationFullInfoDTO> getMedications(Integer limit, Integer offset) {
        String query;
        if (limit == -1) {
            query = "SELECT medicationId, medicationName, medicationForm, dosage, manufacturer, countryOfManufacture, " +
                "dateOfManufacture, expireDate, isPrescription, price, availableCount FROM medication " +
                "WHERE isDeleted = 0 ORDER BY medicationId " +
                "OFFSET :offset ROWS";
        } else {
            query = "SELECT medicationId, medicationName, medicationForm, dosage, manufacturer, countryOfManufacture, " +
                "dateOfManufacture, expireDate, isPrescription, price, availableCount FROM medication " +
                "WHERE isDeleted = 0 ORDER BY medicationId " +
                "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";
        }
        return client.sql(query)
            .param("limit", limit)
            .param("offset", offset)
            .query(MedicationFullInfoDTO.class)
            .list();
    }

    @Override
    public MedicationFullInfoDTO getMedication(Integer medicationId) {
        return client.sql("SELECT medicationId, medicationName, medicationForm, dosage, manufacturer, " +
                "countryOfManufacture, dateOfManufacture, expireDate, isPrescription, price, availableCount " +
                "FROM medication WHERE isDeleted = 0 AND medicationId = ?")
            .param(medicationId)
            .query(MedicationFullInfoDTO.class)
            .single();
    }
}
