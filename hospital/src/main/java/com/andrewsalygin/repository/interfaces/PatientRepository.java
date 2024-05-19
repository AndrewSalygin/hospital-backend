package com.andrewsalygin.repository.interfaces;

import com.andrewsalygin.dto.disease.DiseaseFullInfoDTO;
import com.andrewsalygin.dto.patient.PatientFullInfoDTO;
import com.andrewsalygin.dto.patient.PatientShortInfoDTO;
import com.andrewsalygin.dto.patient.PatientWithDiseaseDTO;
import com.andrewsalygin.dto.patient.PatientWithoutIdDTO;
import com.andrewsalygin.dto.patientJournal.PatientJournalNoteDTO;
import com.andrewsalygin.dto.recipe.RecipeFullInfoDTO;
import com.andrewsalygin.hospital.model.CreatePatientJournalNote;
import com.andrewsalygin.hospital.model.DiseaseFullInfo;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.PatientJournalNote;
import com.andrewsalygin.hospital.model.PatientWithDisease;
import com.andrewsalygin.hospital.model.RecipeFullInfo;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.List;

public interface PatientRepository {

    void attachPatient(Integer id);

    void detachPatient(Integer id);

    void editPatient(Integer id, PatientWithoutIdDTO patientWithoutId);

    PatientFullInfoDTO getPatient(Integer id);

    List<PatientShortInfoDTO> getPatients(Integer limit, Integer offset);

    Integer registerPatient(PatientWithoutIdDTO patientWithoutId);

    void deletePatient(Integer id);

    Integer addPatientNote(CreatePatientJournalNote createMeetingJournalNote);

    void deletePatientNote(Integer medicalHistoryNoteId);

    void changePatientNote(
        Integer medicalHistoryNoteId,
        CreatePatientJournalNote createPatientJournalNote
    );

    List<RecipeFullInfoDTO> getRecipesPatient(Integer patientId);

    Integer addRecipeToPatient(
        Integer patientId,
        Integer medicationId,
        LocalDate expirationDate,
        Integer medicalHistoryNoteId
    );

    void addDiseaseToPatient(
        Integer patientId,
        Integer diseaseId,
        Boolean dispensaryAccounting
    );

    void deleteDiseaseFromPatient(Integer patientId, Integer diseaseId);

    List<DiseaseFullInfoDTO> getDiseasesPatient(Integer patientId);

    List<PatientWithDiseaseDTO> getPatientsDiseases(Integer limit, Integer offset);

    List<PatientJournalNoteDTO> getPatientMeetings(
        Integer patientId,
        Integer limit,
        Integer offset
    );
}
