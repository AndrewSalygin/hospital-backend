package com.andrewsalygin.applicationservice.dto.doctor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DoctorFullInfoDTO {
    private final Integer doctorId;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final LocalDate dateOfBirth;
    private final String gender;
    private final String education;
    private final String phoneNumber;
    private final String emailAddress;
    private final Boolean isDeleted;
}
