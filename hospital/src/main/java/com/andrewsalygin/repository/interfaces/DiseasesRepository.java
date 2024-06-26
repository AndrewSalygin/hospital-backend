package com.andrewsalygin.repository.interfaces;

import com.andrewsalygin.dto.disease.DiseaseFullInfoDTO;
import com.andrewsalygin.dto.disease.DiseaseWithoutIdDTO;
import com.andrewsalygin.dto.patient.PatientShortInfoDTO;
import java.util.List;

public interface DiseasesRepository {

    List<DiseaseFullInfoDTO> getDiseases(Integer limit, Integer offset);

    DiseaseFullInfoDTO getDisease(Integer diseaseId);

    List<PatientShortInfoDTO> getPatientsWithDisease(Integer diseaseId);

    void addDisease(DiseaseWithoutIdDTO diseaseWithoutId);

    void editDisease(Integer diseaseId, DiseaseWithoutIdDTO diseaseWithoutId);
}
