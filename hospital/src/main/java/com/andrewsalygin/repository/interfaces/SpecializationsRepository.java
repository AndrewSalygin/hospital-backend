package com.andrewsalygin.repository.interfaces;

import com.andrewsalygin.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.dto.specialization.SpecializationDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface SpecializationsRepository {

    List<SpecializationDTO> getSpecializations();

    List<DoctorShortInfoDTO> getSpecializationsDoctors(Integer specializationId);

    Integer addSpecialization(String specializationName);

    void deleteSpecialization(Integer specializationId);
}
