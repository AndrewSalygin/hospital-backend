package com.andrewsalygin.applicationservice.dto.medicalProcedure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class MedicalProcedureFullInfoWithTreatmentDaysDTO {
    private final Integer medicalProcedureId;
    private final String medicalProcedureName;
    private final Float price;
    private final Integer amount;
    private final String doctorInstructions;
}
