package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.patient.PatientFullInfoDTO;
import com.andrewsalygin.dto.patient.PatientShortInfoDTO;
import com.andrewsalygin.dto.patient.PatientWithoutIdDTO;
import com.andrewsalygin.exception.PatientNotFoundException;
import com.andrewsalygin.repository.PatientRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcPatientRepository implements PatientRepository {

    private final JdbcClient client;

    @Override
    public void attachPatient(Integer id) {
        client.sql("UPDATE patient SET isDeleted = 0 WHERE patientId = :patientId")
            .param("patientId", id)
            .update();
    }

    @Override
    public void detachPatient(Integer id) {
        client.sql("UPDATE patient SET isDeleted = 1 WHERE patientId = :patientId")
            .param("patientId", id)
            .update();
    }

    @Override
    public void editPatient(Integer id, PatientWithoutIdDTO patientWithoutId) {
        client.sql("UPDATE patient SET " +
                "lastName = :lastName, " +
                "firstName = :firstName, " +
                "middleName = :middleName, " +
                "gender = :gender, " +
                "dateOfBirth = :dateOfBirth, " +
                "phoneNumber = :phoneNumber, " +
                "insuranceInformation = :insuranceInformation " +
                "WHERE patientId = :patientId")
            .param("lastName", patientWithoutId.getLastName())
            .param("firstName", patientWithoutId.getFirstName())
            .param("middleName", patientWithoutId.getMiddleName())
            .param("gender", patientWithoutId.getGender())
            .param("dateOfBirth", patientWithoutId.getDateOfBirth())
            .param("phoneNumber", patientWithoutId.getPhoneNumber())
            .param("insuranceInformation", patientWithoutId.getInsuranceInformation())
            .param("patientId", id)
            .update();
    }

    @Override
    public PatientFullInfoDTO getPatient(Integer id) {
        return client.sql("SELECT * FROM patient WHERE patientId = :patientId")
            .param("patientId", id)
            .query(PatientFullInfoDTO.class)
            .optional().orElseThrow(PatientNotFoundException::new);
    }

    @Override
    public List<PatientShortInfoDTO> getPatients(Integer limit, Integer offset) {
        String query = "";
        if (limit == -1) {
            query = "SELECT patientId, lastName, firstName, middleName, gender, dateOfBirth, isDeleted " +
                "FROM patient " +
                "ORDER BY patientId " +
                "OFFSET :offset ROWS";
        } else if (limit > 0) {
            query = "SELECT patientId, lastName, firstName, middleName, gender, dateOfBirth, isDeleted " +
                "FROM patient " +
                "ORDER BY patientId " +
                "OFFSET :offset ROWS " +
                "FETCH NEXT :limit ROWS ONLY";
        }
        return client.sql(query)
            .param("limit", limit)
            .param("offset", offset)
            .query(PatientShortInfoDTO.class)
            .list();
    }

    @Override
    public Integer registerPatient(PatientWithoutIdDTO patientWithoutId) {
        client.sql(
                "INSERT INTO patientInsertV (lastName, firstName, middleName, gender, dateOfBirth, phoneNumber, insuranceInformation) " +
                    "VALUES (:lastName, :firstName, :middleName, :gender, :dateOfBirth, :phoneNumber, :insuranceInformation)")
            .param("lastName", patientWithoutId.getLastName())
            .param("firstName", patientWithoutId.getFirstName())
            .param("middleName", patientWithoutId.getMiddleName())
            .param("gender", patientWithoutId.getGender())
            .param("dateOfBirth", patientWithoutId.getDateOfBirth())
            .param("phoneNumber", patientWithoutId.getPhoneNumber())
            .param("insuranceInformation", patientWithoutId.getInsuranceInformation())
            .update();

        return client.sql("SELECT patientId FROM patient WHERE phoneNumber = :phoneNumber").
            param("phoneNumber", patientWithoutId.getPhoneNumber())
            .query(Integer.class).optional().orElseThrow(PatientNotFoundException::new);
    }

    @Override
    public void deletePatient(Integer id) {
        client.sql("DELETE FROM patient WHERE patientId = :patientId")
            .param("patientId", id)
            .update();
    }
}
