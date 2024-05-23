package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.patientJournal.PatientJournalNoteDTO;
import com.andrewsalygin.dto.patientJournal.PatientJournalNoteFullInfoDTO;
import com.andrewsalygin.dto.patientJournal.PatientJournalNoteFullInfoDiseaseListInnerDTO;
import com.andrewsalygin.dto.recipe.RecipeFullInfoDTO;
import com.andrewsalygin.repository.interfaces.JournalRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcJournalRepository implements JournalRepository {

    private final JdbcClient client;

    @Override
    public void deleteNote(Integer medicalHistoryNoteId) {
        client.sql("UPDATE patientJournal SET isDeleted = 1 WHERE medicalHistoryNoteId = ?")
            .param(medicalHistoryNoteId)
            .update();
    }

    @Override
    public List<PatientJournalNoteDTO> getNotes(Integer limit, Integer offset) {
        String query;
        if (limit == -1) {
            query =
                "SELECT pj.medicalHistoryNoteId, patientId, doctorId, initialAdmission, discharge, patientStatus, " +
                    "admissionDateTime, isDeleted " +
                    "FROM patientJournal pj INNER JOIN medicalHistoryNote mhn ON pj.medicalHistoryNoteId = " +
                    "mhn.medicalHistoryNoteId " +
                    "ORDER BY medicalHistoryNoteId " +
                    "OFFSET :offset ROWS";
        } else {
            query =
                "SELECT pj.medicalHistoryNoteId, patientId, doctorId, initialAdmission, discharge, patientStatus, " +
                    "admissionDateTime, isDeleted " +
                    "FROM patientJournal pj INNER JOIN medicalHistoryNote mhn ON pj.medicalHistoryNoteId = " +
                    "mhn.medicalHistoryNoteId " +
                    "ORDER BY medicalHistoryNoteId " +
                    "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";
        }
        return client.sql(query)
            .param("offset", offset)
            .param("limit", limit)
            .query(PatientJournalNoteDTO.class)
            .list();
    }

    @Override
    public PatientJournalNoteFullInfoDTO getNote(Integer medicalHistoryNoteId) {
        String query =
            "SELECT pj.medicalHistoryNoteId, patientId, doctorId, initialAdmission, discharge, patientStatus, " +
                "admissionDateTime, anamnesis, isDeleted " +
                "FROM patientJournal pj " +
                "INNER JOIN medicalHistoryNote mhn ON pj.medicalHistoryNoteId = mhn.medicalHistoryNoteId " +
                "WHERE pj.medicalHistoryNoteId = ?";

        PatientJournalNoteFullInfoDTO result = client.sql(query)
            .param(medicalHistoryNoteId)
            .query(PatientJournalNoteFullInfoDTO.class)
            .single();

        result.setDiseaseList(client.sql(
                "SELECT diseaseId, treatmentId, resultsOfTreatment FROM diseaseList WHERE medicalHistoryNoteId = ?")
            .param(medicalHistoryNoteId)
            .query(PatientJournalNoteFullInfoDiseaseListInnerDTO.class)
            .list());

        return result;
    }

    @Override
    public void editAnamnesis(Integer medicalHistoryNoteId, String anamnesis) {
        client.sql("UPDATE medicalHistoryNote SET anamnesis = ? WHERE medicalHistoryNoteId = ?")
            .params(anamnesis, medicalHistoryNoteId)
            .update();
    }

    @Override
    public void addDiseaseToNote(Integer medicalHistoryNoteId, Integer diseaseId) {
        client.sql("INSERT INTO diseaseList (medicalHistoryNoteId, diseaseId) VALUES (?, ?)")
            .params(medicalHistoryNoteId, diseaseId)
            .update();
    }

    @Override
    public void deleteDiseaseFromNote(Integer medicalHistoryNoteId, Integer diseaseId) {
        client.sql("DELETE FROM diseaseList WHERE medicalHistoryNoteId = ? AND diseaseId = ?")
            .params(medicalHistoryNoteId, diseaseId)
            .update();
    }

    @Override
    public void addTreatmentToDiseaseInNote(Integer medicalHistoryNoteId, Integer diseaseId, Integer treatmentId) {
        client.sql("INSERT INTO diseaseList(treatmentId, medicalHistoryNoteId, diseaseId) VALUES (?, ?, ?)")
            .params(treatmentId, medicalHistoryNoteId, diseaseId)
            .update();
    }

    @Override
    public void deleteTreatmentForDiseaseInNote(Integer medicalHistoryNoteId, Integer diseaseId, Integer treatmentId) {
        client.sql("DELETE FROM diseaseList WHERE medicalHistoryNoteId = ? AND diseaseId = ? AND treatmentId = ?")
            .params(medicalHistoryNoteId, diseaseId, treatmentId)
            .update();
    }

    @Override
    public void changeResultsOfTreatmentForDiseaseInNote(
        Integer medicalHistoryNoteId,
        Integer diseaseId,
        Integer treatmentId,


        String resultsOfTreatment
    ) {
        client.sql(
                "UPDATE diseaseList SET resultsOfTreatment = ? WHERE medicalHistoryNoteId = ? AND diseaseId = ? AND treatmentId = ?")
            .params(resultsOfTreatment, medicalHistoryNoteId, diseaseId, treatmentId)
            .update();
    }

    @Override
    public List<RecipeFullInfoDTO> getRecipes(Integer medicalHistoryNoteId) {
        return client.sql(
                "SELECT rV.recipeId, admissionDateTime, expirationDate, medicationName, medicationForm, dosage, " +
                    "patientLastName, patientFirstName, patientMiddleName, doctorLastName, doctorFirstName, " +
                    "doctorMiddleName, isDeleted " +
                    "FROM recipeV rV INNER JOIN recipeJournal rj ON rV.recipeId = rj.recipeId WHERE medicalHistoryNoteId = ?")
            .param(medicalHistoryNoteId)
            .query(RecipeFullInfoDTO.class)
            .list();
    }
}
