package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureFullInfoWithTreatmentDaysDTO;
import com.andrewsalygin.dto.medication.MedicationsFullInfoWithTreatmentDaysDTO;
import com.andrewsalygin.dto.treatment.TreatmentFullInfoDTO;
import com.andrewsalygin.dto.treatment.TreatmentPriceDTO;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfoWithTreatmentDays;
import com.andrewsalygin.hospital.model.MedicationsFullInfoWithTreatmentDays;
import com.andrewsalygin.hospital.model.TreatmentFullInfo;
import com.andrewsalygin.hospital.model.TreatmentPrice;
import com.andrewsalygin.repository.interfaces.TreatmentRepository;
import com.andrewsalygin.service.interfaces.TreatmentService;
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
public class JdbcTreatmentService implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<IdResponse> addTreatment(String treatmentName, Integer doctorId) {
        Integer treatmentId = treatmentRepository.addTreatment(treatmentName, doctorId);
        IdResponse response = new IdResponse();
        response.setId(treatmentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteTreatment(Integer treatmentId) {
        treatmentRepository.deleteTreatment(treatmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MedicalProcedureFullInfoWithTreatmentDays>> getMedicalProceduresWithTreatment(Integer treatmentId) {
        List<MedicalProcedureFullInfoWithTreatmentDaysDTO> resultFromRepository =
            treatmentRepository.getMedicalProceduresWithTreatment(treatmentId);
        Type listType = new TypeToken<List<MedicalProcedureFullInfoWithTreatmentDays>>() {
        }.getType();
        List<MedicalProcedureFullInfoWithTreatmentDays> result = modelMapper.map(resultFromRepository, listType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addMedicalProcedureToTreatment(
        Integer treatmentId,
        Integer procedureId,
        Integer amount,
        String doctorInstructions
    ) {
        treatmentRepository.addMedicalProcedureToTreatment(treatmentId, procedureId, amount, doctorInstructions);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MedicationsFullInfoWithTreatmentDays>> getMedicationsWithTreatment(Integer treatmentId) {
        List<MedicationsFullInfoWithTreatmentDaysDTO> resultFromRepository =
            treatmentRepository.getMedicationsWithTreatment(treatmentId);
        Type listType = new TypeToken<List<MedicationsFullInfoWithTreatmentDays>>() {
        }.getType();
        List<MedicationsFullInfoWithTreatmentDays> result = modelMapper.map(resultFromRepository, listType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addMedicationToTreatment(
        Integer treatmentId,
        Integer medicationId,
        Integer amount,
        String doctorInstructions
    ) {
        treatmentRepository.addMedicationToTreatment(treatmentId, medicationId, amount, doctorInstructions);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TreatmentPrice>> getTreatmentPrice(Integer treatmentId) {
        List<TreatmentPriceDTO> resultFromRepository = treatmentRepository.getTreatmentPrice(treatmentId);
        Type listType = new TypeToken<List<TreatmentPrice>>() {
        }.getType();
        List<TreatmentPrice> result = modelMapper.map(resultFromRepository, listType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteMedicalProcedureFromTreatment(Integer treatmentId, Integer procedureId) {
        treatmentRepository.deleteMedicalProcedureFromTreatment(treatmentId, procedureId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteMedicationFromTreatment(Integer treatmentId, Integer medicationId) {
        treatmentRepository.deleteMedicationFromTreatment(treatmentId, medicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateTreatmentMedicalProcedure(
        Integer treatmentId,
        Integer medicalProcedureId,
        Integer amount,
        String doctorInstructions
    ) {
        treatmentRepository.updateTreatmentMedicalProcedure(treatmentId, medicalProcedureId, amount, doctorInstructions);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateTreatmentMedication(
        Integer treatmentId,
        Integer medicationId,
        Integer amount,
        String doctorInstructions
    ) {
        treatmentRepository.updateTreatmentMedication(treatmentId, medicationId, amount, doctorInstructions);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TreatmentFullInfo>> getAllTreatments(Integer limit, Integer offset) {
        List<TreatmentFullInfoDTO> resultFromRepository = treatmentRepository.getAllTreatments(limit, offset);
        Type listType = new TypeToken<List<TreatmentFullInfo>>() {
        }.getType();
        List<TreatmentFullInfo> result = modelMapper.map(resultFromRepository, listType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TreatmentFullInfo>> getDoctorTreatments(
        Integer doctorId,
        Integer limit,
        Integer offset
    ) {
        List<TreatmentFullInfoDTO> resultFromRepository = treatmentRepository.getDoctorTreatments(doctorId, limit, offset);
        Type listType = new TypeToken<List<TreatmentFullInfo>>() {
        }.getType();
        List<TreatmentFullInfo> result = modelMapper.map(resultFromRepository, listType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TreatmentFullInfo> getTreatmentById(Integer treatmentId) {
        TreatmentFullInfoDTO result = treatmentRepository.getTreatmentById(treatmentId);
        TreatmentFullInfo response = modelMapper.map(result, TreatmentFullInfo.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
