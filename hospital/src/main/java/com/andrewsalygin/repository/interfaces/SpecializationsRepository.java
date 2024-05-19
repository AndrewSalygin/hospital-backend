package com.andrewsalygin.repository.interfaces;

import com.andrewsalygin.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.dto.specialization.SpecializationDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface SpecializationsRepository {

    ResponseEntity<List<SpecializationDTO>> getSpecializations();

    ResponseEntity<List<DoctorShortInfoDTO>> getSpecializationsDoctors(Integer specializationId);

    ResponseEntity<List<IdResponse>> addSpecialization(String specializationName);

    ResponseEntity<Void> deleteSpecialization(Integer specializationId);
}
