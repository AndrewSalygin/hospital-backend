package com.andrewsalygin.dto.disease;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DiseaseFullInfoDTO {
    private final Integer diseaseId;
    private final String diseaseName;
    private final String diseaseCode;
    private final String commonDiseaseDuration;
}
