package com.andrewsalygin.applicationservice.dto.surgeries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class DoctorWithWorkingHoursDTO {
    private final Integer doctorId;
    private final Float workingHours;
    private final Float scheduledWorkingHours;
}
