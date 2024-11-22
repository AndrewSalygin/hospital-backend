package com.andrewsalygin.dto.patient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class PatientWithDiseaseDTO {
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String gender;
    private final LocalDate dateOfBirth;
    private final String diseaseCode;
    private final String diseaseName;
    private final Boolean dispensaryAccounting;
}
