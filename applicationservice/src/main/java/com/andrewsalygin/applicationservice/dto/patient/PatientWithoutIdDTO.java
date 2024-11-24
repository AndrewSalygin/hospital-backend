package com.andrewsalygin.applicationservice.dto.patient;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class PatientWithoutIdDTO {
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String gender;
    private final LocalDate dateOfBirth;
    private final String phoneNumber;
    private final String insuranceInformation;
    private final Boolean isDeleted;
}
