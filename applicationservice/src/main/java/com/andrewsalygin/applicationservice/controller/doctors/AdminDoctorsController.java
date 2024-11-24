package com.andrewsalygin.applicationservice.controller.doctors;

import com.andrewsalygin.applicationservice.api.AdminDoctorsApi;
import com.andrewsalygin.applicationservice.model.DoctorInfo;
import com.andrewsalygin.applicationservice.model.SurgeryShortInfo;
import com.andrewsalygin.applicationservice.service.interfaces.DoctorsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminDoctorsController implements AdminDoctorsApi {

    private final DoctorsService doctorsService;

    @Override
    public ResponseEntity<List<SurgeryShortInfo>> getSurgeriesForDoctor(
        Integer doctorId,
        Integer limit,
        Integer offset
    ) {
        return doctorsService.getSurgeriesForDoctor(doctorId, limit, offset);
    }

    @Override
    public ResponseEntity<DoctorInfo> getFirstAvailableDoctorBySpecializationAndExperience(String specializationName) {
        return doctorsService.getFirstAvailableDoctorBySpecializationAndExperience(specializationName);
    }
}
