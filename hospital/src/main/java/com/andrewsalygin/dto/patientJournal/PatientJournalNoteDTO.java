package com.andrewsalygin.dto.patientJournal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.time.OffsetDateTime;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class PatientJournalNoteDTO {
    private final Integer medicalHistoryNoteId;
    private final Integer patientId;
    private final Integer doctorId;
    private final Boolean initialAdmission;
    private final Boolean discharge;
    private final String patientStatus;
    private final OffsetDateTime admissionDateTime;
    private final Boolean isDeleted;
}
