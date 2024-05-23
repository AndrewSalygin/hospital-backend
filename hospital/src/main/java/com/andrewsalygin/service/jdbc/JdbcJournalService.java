package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.dto.patientJournal.PatientJournalNoteDTO;
import com.andrewsalygin.dto.patientJournal.PatientJournalNoteFullInfoDTO;
import com.andrewsalygin.dto.recipe.RecipeFullInfoDTO;
import com.andrewsalygin.hospital.model.PatientJournalNote;
import com.andrewsalygin.hospital.model.PatientJournalNoteFullInfo;
import com.andrewsalygin.hospital.model.RecipeFullInfo;
import com.andrewsalygin.repository.interfaces.JournalRepository;
import com.andrewsalygin.service.interfaces.JournalService;
import java.lang.reflect.Type;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcJournalService implements JournalService {

    private final JournalRepository journalRepository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<Void> deleteNote(Integer medicalHistoryNoteId) {
        journalRepository.deleteNote(medicalHistoryNoteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override

    public ResponseEntity<List<PatientJournalNote>> getNotes(Integer limit, Integer offset) {
        List<PatientJournalNoteDTO> notesDTO = journalRepository.getNotes(limit, offset);
        Type listType = new TypeToken<List<PatientJournalNote>>() {}.getType();
        List<PatientJournalNote> notes = modelMapper.map(notesDTO, listType);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PatientJournalNoteFullInfo> getNote(Integer medicalHistoryNoteId) {
        PatientJournalNoteFullInfoDTO noteDTO = journalRepository.getNote(medicalHistoryNoteId);
        PatientJournalNoteFullInfo note = modelMapper.map(noteDTO, PatientJournalNoteFullInfo.class);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> editAnamnesis(Integer medicalHistoryNoteId, String anamnesis) {
        journalRepository.editAnamnesis(medicalHistoryNoteId, anamnesis);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addDiseaseToNote(Integer medicalHistoryNoteId, Integer diseaseId) {
        journalRepository.addDiseaseToNote(medicalHistoryNoteId, diseaseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteDiseaseFromNote(Integer medicalHistoryNoteId, Integer diseaseId) {
        journalRepository.deleteDiseaseFromNote(medicalHistoryNoteId, diseaseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addTreatmentToDiseaseInNote(
        Integer medicalHistoryNoteId,
        Integer diseaseId,
        Integer treatmentId
    ) {
        journalRepository.addTreatmentToDiseaseInNote(medicalHistoryNoteId, diseaseId, treatmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteTreatmentForDiseaseInNote(
        Integer medicalHistoryNoteId,
        Integer diseaseId,
        Integer treatmentId
    ) {
        journalRepository.deleteTreatmentForDiseaseInNote(medicalHistoryNoteId, diseaseId, treatmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> changeResultsOfTreatmentForDiseaseInNote(
        Integer medicalHistoryNoteId,
        Integer diseaseId,
        Integer treatmentId,
        String resultsOfTreatment
    ) {
        journalRepository.changeResultsOfTreatmentForDiseaseInNote(medicalHistoryNoteId, diseaseId, treatmentId, resultsOfTreatment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RecipeFullInfo>> getRecipesNote(Integer medicalHistoryNoteId) {
        List<RecipeFullInfoDTO> recipeDTOs = journalRepository.getRecipes(medicalHistoryNoteId);
        Type listType = new TypeToken<List<RecipeFullInfo>>() {}.getType();
        List<RecipeFullInfo> recipes = modelMapper.map(recipeDTOs, listType);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }
}
