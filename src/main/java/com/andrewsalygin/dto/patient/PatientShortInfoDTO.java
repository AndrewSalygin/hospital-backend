package com.andrewsalygin.dto.patient;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class PatientShortInfoDTO {
    private final Integer patientId;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String gender;
    private final LocalDate dateOfBirth;
    private final Boolean isDeleted;
}
