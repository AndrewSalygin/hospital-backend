package com.andrewsalygin.repository.interfaces;

import com.andrewsalygin.dto.patientJournal.PatientJournalNoteDTO;
import com.andrewsalygin.dto.patientJournal.PatientJournalNoteFullInfoDTO;
import com.andrewsalygin.dto.recipe.RecipeFullInfoDTO;

import java.util.List;

public interface JournalRepository {

    void deleteNote(Integer medicalHistoryNoteId);

    List<PatientJournalNoteDTO> getNotes(Integer limit, Integer offset);

    PatientJournalNoteFullInfoDTO getNote(Integer medicalHistoryNoteId);

    void editAnamnesis(Integer medicalHistoryNoteId, String anamnesis);

    void addDiseaseToNote(Integer medicalHistoryNoteId, Integer diseaseId);

    void deleteDiseaseFromNote(Integer medicalHistoryNoteId, Integer diseaseId);

    void addTreatmentToDiseaseInNote(Integer medicalHistoryNoteId, Integer diseaseId, Integer treatmentId);

    void deleteTreatmentForDiseaseInNote(Integer medicalHistoryNoteId, Integer diseaseId, Integer treatmentId);

    void changeResultsOfTreatmentForDiseaseInNote(Integer medicalHistoryNoteId, Integer diseaseId, Integer treatmentId, String resultsOfTreatment);

    List<RecipeFullInfoDTO> getRecipes(Integer medicalHistoryNoteId);
}
