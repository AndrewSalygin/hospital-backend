package com.andrewsalygin.controller.medicalProcedures;

import com.andrewsalygin.hospital.api.SuperAdminMedicalProceduresApi;
import com.andrewsalygin.hospital.model.DiseaseFullInfo;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicalProcedureWithoutId;
import com.andrewsalygin.service.interfaces.MedicalProceduresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SuperAdminMedicalProceduresController implements SuperAdminMedicalProceduresApi {

    private final MedicalProceduresService medicalProceduresService;

    @Override
    public ResponseEntity<IdResponse> addMedicalProcedure(MedicalProcedureWithoutId medicalProcedureWithoutId) {
        return medicalProceduresService.addMedicalProcedure(medicalProcedureWithoutId);
    }

    @Override
    public ResponseEntity<DiseaseFullInfo> editMedicalProcedure(
        Integer medicalProcedureId,
        MedicalProcedureWithoutId medicalProcedureWithoutId
    ) {
        return medicalProceduresService.editMedicalProcedure(medicalProcedureId, medicalProcedureWithoutId);
    }

    @Override
    public ResponseEntity<Void> deleteMedicalProcedure(Integer medicalProcedureId) {
        return medicalProceduresService.deleteMedicalProcedure(medicalProcedureId);
    }
}
