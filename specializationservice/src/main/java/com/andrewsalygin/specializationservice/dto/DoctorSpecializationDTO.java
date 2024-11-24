package com.andrewsalygin.specializationservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DoctorSpecializationDTO {
    private final Integer specializationId;
    private final String specializationName;
    private final Integer yearsOfExperience;
}
