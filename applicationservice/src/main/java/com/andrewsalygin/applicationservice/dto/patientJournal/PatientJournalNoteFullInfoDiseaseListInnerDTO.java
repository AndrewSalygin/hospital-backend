package com.andrewsalygin.applicationservice.dto.patientJournal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class PatientJournalNoteFullInfoDiseaseListInnerDTO {
    private final Integer diseaseId;
    private final Integer treatmentId;
    private final String resultsOfTreatment;
}
