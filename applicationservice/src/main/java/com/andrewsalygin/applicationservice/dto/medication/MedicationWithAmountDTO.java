package com.andrewsalygin.applicationservice.dto.medication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class MedicationWithAmountDTO {
    private final Integer medicationId;
    private final Integer amount;
}
