package com.andrewsalygin.applicationservice.service.jdbc;

import com.andrewsalygin.applicationservice.dto.medicalProcedure.MedicalProcedureFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.medicalProcedure.MedicalProcedureWithoutIdDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientShortInfoDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientWithoutIdDTO;
import com.andrewsalygin.applicationservice.model.DiseaseFullInfo;
import com.andrewsalygin.applicationservice.model.IdResponse;
import com.andrewsalygin.applicationservice.model.MedicalProcedureFullInfo;
import com.andrewsalygin.applicationservice.model.MedicalProcedureWithoutId;
import com.andrewsalygin.applicationservice.model.PatientFullInfo;
import com.andrewsalygin.applicationservice.model.PatientShortInfo;
import com.andrewsalygin.applicationservice.repository.interfaces.MedicalProceduresRepository;
import com.andrewsalygin.applicationservice.service.interfaces.MedicalProceduresService;
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
public class JdbcMedicalProceduresService implements MedicalProceduresService {

    private final MedicalProceduresRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<MedicalProcedureFullInfo>> getMedicalProcedures(Integer limit, Integer offset) {
        List<MedicalProcedureFullInfoDTO> result = repository.getMedicalProcedures(limit, offset);

        Type listType = new TypeToken<List<MedicalProcedureFullInfo>>() {
        }.getType();
        List<MedicalProcedureFullInfo> response = modelMapper.map(result, listType);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MedicalProcedureFullInfo> getMedicalProcedure(Integer medicalProcedureId) {
        MedicalProcedureFullInfoDTO result = repository.getMedicalProcedure(medicalProcedureId);

        MedicalProcedureFullInfo response = modelMapper.map(result, MedicalProcedureFullInfo.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<IdResponse> addMedicalProcedure(MedicalProcedureWithoutId medicalProcedureWithoutId) {
        MedicalProcedureWithoutIdDTO medicalProcedureWithoutIdDTO =
            modelMapper.map(medicalProcedureWithoutId, MedicalProcedureWithoutIdDTO.class);
        Integer medicalProcedureId = repository.addMedicalProcedure(medicalProcedureWithoutIdDTO);
        IdResponse response = new IdResponse();
        response.setId(medicalProcedureId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> editMedicalProcedure(
        Integer medicalProcedureId,
        MedicalProcedureWithoutId medicalProcedureWithoutId
    ) {
        MedicalProcedureWithoutIdDTO medicalProcedureWithoutIdDTO =
            modelMapper.map(medicalProcedureWithoutId, MedicalProcedureWithoutIdDTO.class);
        repository.editMedicalProcedure(medicalProcedureId, medicalProcedureWithoutIdDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteMedicalProcedure(Integer medicalProcedureId) {
        repository.deleteMedicalProcedure(medicalProcedureId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
