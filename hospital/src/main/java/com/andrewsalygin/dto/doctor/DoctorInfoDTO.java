package com.andrewsalygin.dto.doctor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DoctorInfoDTO {
    private final Integer doctorId;
    private final String firstName;
    private final String lastName;
}
