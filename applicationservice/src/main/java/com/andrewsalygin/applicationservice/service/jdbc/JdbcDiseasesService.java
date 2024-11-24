package com.andrewsalygin.applicationservice.service.jdbc;

import com.andrewsalygin.applicationservice.dto.disease.DiseaseFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.disease.DiseaseWithoutIdDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientShortInfoDTO;
import com.andrewsalygin.applicationservice.model.DiseaseFullInfo;
import com.andrewsalygin.applicationservice.model.DiseaseWithoutId;
import com.andrewsalygin.applicationservice.model.PatientShortInfo;
import com.andrewsalygin.applicationservice.repository.interfaces.DiseasesRepository;
import com.andrewsalygin.applicationservice.service.interfaces.DiseasesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcDiseasesService implements DiseasesService {

    private final DiseasesRepository diseasesRepository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<DiseaseFullInfo>> getDiseases(Integer limit, Integer offset) {
        List<DiseaseFullInfoDTO> resultFromRepository = diseasesRepository.getDiseases(limit, offset);

        Type listType = new TypeToken<List<DiseaseFullInfo>>() {}.getType();
        List<DiseaseFullInfo> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DiseaseFullInfo> getDisease(Integer diseaseId) {
        DiseaseFullInfoDTO diseaseFullInfoDTO = diseasesRepository.getDisease(diseaseId);
        DiseaseFullInfo diseaseFullInfo = modelMapper.map(diseaseFullInfoDTO, DiseaseFullInfo.class);

        return new ResponseEntity<>(diseaseFullInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PatientShortInfo>> getPatientsWithDisease(Integer diseaseId) {
        List<PatientShortInfoDTO> resultFromRepository = diseasesRepository.getPatientsWithDisease(diseaseId);

        Type listType = new TypeToken<List<PatientShortInfo>>() {}.getType();
        List<PatientShortInfo> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addDisease(DiseaseWithoutId diseaseWithoutId) {
        DiseaseWithoutIdDTO diseaseWithoutIdDTO = modelMapper.map(diseaseWithoutId, DiseaseWithoutIdDTO.class);
        diseasesRepository.addDisease(diseaseWithoutIdDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> editDisease(Integer diseaseId, DiseaseWithoutId diseaseWithoutId) {
        DiseaseWithoutIdDTO diseaseWithoutIdDTO = modelMapper.map(diseaseWithoutId, DiseaseWithoutIdDTO.class);
        diseasesRepository.editDisease(diseaseId, diseaseWithoutIdDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
