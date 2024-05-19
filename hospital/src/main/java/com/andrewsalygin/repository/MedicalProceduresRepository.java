package com.andrewsalygin.repository;

import com.andrewsalygin.dto.disease.DiseaseFullInfoDTO;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureFullInfoDTO;
import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureWithoutIdDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface MedicalProceduresRepository {

    List<MedicalProcedureFullInfoDTO> getMedicalProcedures(Integer limit, Integer offset);

    MedicalProcedureFullInfoDTO getMedicalProcedure(Integer medicalProcedureId);

    IdResponse addMedicalProcedure(MedicalProcedureWithoutIdDTO medicalProcedureWithoutId);

    DiseaseFullInfoDTO editMedicalProcedure(Integer medicalProcedureId, MedicalProcedureWithoutIdDTO medicalProcedureWithoutId);

    void deleteMedicalProcedure(Integer medicalProcedureId);
}
