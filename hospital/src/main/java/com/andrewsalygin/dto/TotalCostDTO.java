package com.andrewsalygin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class TotalCostDTO {
    private final Float totalMedicationsCost;
    private final Float totalMedicalProceduresCost;
    private final Float totalMedicationCost;
}
