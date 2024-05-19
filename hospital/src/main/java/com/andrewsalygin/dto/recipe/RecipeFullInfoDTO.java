package com.andrewsalygin.dto.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class RecipeFullInfoDTO {
    private final Integer recipeId;
    private final LocalDate admissionDateTime;
    private final LocalDate expirationDate;
    private final String medicationName;
    private final String dosage;
    private final String patientLastName;
    private final String patientFirstName;
    private final String patientMiddleName;
    private final String doctorLastName;
    private final String doctorFirstName;
    private final String doctorMiddleName;
}
