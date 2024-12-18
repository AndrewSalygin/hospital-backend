package com.andrewsalygin.applicationservice.dto.medication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class MedicationWithoutIdDTO {
    private final String medicationName;
    private final String medicationForm;
    private final String dosage;
    private final String manufacturer;
    private final String countryOfManufacture;
    private final LocalDate dateOfManufacture;
    private final LocalDate expireDate;
    private final Boolean isPrescription;
    private final Float price;
    private final Integer availableCount;
}
