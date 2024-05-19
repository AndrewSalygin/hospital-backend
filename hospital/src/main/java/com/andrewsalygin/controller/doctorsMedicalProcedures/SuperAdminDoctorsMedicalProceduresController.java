package com.andrewsalygin.controller.doctorsMedicalProcedures;

import com.andrewsalygin.hospital.api.SuperAdminDoctorsMedicalProceduresApi;
import com.andrewsalygin.service.interfaces.DoctorsMedicalProceduresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SuperAdminDoctorsMedicalProceduresController implements SuperAdminDoctorsMedicalProceduresApi {

    private final DoctorsMedicalProceduresService doctorsMedicalProceduresService;

    @Override
    public ResponseEntity<Void> addDoctorProcedure(Integer doctorId, Integer procedureId) {
        return doctorsMedicalProceduresService.addDoctorProcedure(doctorId, procedureId);
    }

    @Override
    public ResponseEntity<Void> deleteDoctorProcedure(Integer doctorId, Integer procedureId) {
        return doctorsMedicalProceduresService.deleteDoctorProcedure(doctorId, procedureId);
    }

}
