package com.andrewsalygin.controller.medicalProcedures;

import com.andrewsalygin.hospital.api.MedicalProceduresApi;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfo;
import com.andrewsalygin.service.MedicalProceduresService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class MedicalProceduresController implements MedicalProceduresApi {

    private final MedicalProceduresService medicalProceduresService;

    @Override
    public ResponseEntity<List<MedicalProcedureFullInfo>> getMedicalProcedures(Integer limit, Integer offset) {
        return medicalProceduresService.getMedicalProcedures(limit, offset);
    }

    @Override
    public ResponseEntity<MedicalProcedureFullInfo> getMedicalProcedure(Integer medicalProcedureId) {
        return medicalProceduresService.getMedicalProcedure(medicalProcedureId);
    }
}
