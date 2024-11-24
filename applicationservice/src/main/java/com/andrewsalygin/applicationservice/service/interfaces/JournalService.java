package com.andrewsalygin.applicationservice.service.interfaces;

import com.andrewsalygin.applicationservice.model.PatientJournalNote;
import com.andrewsalygin.applicationservice.model.PatientJournalNoteFullInfo;
import java.util.List;

import com.andrewsalygin.applicationservice.model.RecipeFullInfo;
import org.springframework.http.ResponseEntity;

public interface JournalService {

    ResponseEntity<Void> deleteNote(Integer medicalHistoryNoteId);

    ResponseEntity<List<PatientJournalNote>> getNotes(Integer limit, Integer offset);

    ResponseEntity<PatientJournalNoteFullInfo> getNote(Integer medicalHistoryNoteId);

    ResponseEntity<Void> editAnamnesis(Integer medicalHistoryNoteId, String anamnesis);

    ResponseEntity<Void> addDiseaseToNote(Integer medicalHistoryNoteId, Integer diseaseId);

    ResponseEntity<Void> deleteDiseaseFromNote(Integer medicalHistoryNoteId, Integer diseaseId);

    ResponseEntity<Void> addTreatmentToDiseaseInNote(Integer medicalHistoryNoteId, Integer diseaseId, Integer treatmentId);

    ResponseEntity<Void> deleteTreatmentForDiseaseInNote(Integer medicalHistoryNoteId, Integer diseaseId, Integer treatmentId);

    ResponseEntity<Void> changeResultsOfTreatmentForDiseaseInNote(Integer medicalHistoryNoteId, Integer diseaseId, Integer treatmentId, String resultsOfTreatment);

    ResponseEntity<List<RecipeFullInfo>> getRecipesNote(Integer medicalHistoryNoteId);
}
