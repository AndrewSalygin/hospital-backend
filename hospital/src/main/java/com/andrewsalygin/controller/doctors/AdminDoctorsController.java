package com.andrewsalygin.controller.doctors;

import com.andrewsalygin.hospital.api.AdminDoctorsApi;
import com.andrewsalygin.hospital.model.DoctorInfo;
import com.andrewsalygin.hospital.model.SurgeryShortInfo;
import com.andrewsalygin.service.interfaces.DoctorsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AdminDoctorsController implements AdminDoctorsApi {

    private final DoctorsService doctorsService;

    @Override
    public ResponseEntity<List<SurgeryShortInfo>> getSurgeriesForDoctor(Integer doctorId) {
        return doctorsService.getSurgeriesForDoctor(doctorId);
    }

    @Override
    public ResponseEntity<DoctorInfo> getFirstAvailableDoctorBySpecializationAndExperience(String specializationName) {
        return doctorsService.getFirstAvailableDoctorBySpecializationAndExperience(specializationName);
    }
}
