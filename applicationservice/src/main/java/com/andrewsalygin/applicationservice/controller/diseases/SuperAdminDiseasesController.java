package com.andrewsalygin.applicationservice.controller.diseases;

import com.andrewsalygin.applicationservice.api.SuperAdminDiseasesApi;
import com.andrewsalygin.applicationservice.model.DiseaseFullInfo;
import com.andrewsalygin.applicationservice.model.DiseaseWithoutId;
import com.andrewsalygin.applicationservice.service.interfaces.DiseasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuperAdminDiseasesController implements SuperAdminDiseasesApi {

    private final DiseasesService diseasesService;

    @Override
    public ResponseEntity<Void> addDisease(DiseaseWithoutId diseaseWithoutId) {
        return diseasesService.addDisease(diseaseWithoutId);
    }

    @Override
    public ResponseEntity<Void> editDisease(Integer diseaseId, DiseaseWithoutId diseaseWithoutId) {
        return diseasesService.editDisease(diseaseId, diseaseWithoutId);
    }
}
