package com.andrewsalygin.specializationservice.repository;

import com.andrewsalygin.specializationservice.dto.DoctorSpecializationDTO;
import com.andrewsalygin.specializationservice.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.specializationservice.model.Specialization;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcSpecializationsRepository {

    private final JdbcClient client;

    public List<Specialization> getSpecializations() {
        return client.sql("SELECT specializationId, specializationName FROM specialization")
            .query(Specialization.class)
            .list();
    }

    public Integer addSpecialization(String specializationName) {
        return client.sql("INSERT INTO specialization (specializationName) OUTPUT INSERTED.specializationId VALUES (?)")
            .param(specializationName)
            .query(Integer.class)
            .single();
    }

    public void deleteSpecialization(Integer specializationId) {
        client.sql("DELETE FROM specialization WHERE specializationId = ?")
            .param(specializationId)
            .update();
    }

    public List<DoctorSpecializationDTO> getDoctorSpecializations(Integer doctorId) {
        return client.sql("SELECT s.specializationId, s.specializationName, ds.yearsOfExperience " +
                "FROM doctorSpecialization ds " +
                "INNER JOIN specialization s ON ds.specializationId = s.specializationId " +
                "WHERE ds.doctorId = ?")
            .param(doctorId)
            .query(DoctorSpecializationDTO.class)
            .list();
    }
}
