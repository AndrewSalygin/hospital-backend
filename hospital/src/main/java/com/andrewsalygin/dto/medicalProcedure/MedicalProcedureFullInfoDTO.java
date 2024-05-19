package com.andrewsalygin.dto.medicalProcedure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class MedicalProcedureFullInfoDTO {
    private final Integer medicalProcedureId;
    private final String medicalProcedureName;
    private final Float price;
}
