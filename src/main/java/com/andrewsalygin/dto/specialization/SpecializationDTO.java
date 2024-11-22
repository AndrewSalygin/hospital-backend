package com.andrewsalygin.dto.specialization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class SpecializationDTO {
    private final Integer specializationId;
    private final String specializationName;
}
