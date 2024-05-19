package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.DiseaseFullInfo;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfo;
import com.andrewsalygin.hospital.model.MedicalProcedureWithoutId;
import com.andrewsalygin.repository.interfaces.MedicalProceduresRepository;
import com.andrewsalygin.service.interfaces.MedicalProceduresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcMedicalProceduresService implements MedicalProceduresService {

    private final MedicalProceduresRepository repository;

    @Override
    public ResponseEntity<List<MedicalProcedureFullInfo>> getMedicalProcedures(Integer limit, Integer offset) {
        return null;
    }

    @Override
    public ResponseEntity<MedicalProcedureFullInfo> getMedicalProcedure(Integer medicalProcedureId) {
        return null;
    }

    @Override
    public ResponseEntity<IdResponse> addMedicalProcedure(MedicalProcedureWithoutId medicalProcedureWithoutId) {
        return null;
    }

    @Override
    public ResponseEntity<DiseaseFullInfo> editMedicalProcedure(
        Integer medicalProcedureId,
        MedicalProcedureWithoutId medicalProcedureWithoutId
    ) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteMedicalProcedure(Integer medicalProcedureId) {
        return null;
    }
}
