package com.andrewsalygin.specializationservice.dto.doctor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DoctorShortInfoDTO {
    private final Integer doctorId;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final LocalDate dateOfBirth;
    private final String gender;
    private final String specializationName;
    private final Integer yearsOfExperience;
    private final Boolean isDeleted;
}
