package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.doctor.DoctorFullInfoDTO;
import com.andrewsalygin.dto.doctor.DoctorInfoDTO;
import com.andrewsalygin.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.dto.doctor.DoctorSpecializationDTO;
import com.andrewsalygin.dto.patient.PatientShortInfoDTO;
import com.andrewsalygin.dto.surgeries.SurgeryShortInfoDTO;
import com.andrewsalygin.repository.interfaces.DoctorsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcDoctorsRepository implements DoctorsRepository {

    private final JdbcClient client;

    @Override
    public List<SurgeryShortInfoDTO> getSurgeriesForDoctor(Integer doctorId, Integer limit, Integer offset) {
        String query = "";
        if (limit == -1) {
            query = "SELECT s.surgeryId, patientId, scheduledDateTime, startTime, endTime, surgeryType FROM doctor d " +
                "INNER JOIN doctorSurgeryJournal dsj ON d.doctorId = dsj.doctorId " +
                "INNER JOIN surgery s ON dsj.surgeryId = s.surgeryId " +
                "WHERE d.doctorId = :doctorId " +
                "ORDER BY scheduledDateTime DESC " +
                "OFFSET :offset ROWS";
        } else if (limit > 0) {
            query = "SELECT s.surgeryId, patientId, scheduledDateTime, startTime, endTime, surgeryType FROM doctor d " +
                "INNER JOIN doctorSurgeryJournal dsj ON d.doctorId = dsj.doctorId " +
                "INNER JOIN surgery s ON dsj.surgeryId = s.surgeryId " +
                "WHERE d.doctorId = :doctorId " +
                "ORDER BY scheduledDateTime DESC " +
                "OFFSET :offset ROWS " +
                "FETCH NEXT :limit ROWS ONLY";
        }

        return client.sql(query)
            .param("doctorId", doctorId)
            .param("offset", offset)
            .param("limit", limit)
            .query(SurgeryShortInfoDTO.class)
            .list();
    }

    @Override
    public DoctorInfoDTO getFirstAvailableDoctorBySpecializationAndExperience(String specializationName) {
        return client.sql("EXEC GetFirstAvailableDoctorBySpecializationAndExperience @SpecializationName = ?")
            .param(specializationName)
            .query(DoctorInfoDTO.class)
            .single();
    }

    @Override
    public List<DoctorShortInfoDTO> getDoctors(Integer limit, Integer offset) {
        String query = "";
        if (limit == -1) {
            query = "SELECT d.doctorId, lastName, firstName, middleName, dateOfBirth, gender, " +
                "specializationName, yearsOfExperience, isDeleted " +
                "FROM doctor d " +
                "INNER JOIN doctorSpecialization ds ON d.doctorId = ds.doctorId " +
                "INNER JOIN specialization s ON ds.specializationId = s.specializationId " +
                "ORDER BY d.doctorId " +
                "OFFSET :offset ROWS";
        } else if (limit > 0) {
            query = "SELECT d.doctorId, lastName, firstName, middleName, dateOfBirth, gender, " +
                "specializationName, yearsOfExperience, isDeleted " +
                "FROM doctor d " +
                "INNER JOIN doctorSpecialization ds ON d.doctorId = ds.doctorId " +
                "INNER JOIN specialization s ON ds.specializationId = s.specializationId " +
                "ORDER BY d.doctorId " +
                "OFFSET :offset ROWS " +
                "FETCH NEXT :limit ROWS ONLY";
        }
        return client.sql(query)
            .param("limit", limit)
            .param("offset", offset)
            .query(DoctorShortInfoDTO.class)
            .list();
    }

    @Override
    public DoctorFullInfoDTO getDoctor(Integer doctorId) {
        return client.sql("SELECT doctorId, lastName, firstName, middleName, dateOfBirth, gender, " +
                "education, phoneNumber, emailAddress, isDeleted " +
                "FROM doctor " +
                "WHERE doctorId = ?")
            .param(doctorId)
            .query(DoctorFullInfoDTO.class)
            .single();
    }

    @Override
    public List<DoctorSpecializationDTO> getDoctorSpecializations(Integer doctorId) {
        return client.sql("SELECT s.specializationId, s.specializationName, ds.yearsOfExperience " +
                "FROM doctorSpecialization ds " +
                "INNER JOIN specialization s ON ds.specializationId = s.specializationId " +
                "WHERE ds.doctorId = ?")
            .param(doctorId)
            .query(DoctorSpecializationDTO.class)
            .list();
    }

    @Override
    public void deleteDoctor(Integer doctorId) {
        client.sql("UPDATE doctor SET isDeleted = 1 WHERE doctorId = ?")
            .param(doctorId)
            .update();
    }

    @Override
    public void addSpecializationToDoctor(Integer doctorId, Integer specializationId, Integer yearsOfExperience) {
        client.sql("INSERT INTO doctorSpecialization (doctorId, specializationId, yearsOfExperience) VALUES (?, ?, ?)")
            .params(doctorId, specializationId, yearsOfExperience)
            .update();
    }

    @Override
    public void changeSpecializationExperienceDoctor(
        Integer doctorId,
        Integer specializationId,
        Integer yearsOfExperience
    ) {
        client.sql("UPDATE doctorSpecialization SET yearsOfExperience = ? WHERE doctorId = ? AND specializationId = ?")
            .params(yearsOfExperience, doctorId, specializationId)
            .update();
    }
}
