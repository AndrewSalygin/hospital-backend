package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.DoctorShortInfo;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfo;
import com.andrewsalygin.repository.interfaces.DoctorsMedicalProceduresRepository;
import com.andrewsalygin.service.interfaces.DoctorsMedicalProceduresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcDoctorsMedicalProceduresService implements DoctorsMedicalProceduresService {

    private final DoctorsMedicalProceduresRepository repository;

    @Override
    public ResponseEntity<List<DoctorShortInfo>> getDoctorsForProcedure(Integer procedureId) {
        return null;
    }

    @Override
    public ResponseEntity<List<MedicalProcedureFullInfo>> getProceduresForDoctor(Integer doctorId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addDoctorProcedure(Integer doctorId, Integer procedureId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteDoctorProcedure(Integer doctorId, Integer procedureId) {
        return null;
    }
}
