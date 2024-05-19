package com.andrewsalygin.controller.journal;

import com.andrewsalygin.hospital.api.AdminJournalPatientApi;
import com.andrewsalygin.service.interfaces.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AdminJournalController implements AdminJournalPatientApi {

    private final JournalService journalService;

    @Override
    public ResponseEntity<Void> deleteNote(Integer medicalHistoryNoteId) {
        return journalService.deleteNote(medicalHistoryNoteId);
    }
}
