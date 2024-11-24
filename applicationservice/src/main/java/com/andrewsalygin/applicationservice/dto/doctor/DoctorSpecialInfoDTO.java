package com.andrewsalygin.applicationservice.dto.doctor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DoctorSpecialInfoDTO {
    private final Integer doctorId;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final LocalDate dateOfBirth;
    private final String gender;
    private final Integer specializationId;
    private String specializationName;
    private final Integer yearsOfExperience;
    private final Boolean isDeleted;
}
