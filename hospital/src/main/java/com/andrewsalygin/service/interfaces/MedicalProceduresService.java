package com.andrewsalygin.service.interfaces;

import com.andrewsalygin.hospital.model.DiseaseFullInfo;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfo;
import com.andrewsalygin.hospital.model.MedicalProcedureWithoutId;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface MedicalProceduresService {

    ResponseEntity<List<MedicalProcedureFullInfo>> getMedicalProcedures(Integer limit, Integer offset);

    ResponseEntity<MedicalProcedureFullInfo> getMedicalProcedure(Integer medicalProcedureId);

    ResponseEntity<IdResponse> addMedicalProcedure(MedicalProcedureWithoutId medicalProcedureWithoutId);

    ResponseEntity<DiseaseFullInfo> editMedicalProcedure(Integer medicalProcedureId, MedicalProcedureWithoutId medicalProcedureWithoutId);

    ResponseEntity<Void> deleteMedicalProcedure(Integer medicalProcedureId);
}
