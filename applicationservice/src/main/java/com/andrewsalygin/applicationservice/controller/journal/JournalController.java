package com.andrewsalygin.applicationservice.controller.journal;

import com.andrewsalygin.applicationservice.api.JournalPatientApi;
import com.andrewsalygin.applicationservice.model.PatientJournalNote;
import com.andrewsalygin.applicationservice.model.PatientJournalNoteFullInfo;
import com.andrewsalygin.applicationservice.model.RecipeFullInfo;
import com.andrewsalygin.applicationservice.service.interfaces.JournalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JournalController implements JournalPatientApi {

    private final JournalService journalService;

    @Override
    public ResponseEntity<List<PatientJournalNote>> getNotes(Integer limit, Integer offset) {
        return journalService.getNotes(limit, offset);
    }

    @Override
    public ResponseEntity<PatientJournalNoteFullInfo> getNote(Integer medicalHistoryNoteId) {
        return journalService.getNote(medicalHistoryNoteId);
    }

    @Override
    public ResponseEntity<Void> editAnamnesis(Integer medicalHistoryNoteId, String anamnesis) {
        return journalService.editAnamnesis(medicalHistoryNoteId, anamnesis);
    }

    @Override
    public ResponseEntity<Void> addDiseaseToNote(Integer medicalHistoryNoteId, Integer diseaseId) {
        return journalService.addDiseaseToNote(medicalHistoryNoteId, diseaseId);
    }

    @Override
    public ResponseEntity<Void> deleteDiseaseFromNote(Integer medicalHistoryNoteId, Integer diseaseId) {
        return journalService.deleteDiseaseFromNote(medicalHistoryNoteId, diseaseId);
    }

    @Override
    public ResponseEntity<Void> addTreatmentToDiseaseInNote(
        Integer medicalHistoryNoteId,
        Integer diseaseId,
        Integer treatmentId
    ) {
        return journalService.addTreatmentToDiseaseInNote(medicalHistoryNoteId, diseaseId, treatmentId);
    }

    @Override
    public ResponseEntity<Void> deleteTreatmentForDiseaseInNote(
        Integer medicalHistoryNoteId,
        Integer diseaseId,
        Integer treatmentId
    ) {
        return journalService.deleteTreatmentForDiseaseInNote(medicalHistoryNoteId, diseaseId, treatmentId);
    }

    @Override
    public ResponseEntity<Void> changeResultsOfTreatmentForDiseaseInNote(
        Integer medicalHistoryNoteId,
        Integer diseaseId,
        Integer treatmentId,
        String resultsOfTreatment
    ) {
        return journalService.changeResultsOfTreatmentForDiseaseInNote(
            medicalHistoryNoteId,
            diseaseId,
            treatmentId,
            resultsOfTreatment
        );
    }

    @Override
    public ResponseEntity<List<RecipeFullInfo>> getRecipesNote(Integer medicalHistoryNoteId) {
        return journalService.getRecipesNote(medicalHistoryNoteId);
    }
}
