package com.andrewsalygin.applicationservice.controller.medicalProcedures;

import com.andrewsalygin.applicationservice.api.MedicalProceduresApi;
import com.andrewsalygin.applicationservice.model.MedicalProcedureFullInfo;
import com.andrewsalygin.applicationservice.service.interfaces.MedicalProceduresService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
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
