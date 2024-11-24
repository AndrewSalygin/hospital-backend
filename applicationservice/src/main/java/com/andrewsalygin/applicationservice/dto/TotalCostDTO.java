package com.andrewsalygin.applicationservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class TotalCostDTO {
    private final Float totalMedicationCost;
}
