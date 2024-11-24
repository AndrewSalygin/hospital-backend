package com.andrewsalygin.applicationservice.service.jdbc;

import com.andrewsalygin.applicationservice.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.applicationservice.dto.medicalProcedure.MedicalProcedureFullInfoDTO;
import com.andrewsalygin.applicationservice.model.DoctorShortInfo;
import com.andrewsalygin.applicationservice.model.MedicalProcedureFullInfo;
import com.andrewsalygin.applicationservice.repository.interfaces.DoctorsMedicalProceduresRepository;
import com.andrewsalygin.applicationservice.service.interfaces.DoctorsMedicalProceduresService;
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
public class JdbcDoctorsMedicalProceduresService implements DoctorsMedicalProceduresService {

    private final DoctorsMedicalProceduresRepository repository;

    private final ModelMapper modelMapper;


    @Override
    public ResponseEntity<List<DoctorShortInfo>> getDoctorsForProcedure(Integer procedureId) {
        List<DoctorShortInfoDTO> resultFromRepository = repository.getDoctorsForProcedure(procedureId);

        Type listType = new TypeToken<List<DoctorShortInfo>>() {}.getType();
        List<DoctorShortInfo> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MedicalProcedureFullInfo>> getProceduresForDoctor(Integer doctorId) {
        List<MedicalProcedureFullInfoDTO> resultFromRepository = repository.getProceduresForDoctor(doctorId);

        Type listType = new TypeToken<List<MedicalProcedureFullInfo>>() {}.getType();
        List<MedicalProcedureFullInfo> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addDoctorProcedure(Integer doctorId, Integer procedureId) {
        repository.addDoctorProcedure(doctorId, procedureId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteDoctorProcedure(Integer doctorId, Integer procedureId) {
        repository.deleteDoctorProcedure(doctorId, procedureId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
