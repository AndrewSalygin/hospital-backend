package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.dto.specialization.SpecializationDTO;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.repository.interfaces.SpecializationsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcSpecializationsRepository implements SpecializationsRepository {

    private final JdbcClient client;

    @Override
    public ResponseEntity<List<SpecializationDTO>> getSpecializations() {
        return null;
    }

    @Override
    public ResponseEntity<List<DoctorShortInfoDTO>> getSpecializationsDoctors(Integer specializationId) {
        return null;
    }

    @Override
    public ResponseEntity<List<IdResponse>> addSpecialization(String specializationName) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteSpecialization(Integer specializationId) {
        return null;
    }
}
