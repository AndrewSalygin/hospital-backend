package com.andrewsalygin.dto.medication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class NewInfoMedicationDTO {
    private final Integer availableCount;
    private final Float price;
    private final LocalDate dateOfManufacture;
    private final LocalDate expireDate;
}
