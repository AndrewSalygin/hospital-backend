package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.DoctorFullInfo;
import com.andrewsalygin.hospital.model.DoctorInfo;
import com.andrewsalygin.hospital.model.DoctorShortInfo;
import com.andrewsalygin.hospital.model.DoctorSpecialization;
import com.andrewsalygin.hospital.model.SurgeryShortInfo;
import com.andrewsalygin.repository.DoctorsRepository;
import com.andrewsalygin.service.DoctorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcDoctorsService implements DoctorsService {

    private final DoctorsRepository doctorsRepository;

    @Override
    public ResponseEntity<List<SurgeryShortInfo>> getSurgeriesForDoctor(Integer doctorId) {
        return null;
    }

    @Override
    public ResponseEntity<DoctorInfo> getFirstAvailableDoctorBySpecializationAndExperience(String specializationName) {
        return null;
    }

    @Override
    public ResponseEntity<List<DoctorShortInfo>> getDoctors(Integer limit, Integer offset) {
        return null;
    }

    @Override
    public ResponseEntity<DoctorFullInfo> getDoctor(Integer doctorId) {
        return null;
    }

    @Override
    public ResponseEntity<List<DoctorSpecialization>> getDoctorSpecializations(Integer doctorId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteDoctor(Integer doctorId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addSpecializationToDoctor(
        Integer doctorId,
        Integer specializationId,
        Integer yearsOfExperience
    ) {
        return null;
    }

    @Override
    public ResponseEntity<Void> changeSpecializationExperienceDoctor(
        Integer doctorId,
        Integer specializationId,
        Integer yearsOfExperience
    ) {
        return null;
    }
}
