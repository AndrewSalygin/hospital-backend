package com.andrewsalygin.applicationservice.repository.interfaces;

import com.andrewsalygin.applicationservice.dto.disease.DiseaseFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientShortInfoDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientWithDiseaseDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientWithoutIdDTO;
import com.andrewsalygin.applicationservice.dto.patientJournal.PatientJournalNoteDTO;
import com.andrewsalygin.applicationservice.dto.recipe.RecipeFullInfoDTO;
import com.andrewsalygin.applicationservice.model.CreatePatientJournalNote;
import com.andrewsalygin.applicationservice.model.DiseaseFullInfo;
import com.andrewsalygin.applicationservice.model.IdResponse;
import com.andrewsalygin.applicationservice.model.PatientJournalNote;
import com.andrewsalygin.applicationservice.model.PatientWithDisease;
import com.andrewsalygin.applicationservice.model.RecipeFullInfo;
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
