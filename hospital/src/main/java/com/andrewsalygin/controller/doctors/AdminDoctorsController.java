package com.andrewsalygin.controller.doctors;

import com.andrewsalygin.hospital.api.AdminApi;
import com.andrewsalygin.hospital.api.DoctorsApi;
import com.andrewsalygin.hospital.api.SurgeriesApi;
import com.andrewsalygin.hospital.model.DoctorInfo;
import com.andrewsalygin.hospital.model.SurgeryShortInfo;
import org.springframework.http.ResponseEntity;
import java.util.List;

public class AdminDoctorsController implements DoctorsApi {

    @Override
    public ResponseEntity<List<SurgeryShortInfo>> getSurgeriesForDoctor(Integer doctorId) {
        return DoctorsApi.super.getSurgeriesForDoctor(doctorId);
    }

    @Override
    public ResponseEntity<DoctorInfo> getFirstAvailableDoctorBySpecializationAndExperience(String specializationName) {
        return DoctorsApi.super.getFirstAvailableDoctorBySpecializationAndExperience(specializationName);
    }
}
