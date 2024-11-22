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
    public List<SpecializationDTO> getSpecializations() {
        return client.sql("SELECT specializationId, specializationName FROM specialization")
            .query(SpecializationDTO.class)
            .list();
    }

    @Override
    public List<DoctorShortInfoDTO> getSpecializationsDoctors(Integer specializationId) {
        return client.sql("SELECT d.doctorId, lastName, firstName, middleName, dateOfBirth, gender, specializationName, yearsOfExperience, isDeleted " +
                "FROM doctor d " +
                "INNER JOIN doctorSpecialization ds ON d.doctorId = ds.doctorId " +
                "INNER JOIN specialization s ON ds.specializationId = s.specializationId " +
                "WHERE s.specializationId = ?")
            .param(specializationId)
            .query(DoctorShortInfoDTO.class)
            .list();
    }

    @Override
    public Integer addSpecialization(String specializationName) {
        return client.sql("INSERT INTO specialization (specializationName) OUTPUT INSERTED.specializationId VALUES (?)")
            .param(specializationName)
            .query(Integer.class)
            .single();
    }

    @Override
    public void deleteSpecialization(Integer specializationId) {
        client.sql("DELETE FROM specialization WHERE specializationId = ?")
            .param(specializationId)
            .update();
    }
}
