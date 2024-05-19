package com.andrewsalygin.controller.diseases;

import com.andrewsalygin.hospital.api.SuperAdminDiseasesApi;
import com.andrewsalygin.hospital.model.DiseaseFullInfo;
import com.andrewsalygin.hospital.model.DiseaseWithoutId;
import com.andrewsalygin.service.DiseasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SuperAdminDiseasesController implements SuperAdminDiseasesApi {

    private final DiseasesService diseasesService;

    @Override
    public ResponseEntity<Void> addDisease(DiseaseWithoutId diseaseWithoutId) {
        return diseasesService.addDisease(diseaseWithoutId);
    }

    @Override
    public ResponseEntity<DiseaseFullInfo> editDisease(Integer diseaseId, DiseaseWithoutId diseaseWithoutId) {
        return diseasesService.editDisease(diseaseId, diseaseWithoutId);
    }
}
