package com.andrewsalygin.applicationservice.service.jdbc;

import com.andrewsalygin.applicationservice.dto.TotalCostDTO;
import com.andrewsalygin.applicationservice.dto.medication.MedicationWithAmountDTO;
import com.andrewsalygin.applicationservice.dto.surgeries.DoctorSurgeryCountDTO;
import com.andrewsalygin.applicationservice.dto.surgeries.DoctorWithWorkingHoursDTO;
import com.andrewsalygin.applicationservice.dto.surgeries.SurgeryFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.surgeries.SurgeryFullInfoWithoutIdDTO;
import com.andrewsalygin.applicationservice.model.DoctorSurgeryCount;
import com.andrewsalygin.applicationservice.model.DoctorWithWorkingHours;
import com.andrewsalygin.applicationservice.model.IdResponse;
import com.andrewsalygin.applicationservice.model.MedicationWithAmount;
import com.andrewsalygin.applicationservice.model.SurgeryFullInfo;
import com.andrewsalygin.applicationservice.model.SurgeryFullInfoWithoutId;
import com.andrewsalygin.applicationservice.model.TotalCost;
import com.andrewsalygin.applicationservice.repository.interfaces.SurgeriesRepository;
import com.andrewsalygin.applicationservice.service.interfaces.SurgeriesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcSurgeriesService implements SurgeriesService {

    private final SurgeriesRepository surgeriesRepository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<SurgeryFullInfo>> getAllSurgeries(Integer limit, Integer offset) {
        List<SurgeryFullInfoDTO> resultFromRepository = surgeriesRepository.getAllSurgeries(limit, offset);
        Type listType = new TypeToken<List<SurgeryFullInfo>>() {}.getType();
        List<SurgeryFullInfo> result = modelMapper.map(resultFromRepository, listType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<IdResponse> createSurgery(SurgeryFullInfoWithoutId surgeryFullInfoWithoutId) {
        SurgeryFullInfoWithoutIdDTO surgeryDTO = modelMapper.map(surgeryFullInfoWithoutId, SurgeryFullInfoWithoutIdDTO.class);
        Integer surgeryId = surgeriesRepository.createSurgery(surgeryDTO);
        IdResponse response = new IdResponse();
        response.setId(surgeryId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteSurgery(Integer surgeryId) {
        surgeriesRepository.deleteSurgery(surgeryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateSurgery(Integer surgeryId, SurgeryFullInfoWithoutId surgeryFullInfoWithoutId) {
        SurgeryFullInfoWithoutIdDTO surgeryDTO = modelMapper.map(surgeryFullInfoWithoutId, SurgeryFullInfoWithoutIdDTO.class);
        surgeriesRepository.updateSurgery(surgeryId, surgeryDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DoctorWithWorkingHours>> getDoctorsForSurgery(Integer surgeryId) {
        List<DoctorWithWorkingHoursDTO> resultFromRepository = surgeriesRepository.getDoctorsForSurgery(surgeryId);
        Type listType = new TypeToken<List<DoctorWithWorkingHours>>() {}.getType();
        List<DoctorWithWorkingHours> result = modelMapper.map(resultFromRepository, listType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addDoctorToSurgery(
        Integer surgeryId,
        Integer doctorId,
        Float workingHours,
        Float scheduledWorkingHours
    ) {
        surgeriesRepository.addDoctorToSurgery(surgeryId, doctorId, workingHours, scheduledWorkingHours);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateDoctorWorkingHours(
        Integer surgeryId,
        Integer doctorId,
        Float workingHours,
        Float scheduledWorkingHours
    ) {
        surgeriesRepository.updateDoctorWorkingHours(surgeryId, doctorId, workingHours, scheduledWorkingHours);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DoctorSurgeryCount>> countSurgeriesByDoctor(
        LocalDate startDate,
        LocalDate endDate
    ) {
        List<DoctorSurgeryCountDTO> resultFromRepository = surgeriesRepository.countSurgeriesByDoctor(startDate, endDate);
        Type listType = new TypeToken<List<DoctorSurgeryCount>>() {}.getType();
        List<DoctorSurgeryCount> result = modelMapper.map(resultFromRepository, listType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TotalCost> calculateTotalMedicationCost(Integer surgeryId) {
        TotalCostDTO totalCostDTO = surgeriesRepository.calculateTotalMedicationCost(surgeryId);
        TotalCost totalCost = modelMapper.map(totalCostDTO, TotalCost.class);
        return new ResponseEntity<>(totalCost, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> removeMedicationFromSurgery(Integer surgeryId, Integer medicationId) {
        surgeriesRepository.removeMedicationFromSurgery(surgeryId, medicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> removeDoctorFromSurgery(Integer surgeryId, Integer doctorId) {
        surgeriesRepository.removeDoctorFromSurgery(surgeryId, doctorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MedicationWithAmount>> getMedicationsForSurgery(Integer surgeryId) {
        List<MedicationWithAmountDTO> resultFromRepository = surgeriesRepository.getMedicationsForSurgery(surgeryId);
        Type listType = new TypeToken<List<MedicationWithAmount>>() {}.getType();
        List<MedicationWithAmount> result = modelMapper.map(resultFromRepository, listType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addMedicationToSurgery(Integer surgeryId, Integer medicationId, Integer amount) {
        surgeriesRepository.addMedicationToSurgery(surgeryId, medicationId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateSurgeryDescription(Integer surgeryId, String surgicalProcedureDescription) {
        surgeriesRepository.updateSurgeryDescription(surgeryId, surgicalProcedureDescription);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SurgeryFullInfo>> getSurgeriesByPatient(Integer patientId) {
        List<SurgeryFullInfoDTO> resultFromRepository = surgeriesRepository.getSurgeriesByPatient(patientId);
        Type listType = new TypeToken<List<SurgeryFullInfo>>() {}.getType();
        List<SurgeryFullInfo> result = modelMapper.map(resultFromRepository, listType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SurgeryFullInfo> getSurgeryById(Integer surgeryId) {
        SurgeryFullInfoDTO surgeryDTO = surgeriesRepository.getSurgeryById(surgeryId);
        SurgeryFullInfo surgery = modelMapper.map(surgeryDTO, SurgeryFullInfo.class);
        return new ResponseEntity<>(surgery, HttpStatus.OK);
    }
}
