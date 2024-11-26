package com.andrewsalygin.applicationservice.dto.doctor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class DoctorSpecializationDTO {
    private Integer specializationId;
    private String specializationName;
    private Integer yearsOfExperience;

    public DoctorSpecializationDTO() {
    }

    public DoctorSpecializationDTO(Integer specializationId, String specializationName, Integer yearsOfExperience) {
        this.specializationId = specializationId;
        this.specializationName = specializationName;
        this.yearsOfExperience = yearsOfExperience;
    }
}
