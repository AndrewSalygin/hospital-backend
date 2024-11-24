package com.andrewsalygin.applicationservice.controller.doctorsMedicalProcedures;

import com.andrewsalygin.applicationservice.api.DoctorsMedicalProceduresApi;
import com.andrewsalygin.applicationservice.model.DoctorShortInfo;
import com.andrewsalygin.applicationservice.model.MedicalProcedureFullInfo;
import com.andrewsalygin.applicationservice.service.interfaces.DoctorsMedicalProceduresService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
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
