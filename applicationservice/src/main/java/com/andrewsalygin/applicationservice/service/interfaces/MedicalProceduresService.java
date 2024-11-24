package com.andrewsalygin.applicationservice.service.interfaces;

import com.andrewsalygin.applicationservice.model.DiseaseFullInfo;
import com.andrewsalygin.applicationservice.model.IdResponse;
import com.andrewsalygin.applicationservice.model.MedicalProcedureFullInfo;
import com.andrewsalygin.applicationservice.model.MedicalProcedureWithoutId;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface MedicalProceduresService {

    ResponseEntity<List<MedicalProcedureFullInfo>> getMedicalProcedures(Integer limit, Integer offset);

    ResponseEntity<MedicalProcedureFullInfo> getMedicalProcedure(Integer medicalProcedureId);

    ResponseEntity<IdResponse> addMedicalProcedure(MedicalProcedureWithoutId medicalProcedureWithoutId);

    ResponseEntity<Void> editMedicalProcedure(Integer medicalProcedureId, MedicalProcedureWithoutId medicalProcedureWithoutId);

    ResponseEntity<Void> deleteMedicalProcedure(Integer medicalProcedureId);
}
