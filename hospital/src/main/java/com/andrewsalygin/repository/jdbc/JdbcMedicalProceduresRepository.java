package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.disease.DiseaseFullInfoDTO;
import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureFullInfoDTO;
import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureWithoutIdDTO;
import com.andrewsalygin.hospital.model.DiseaseFullInfo;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfo;
import com.andrewsalygin.hospital.model.MedicalProcedureWithoutId;
import com.andrewsalygin.repository.MedicalProceduresRepository;
import com.andrewsalygin.service.MedicalProceduresService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcMedicalProceduresRepository implements MedicalProceduresRepository {

    private final JdbcClient client;

    @Override
    public List<MedicalProcedureFullInfoDTO> getMedicalProcedures(Integer limit, Integer offset) {
        return List.of();
    }

    @Override
    public MedicalProcedureFullInfoDTO getMedicalProcedure(Integer medicalProcedureId) {
        return null;
    }

    @Override
    public IdResponse addMedicalProcedure(MedicalProcedureWithoutIdDTO medicalProcedureWithoutId) {
        return null;
    }

    @Override
    public DiseaseFullInfoDTO editMedicalProcedure(
        Integer medicalProcedureId,
        MedicalProcedureWithoutIdDTO medicalProcedureWithoutId
    ) {
        return null;
    }

    @Override
    public void deleteMedicalProcedure(Integer medicalProcedureId) {

    }
}
