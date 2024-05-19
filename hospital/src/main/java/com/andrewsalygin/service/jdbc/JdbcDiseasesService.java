package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.DiseaseFullInfo;
import com.andrewsalygin.hospital.model.DiseaseWithoutId;
import com.andrewsalygin.hospital.model.PatientShortInfo;
import com.andrewsalygin.repository.DiseasesRepository;
import com.andrewsalygin.service.DiseasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcDiseasesService implements DiseasesService {

    private final DiseasesRepository diseasesRepository;

    @Override
    public ResponseEntity<List<DiseaseFullInfo>> getDiseases(Integer limit, Integer offset) {
        return null;
    }

    @Override
    public ResponseEntity<DiseaseFullInfo> getDisease(Integer diseaseId) {
        return null;
    }

    @Override
    public ResponseEntity<List<PatientShortInfo>> getPatientsWithDisease(Integer diseaseId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addDisease(DiseaseWithoutId diseaseWithoutId) {
        return null;
    }

    @Override
    public ResponseEntity<DiseaseFullInfo> editDisease(Integer diseaseId, DiseaseWithoutId diseaseWithoutId) {
        return null;
    }
}
