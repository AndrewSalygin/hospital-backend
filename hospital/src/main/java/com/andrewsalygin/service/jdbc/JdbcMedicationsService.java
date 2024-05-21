package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureFullInfoDTO;
import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureWithoutIdDTO;
import com.andrewsalygin.dto.medication.MedicationFullInfoDTO;
import com.andrewsalygin.dto.medication.MedicationWithoutIdDTO;
import com.andrewsalygin.dto.medication.NewInfoMedicationDTO;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfo;
import com.andrewsalygin.hospital.model.MedicationFullInfo;
import com.andrewsalygin.hospital.model.MedicationWithoutId;
import com.andrewsalygin.hospital.model.NewInfoMedication;
import com.andrewsalygin.repository.interfaces.MedicationsRepository;
import com.andrewsalygin.service.interfaces.MedicationsService;
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
public class JdbcMedicationsService implements MedicationsService {

    private final MedicationsRepository medicationsRepository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<Void> setNewMedicationFeatures(Integer medicationId, NewInfoMedication newInfoMedication) {
        NewInfoMedicationDTO newInfoMedicationDTO =
            modelMapper.map(newInfoMedication, NewInfoMedicationDTO.class);
        medicationsRepository.setNewMedicationFeatures(medicationId, newInfoMedicationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<IdResponse> addMedication(MedicationWithoutId medicationWithoutId) {
        MedicationWithoutIdDTO medicationWithoutIdDTO =
            modelMapper.map(medicationWithoutId, MedicationWithoutIdDTO.class);
        Integer result = medicationsRepository.addMedication(medicationWithoutIdDTO);
        IdResponse response = new IdResponse();
        response.setId(result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteMedication(Integer medicationId) {
        medicationsRepository.deleteMedication(medicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MedicationFullInfo>> getMedications(Integer limit, Integer offset) {
        List<MedicationFullInfoDTO> result = medicationsRepository.getMedications(limit, offset);

        Type listType = new TypeToken<List<MedicationFullInfo>>() {
        }.getType();
        List<MedicationFullInfo> response = modelMapper.map(result, listType);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MedicationFullInfo> getMedication(Integer medicationId) {
        MedicationFullInfoDTO medicationDTO = medicationsRepository.getMedication(medicationId);
        MedicationFullInfo medication = modelMapper.map(medicationDTO, MedicationFullInfo.class);
        return new ResponseEntity<>(medication, HttpStatus.OK);
    }
}
