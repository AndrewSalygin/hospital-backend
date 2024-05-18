package com.andrewsalygin.controller.doctors;

import com.andrewsalygin.hospital.api.DoctorsApi;
import com.andrewsalygin.hospital.model.DoctorFullInfo;
import com.andrewsalygin.hospital.model.DoctorShortInfo;
import com.andrewsalygin.hospital.model.DoctorSpecialization;
import org.springframework.http.ResponseEntity;
import java.util.List;

public class DoctorsController implements DoctorsApi {

    @Override
    public ResponseEntity<List<DoctorShortInfo>> getDoctors(Integer limit, Integer offset) {
        return DoctorsApi.super.getDoctors(limit, offset);
    }

    @Override
    public ResponseEntity<DoctorFullInfo> getDoctor(Integer doctorId) {
        return DoctorsApi.super.getDoctor(doctorId);
    }

    @Override
    public ResponseEntity<List<DoctorSpecialization>> getDoctorSpecializations(Integer doctorId) {
        return DoctorsApi.super.getDoctorSpecializations(doctorId);
    }
}
