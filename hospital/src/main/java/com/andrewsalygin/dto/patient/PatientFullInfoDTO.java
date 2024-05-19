package com.andrewsalygin.dto.patient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class PatientFullInfoDTO {
    private final Integer patientId;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String gender;
    private final LocalDate dateOfBirth;
    private final String phoneNumber;
    private final String insuranceInformation;
    private final Boolean isDeleted;
}
