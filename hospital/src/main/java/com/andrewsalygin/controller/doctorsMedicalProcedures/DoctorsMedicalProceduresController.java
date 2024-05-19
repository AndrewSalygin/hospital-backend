package com.andrewsalygin.controller.doctorsMedicalProcedures;

import com.andrewsalygin.hospital.api.DoctorsMedicalProceduresApi;
import com.andrewsalygin.hospital.model.DoctorShortInfo;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfo;
import com.andrewsalygin.service.DoctorsMedicalProceduresService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class DoctorsMedicalProceduresController implements DoctorsMedicalProceduresApi {

    private final DoctorsMedicalProceduresService doctorsMedicalProceduresService;

    @Override
    public ResponseEntity<List<DoctorShortInfo>> getDoctorsForProcedure(Integer procedureId) {
        return doctorsMedicalProceduresService.getDoctorsForProcedure(procedureId);
    }

    @Override
    public ResponseEntity<List<MedicalProcedureFullInfo>> getProceduresForDoctor(Integer doctorId) {
        return doctorsMedicalProceduresService.getProceduresForDoctor(doctorId);
    }
}
