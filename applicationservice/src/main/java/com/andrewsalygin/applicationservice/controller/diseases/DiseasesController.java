package com.andrewsalygin.applicationservice.controller.diseases;

import com.andrewsalygin.applicationservice.api.DiseasesApi;
import com.andrewsalygin.applicationservice.model.DiseaseFullInfo;
import com.andrewsalygin.applicationservice.model.PatientShortInfo;
import com.andrewsalygin.applicationservice.service.interfaces.DiseasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiseasesController implements DiseasesApi {

    private final DiseasesService diseasesService;

    @Override
    public ResponseEntity<List<DiseaseFullInfo>> getDiseases(Integer limit, Integer offset) {
        return diseasesService.getDiseases(limit, offset);
    }

    @Override
    public ResponseEntity<DiseaseFullInfo> getDisease(Integer diseaseId) {
        return diseasesService.getDisease(diseaseId);
    }

    @Override
    public ResponseEntity<List<PatientShortInfo>> getPatientsWithDisease(Integer diseaseId) {
        return diseasesService.getPatientsWithDisease(diseaseId);
    }
}
