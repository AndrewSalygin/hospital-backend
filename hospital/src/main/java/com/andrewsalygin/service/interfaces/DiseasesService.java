package com.andrewsalygin.service.interfaces;

import com.andrewsalygin.hospital.model.DiseaseFullInfo;
import com.andrewsalygin.hospital.model.DiseaseWithoutId;
import com.andrewsalygin.hospital.model.PatientShortInfo;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface DiseasesService {

    ResponseEntity<List<DiseaseFullInfo>> getDiseases(Integer limit, Integer offset);

    ResponseEntity<DiseaseFullInfo> getDisease(Integer diseaseId);

    ResponseEntity<List<PatientShortInfo>> getPatientsWithDisease(Integer diseaseId);

    ResponseEntity<Void> addDisease(DiseaseWithoutId diseaseWithoutId);

    ResponseEntity<DiseaseFullInfo> editDisease(Integer diseaseId, DiseaseWithoutId diseaseWithoutId);
}
