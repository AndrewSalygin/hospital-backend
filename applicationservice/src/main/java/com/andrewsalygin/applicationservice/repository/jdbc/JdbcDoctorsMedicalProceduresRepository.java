package com.andrewsalygin.applicationservice.repository.jdbc;

import com.andrewsalygin.applicationservice.repository.interfaces.DoctorsMedicalProceduresRepository;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.applicationservice.dto.medicalProcedure.MedicalProcedureFullInfoDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcDoctorsMedicalProceduresRepository implements DoctorsMedicalProceduresRepository {

    private final JdbcClient client;

    @Override
    public List<DoctorShortInfoDTO> getDoctorsForProcedure(Integer procedureId) {
        String query = "SELECT d.doctorId, d.lastName, d.firstName, d.middleName, d.dateOfBirth, d.gender, " +
            "s.specializationName, ds.yearsOfExperience, d.isDeleted " +
            "FROM doctor d " +
            "INNER JOIN doctorSpecialization ds ON d.doctorId = ds.doctorId " +
            "INNER JOIN specialization s ON ds.specializationId = s.specializationId " +
            "INNER JOIN doctorMedicalProcedure dmp ON d.doctorId = dmp.doctorId " +
            "WHERE dmp.medicalProcedureId = ?";

        return client.sql(query)
            .param(procedureId)
            .query(DoctorShortInfoDTO.class)
            .list();
    }

    @Override
    public List<MedicalProcedureFullInfoDTO> getProceduresForDoctor(Integer doctorId) {
        String query = "SELECT mp.medicalProcedureId, mp.medicalProcedureName, mp.price " +
            "FROM medicalProcedure mp " +
            "INNER JOIN doctorMedicalProcedure dmp ON mp.medicalProcedureId = dmp.medicalProcedureId " +
            "WHERE dmp.doctorId = ?";

        return client.sql(query)
            .param(doctorId)
            .query(MedicalProcedureFullInfoDTO.class)
            .list();
    }

    @Override
    public void addDoctorProcedure(Integer doctorId, Integer procedureId) {
        String query = "INSERT INTO doctorMedicalProcedure (doctorId, medicalProcedureId) VALUES (?, ?)";

        client.sql(query)
            .params(doctorId, procedureId)
            .update();
    }

    @Override
    public void deleteDoctorProcedure(Integer doctorId, Integer procedureId) {
        String query = "DELETE FROM doctorMedicalProcedure WHERE doctorId = ? AND medicalProcedureId = ?";

        client.sql(query)
            .params(doctorId, procedureId)
            .update();
    }
}
