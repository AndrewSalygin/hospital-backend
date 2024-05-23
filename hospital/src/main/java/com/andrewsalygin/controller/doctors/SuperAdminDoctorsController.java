package com.andrewsalygin.controller.doctors;

import com.andrewsalygin.hospital.api.SuperAdminDoctorsApi;
import com.andrewsalygin.hospital.model.DoctorAddRequest;
import com.andrewsalygin.hospital.model.DoctorFullInfo;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.service.interfaces.DoctorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SuperAdminDoctorsController implements SuperAdminDoctorsApi {

    private final DoctorsService doctorsService;

    @Override
    public ResponseEntity<Void> deleteDoctor(Integer doctorId) {
        return doctorsService.deleteDoctor(doctorId);
    }

    @Override
    public ResponseEntity<Void> addSpecializationToDoctor(
        Integer doctorId,
        Integer specializationId,
        Integer yearsOfExperience
    ) {
        return doctorsService.addSpecializationToDoctor(doctorId, specializationId, yearsOfExperience);
    }

    @Override
    public ResponseEntity<Void> changeSpecializationExperienceDoctor(
        Integer doctorId,
        Integer specializationId,
        Integer yearsOfExperience
    ) {
        return doctorsService.changeSpecializationExperienceDoctor(doctorId, specializationId, yearsOfExperience);
    }

    @Override
    public ResponseEntity<IdResponse> addDoctor(DoctorAddRequest doctorAddRequest) {
        return doctorsService.addDoctor(doctorAddRequest);
    }

    @Override
    public ResponseEntity<Void> updateDoctor(Integer doctorId, DoctorAddRequest doctorAddRequest) {
        return doctorsService.updateDoctor(doctorId, doctorAddRequest);
    }

    @Override
    public ResponseEntity<Void> restoreDoctor(Integer doctorId) {
        return doctorsService.restoreDoctor(doctorId);
    }
}
