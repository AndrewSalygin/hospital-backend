package com.andrewsalygin.service;

import com.andrewsalygin.hospital.model.DoctorShortInfo;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.Specialization;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface SpecializationsService {

    ResponseEntity<List<Specialization>> getSpecializations();

    ResponseEntity<List<DoctorShortInfo>> getSpecializationsDoctors(Integer specializationId);

    ResponseEntity<List<IdResponse>> addSpecialization(String specializationName);

    ResponseEntity<Void> deleteSpecialization(Integer specializationId);
}
