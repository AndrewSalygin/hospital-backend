package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureFullInfoDTO;
import com.andrewsalygin.hospital.model.DoctorShortInfo;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfo;
import com.andrewsalygin.repository.DoctorsMedicalProceduresRepository;
import com.andrewsalygin.service.DoctorsMedicalProceduresService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcDoctorsMedicalProceduresRepository implements DoctorsMedicalProceduresRepository {

    private final JdbcClient client;

    @Override
    public List<DoctorShortInfoDTO> getDoctorsForProcedure(Integer procedureId) {
        return List.of();
    }

    @Override
    public List<MedicalProcedureFullInfoDTO> getProceduresForDoctor(Integer doctorId) {
        return List.of();
    }

    @Override
    public void addDoctorProcedure(Integer doctorId, Integer procedureId) {

    }

    @Override
    public void deleteDoctorProcedure(Integer doctorId, Integer procedureId) {

    }
}
