package com.andrewsalygin.applicationservice.service.interfaces;

import com.andrewsalygin.applicationservice.model.DiseaseFullInfo;
import com.andrewsalygin.applicationservice.model.DiseaseWithoutId;
import com.andrewsalygin.applicationservice.model.PatientShortInfo;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface DiseasesService {

    ResponseEntity<List<DiseaseFullInfo>> getDiseases(Integer limit, Integer offset);

    ResponseEntity<DiseaseFullInfo> getDisease(Integer diseaseId);

    ResponseEntity<List<PatientShortInfo>> getPatientsWithDisease(Integer diseaseId);

    ResponseEntity<Void> addDisease(DiseaseWithoutId diseaseWithoutId);

    ResponseEntity<Void> editDisease(Integer diseaseId, DiseaseWithoutId diseaseWithoutId);
}
