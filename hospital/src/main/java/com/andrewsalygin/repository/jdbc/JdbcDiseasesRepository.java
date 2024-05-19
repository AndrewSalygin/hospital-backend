package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.disease.DiseaseFullInfoDTO;
import com.andrewsalygin.dto.disease.DiseaseWithoutIdDTO;
import com.andrewsalygin.dto.patient.PatientShortInfoDTO;
import com.andrewsalygin.hospital.model.DiseaseFullInfo;
import com.andrewsalygin.hospital.model.DiseaseWithoutId;
import com.andrewsalygin.hospital.model.PatientShortInfo;
import com.andrewsalygin.repository.DiseasesRepository;
import com.andrewsalygin.service.DiseasesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcDiseasesRepository implements DiseasesRepository {

    private final JdbcClient client;

    @Override
    public List<DiseaseFullInfoDTO> getDiseases(Integer limit, Integer offset) {
        return List.of();
    }

    @Override
    public DiseaseFullInfoDTO getDisease(Integer diseaseId) {
        return null;
    }

    @Override
    public List<PatientShortInfoDTO> getPatientsWithDisease(Integer diseaseId) {
        return List.of();
    }

    @Override
    public void addDisease(DiseaseWithoutIdDTO diseaseWithoutId) {

    }

    @Override
    public DiseaseFullInfoDTO editDisease(Integer diseaseId, DiseaseWithoutIdDTO diseaseWithoutId) {
        return null;
    }
}
