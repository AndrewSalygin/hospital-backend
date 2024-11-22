package com.andrewsalygin.dto.surgeries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DoctorSurgeryCountDTO {
    private final Integer doctorId;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final Integer surgeriesCount;
}
