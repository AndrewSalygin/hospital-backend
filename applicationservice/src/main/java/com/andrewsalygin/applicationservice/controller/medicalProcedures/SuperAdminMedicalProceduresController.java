package com.andrewsalygin.applicationservice.controller.medicalProcedures;

import com.andrewsalygin.applicationservice.api.SuperAdminMedicalProceduresApi;
import com.andrewsalygin.applicationservice.model.DiseaseFullInfo;
import com.andrewsalygin.applicationservice.model.IdResponse;
import com.andrewsalygin.applicationservice.model.MedicalProcedureWithoutId;
import com.andrewsalygin.applicationservice.service.interfaces.MedicalProceduresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuperAdminMedicalProceduresController implements SuperAdminMedicalProceduresApi {

    private final MedicalProceduresService medicalProceduresService;

    @Override
    public ResponseEntity<IdResponse> addMedicalProcedure(MedicalProcedureWithoutId medicalProcedureWithoutId) {
        return medicalProceduresService.addMedicalProcedure(medicalProcedureWithoutId);
    }

    @Override
    public ResponseEntity<Void> editMedicalProcedure(
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
