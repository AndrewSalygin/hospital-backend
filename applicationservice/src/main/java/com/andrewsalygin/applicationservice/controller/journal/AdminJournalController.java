package com.andrewsalygin.applicationservice.controller.journal;

import com.andrewsalygin.applicationservice.api.AdminJournalPatientApi;
import com.andrewsalygin.applicationservice.service.interfaces.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminJournalController implements AdminJournalPatientApi {

    private final JournalService journalService;

    @Override
    public ResponseEntity<Void> deleteNote(Integer medicalHistoryNoteId) {
        return journalService.deleteNote(medicalHistoryNoteId);
    }
}
