package com.andrewsalygin.dto.treatment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class TreatmentFullInfoDTO {
    private final Integer treatmentId;
    private final String treatmentName;
    private final Integer doctorId;
    private final Boolean isDeleted;
}
