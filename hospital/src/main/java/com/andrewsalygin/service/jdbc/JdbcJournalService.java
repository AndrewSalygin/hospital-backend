package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.PatientJournalNote;
import com.andrewsalygin.hospital.model.PatientJournalNoteFullInfo;
import com.andrewsalygin.repository.interfaces.JournalRepository;
import com.andrewsalygin.service.interfaces.JournalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcJournalService implements JournalService {

    private final JournalRepository journalRepository;

    @Override
    public ResponseEntity<Void> deleteNote(Integer medicalHistoryNoteId) {
        return null;
    }

    @Override

    public ResponseEntity<List<PatientJournalNote>> getNotes(Integer limit, Integer offset) {
        return null;
    }

    @Override
    public ResponseEntity<PatientJournalNoteFullInfo> getNote(Integer medicalHistoryNoteId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> editAnamnesis(Integer medicalHistoryNoteId, String anamnesis) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addDiseaseToNote(Integer medicalHistoryNoteId, Integer diseaseId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteDiseaseFromNote(Integer medicalHistoryNoteId, Integer diseaseId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addTreatmentToDiseaseInNote(
        Integer medicalHistoryNoteId,
        Integer diseaseId,
        Integer treatmentId
    ) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteTreatmentForDiseaseInNote(
        Integer medicalHistoryNoteId,
        Integer diseaseId,
        Integer treatmentId
    ) {
        return null;
    }

    @Override
    public ResponseEntity<Void> changeResultsOfTreatmentForDiseaseInNote(
        Integer medicalHistoryNoteId,
        Integer diseaseId,
        Integer treatmentId,
        String resultsOfTreatment
    ) {
        return null;
    }
}
