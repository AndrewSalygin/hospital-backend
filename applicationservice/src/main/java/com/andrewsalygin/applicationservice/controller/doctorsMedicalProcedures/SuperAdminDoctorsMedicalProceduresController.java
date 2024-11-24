package com.andrewsalygin.applicationservice.controller.doctorsMedicalProcedures;

import com.andrewsalygin.applicationservice.api.SuperAdminDoctorsMedicalProceduresApi;
import com.andrewsalygin.applicationservice.service.interfaces.DoctorsMedicalProceduresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
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
