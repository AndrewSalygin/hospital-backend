package com.andrewsalygin.dto.treatment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class TreatmentPriceDTO {
    private final Float totalMedicationsCost;
    private final Float totalMedicalProceduresCost;
    private final Float totalCost;
}
