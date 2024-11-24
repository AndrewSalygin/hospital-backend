package com.andrewsalygin.applicationservice.repository.interfaces;

import com.andrewsalygin.applicationservice.dto.disease.DiseaseFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.disease.DiseaseWithoutIdDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientShortInfoDTO;
import java.util.List;

public interface DiseasesRepository {

    List<DiseaseFullInfoDTO> getDiseases(Integer limit, Integer offset);

    DiseaseFullInfoDTO getDisease(Integer diseaseId);

    List<PatientShortInfoDTO> getPatientsWithDisease(Integer diseaseId);

    void addDisease(DiseaseWithoutIdDTO diseaseWithoutId);

    void editDisease(Integer diseaseId, DiseaseWithoutIdDTO diseaseWithoutId);
}
