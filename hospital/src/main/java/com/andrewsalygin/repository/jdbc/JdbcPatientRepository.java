package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.disease.DiseaseFullInfoDTO;
import com.andrewsalygin.dto.patient.PatientFullInfoDTO;
import com.andrewsalygin.dto.patient.PatientShortInfoDTO;
import com.andrewsalygin.dto.patient.PatientWithDiseaseDTO;
import com.andrewsalygin.dto.patient.PatientWithoutIdDTO;
import com.andrewsalygin.dto.patientJournal.PatientJournalNoteDTO;
import com.andrewsalygin.dto.recipe.RecipeFullInfoDTO;
import com.andrewsalygin.exception.PatientNotFoundException;
import com.andrewsalygin.hospital.model.CreatePatientJournalNote;
import com.andrewsalygin.repository.interfaces.PatientRepository;
import java.time.LocalDate;
import java.util.Collections;
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

    @Override
    public Integer addPatientNote(CreatePatientJournalNote createPatientJournalNote) {
        Integer medicalHistoryNoteId =
            client.sql("INSERT INTO medicalHistoryNote (admissionDateTime, anamnesis) " +
                "OUTPUT INSERTED.medicalHistoryNoteId VALUES (?, ?)")
            .param(createPatientJournalNote.getAdmissionDateTime())
            .param(" ")
            .query(Integer.class)
            .single();

        client.sql("INSERT INTO patientJournal (medicalHistoryNoteId, patientId, doctorId, initialAdmission, discharge, patientStatus) " +
                "VALUES (?, ?, ?, ?, ?, ?)")
            .param(medicalHistoryNoteId)
            .param(createPatientJournalNote.getPatientId())
            .param(createPatientJournalNote.getDoctorId())
            .param(createPatientJournalNote.getInitialAdmission())
            .param(createPatientJournalNote.getDischarge())
            .param(createPatientJournalNote.getPatientStatus())
            .update();

        return medicalHistoryNoteId;
    }

    @Override
    public void deletePatientNote(Integer medicalHistoryNoteId) {
        client.sql("DELETE FROM patientJournal WHERE medicalHistoryNoteId = ?")
            .param(medicalHistoryNoteId)
            .update();

        client.sql("DELETE FROM diseaseList WHERE medicalHistoryNoteId = ?")
            .param(medicalHistoryNoteId)
            .update();

        client.sql("DELETE FROM recipeJournal WHERE medicalHistoryNoteId = ?")
            .param(medicalHistoryNoteId)
            .update();

        client.sql("DELETE FROM medicalHistoryNote WHERE medicalHistoryNoteId = ?")
            .param(medicalHistoryNoteId)
            .update();
    }

    @Override
    public void changePatientNote(Integer medicalHistoryNoteId, CreatePatientJournalNote createPatientJournalNote) {
        client.sql("UPDATE patientJournal SET " +
                "patientId = ?, " +
                "doctorId = ?, " +
                "initialAdmission = ?, " +
                "discharge = ?, " +
                "patientStatus = ? " +
                "WHERE medicalHistoryNoteId = ?")
            .params(
                createPatientJournalNote.getPatientId(),
                createPatientJournalNote.getDoctorId(),
                createPatientJournalNote.getInitialAdmission(),
                createPatientJournalNote.getDischarge(),
                createPatientJournalNote.getPatientStatus(),
                medicalHistoryNoteId
            ).update();

        client.sql("UPDATE medicalHistoryNote SET " +
                "admissionDateTime = ? " +
                "WHERE medicalHistoryNoteId = ?")
            .params(
                createPatientJournalNote.getAdmissionDateTime(),
                medicalHistoryNoteId
            ).update();
    }

    @Override
    public List<RecipeFullInfoDTO> getRecipesPatient(Integer patientId) {
        List<Integer> recipesId = client.sql("SELECT recipeId FROM recipeJournal WHERE patientId = ?")
            .param(patientId)
            .query(Integer.class)
            .list();

        if (recipesId.isEmpty()) {
            return Collections.emptyList();
        }

        return client.sql("SELECT * FROM recipeV WHERE recipeId IN (:recipesId)")
            .param("recipesId", recipesId)
            .query(RecipeFullInfoDTO.class)
            .list();
    }

    @Override
    public Integer addRecipeToPatient(
        Integer patientId,
        Integer medicationId,
        LocalDate expirationDate,
        Integer medicalHistoryNoteId
    ) {
        Integer recipeId = client.sql(
                "INSERT INTO recipeInsertV(medicationId, expirationDate) OUTPUT INSERTED.recipeId VALUES(?, ?)")
            .param(medicationId)
            .param(expirationDate).query(Integer.class).single();

        client.sql(
                "INSERT INTO recipeJournal(recipeId, patientId, medicalHistoryNoteId) VALUES(?, ?, ?)")
            .param(recipeId)
            .param(patientId)
            .param(medicalHistoryNoteId)
            .update();

        return recipeId;
    }

    @Override
    public void addDiseaseToPatient(Integer patientId, Integer diseaseId, Boolean dispensaryAccounting) {
        client.sql("INSERT INTO patientDiseasesJournal(diseaseId, patientId, dispensaryAccounting) VALUES(?, ?, ?)")
            .param(diseaseId)
            .param(patientId)
            .param(dispensaryAccounting)
            .update();
    }

    @Override
    public void deleteDiseaseFromPatient(Integer patientId, Integer diseaseId) {
        client.sql("DELETE FROM patientDiseasesJournal WHERE patientId = ? AND diseaseId = ?")
            .param(patientId)
            .param(diseaseId)
            .update();
    }

    @Override
    public List<DiseaseFullInfoDTO> getDiseasesPatient(Integer patientId) {
        return client.sql(
                "SELECT * FROM disease d INNER JOIN patientDiseasesJournal pdj ON d.diseaseId = pdj.diseaseId " +
                    "WHERE patientId = ?")
            .param(patientId)
            .query(DiseaseFullInfoDTO.class)
            .list();
    }

    @Override
    public List<PatientWithDiseaseDTO> getPatientsDiseases(Integer limit, Integer offset) {
        if (limit == -1) {
            String query = "SELECT lastName, firstName, middleName, gender, dateOfBirth, diseaseCode, " +
                "diseaseName, dispensaryAccounting FROM patientDiseaseV " +
                "ORDER BY diseaseName OFFSET ? ROWS";

            return client.sql(query)
                .param(offset)
                .query(PatientWithDiseaseDTO.class)
                .list();
        } else if (limit > 0) {
            String query = "SELECT p.lastName, p.firstName, p.middleName, p.gender, p.dateOfBirth, d.diseaseCode," +
                " d.diseaseName, pdj.dispensaryAccounting FROM patient p INNER JOIN patientDiseasesJournal pdj " +
                "ON p.patientId = pdj.patientId INNER JOIN disease d ON pdj.diseaseId = d.diseaseId " +
                "ORDER BY p.patientId OFFSET ? ROWS " +
                "FETCH NEXT ? ROWS ONLY";

            return client.sql(query)
                .params(offset, limit)
                .query(PatientWithDiseaseDTO.class)
                .list();
        }

        throw new RuntimeException("Limit должен быть равен -1 или больше 0");
    }

    @Override
    public List<PatientJournalNoteDTO> getPatientMeetings(Integer patientId, Integer limit, Integer offset) {
        if (limit == -1) {
            String query = "SELECT * FROM patientJournal pj INNER JOIN medicalHistoryNote mhn " +
                "ON pj.medicalHistoryNoteId = mhn.medicalHistoryNoteId WHERE patientId = ? " +
                "ORDER BY admissionDateTime DESC " +
                "OFFSET ? ROWS";

            return client.sql(query)
                .params(patientId, offset)
                .query(PatientJournalNoteDTO.class)
                .list();
        } else if (limit > 0) {
            String query = "SELECT * FROM patientJournal pj INNER JOIN medicalHistoryNote mhn " +
                "ON pj.medicalHistoryNoteId = mhn.medicalHistoryNoteId WHERE patientId = ? " +
                "ORDER BY admissionDateTime DESC " +
                "OFFSET ? ROWS " +
                "FETCH NEXT ? ROWS ONLY";

            return client.sql(query)
                .params(patientId, offset, limit)
                .query(PatientJournalNoteDTO.class)
                .list();
        }

        throw new RuntimeException("Limit должен быть равен -1 или больше 0");
    }
}
