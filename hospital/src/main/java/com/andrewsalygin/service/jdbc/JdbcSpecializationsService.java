package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.DoctorShortInfo;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.Specialization;
import com.andrewsalygin.repository.SpecializationsRepository;
import com.andrewsalygin.service.SpecializationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcSpecializationsService implements SpecializationsService {

    private final SpecializationsRepository specializationsRepository;

    @Override
    public ResponseEntity<List<Specialization>> getSpecializations() {
        return null;
    }

    @Override
    public ResponseEntity<List<DoctorShortInfo>> getSpecializationsDoctors(Integer specializationId) {
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
