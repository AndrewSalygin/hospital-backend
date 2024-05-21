package com.andrewsalygin.dto.patientJournal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class PatientJournalNoteFullInfoDTO {
    private final Integer medicalHistoryNoteId;
    private final Integer patientId;
    private final Integer doctorId;
    private final Boolean initialAdmission;
    private final Boolean discharge;
    private final String patientStatus;
    private final OffsetDateTime admissionDateTime;
    private final String anamnesis;

    @Setter
    private List<PatientJournalNoteFullInfoDiseaseListInnerDTO> diseaseList;

    private final Boolean isDeleted;
}
