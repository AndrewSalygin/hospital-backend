package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.patientJournal.PatientJournalNoteDTO;
import com.andrewsalygin.dto.patientJournal.PatientJournalNoteFullInfoDTO;
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

    }

    @Override
    public List<PatientJournalNoteDTO> getNotes(Integer limit, Integer offset) {
        return List.of();
    }

    @Override
    public PatientJournalNoteFullInfoDTO getNote(Integer medicalHistoryNoteId) {
        return null;
    }

    @Override
    public void editAnamnesis(Integer medicalHistoryNoteId, String anamnesis) {

    }

    @Override
    public void addDiseaseToNote(Integer medicalHistoryNoteId, Integer diseaseId) {

    }

    @Override
    public void deleteDiseaseFromNote(Integer medicalHistoryNoteId, Integer diseaseId) {

    }

    @Override
    public void addTreatmentToDiseaseInNote(Integer medicalHistoryNoteId, Integer diseaseId, Integer treatmentId) {

    }

    @Override
    public void deleteTreatmentForDiseaseInNote(Integer medicalHistoryNoteId, Integer diseaseId, Integer treatmentId) {

    }

    @Override
    public void changeResultsOfTreatmentForDiseaseInNote(
        Integer medicalHistoryNoteId,
        Integer diseaseId,
        Integer treatmentId,
        String resultsOfTreatment
    ) {

    }
}
