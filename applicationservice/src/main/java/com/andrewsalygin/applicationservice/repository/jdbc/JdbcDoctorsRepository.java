package com.andrewsalygin.applicationservice.repository.jdbc;

import com.andrewsalygin.applicationservice.dto.doctor.DoctorSpecialInfoDTO;
import com.andrewsalygin.applicationservice.model.DoctorShortInfo;
import com.andrewsalygin.applicationservice.repository.interfaces.DoctorsRepository;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorAddRequestDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorInfoDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorSpecializationDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientShortInfoDTO;
import com.andrewsalygin.applicationservice.dto.surgeries.SurgeryShortInfoDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import static org.springframework.web.servlet.function.RequestPredicates.param;

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
    public List<DoctorSpecialInfoDTO> getDoctors(Integer limit, Integer offset) {
        String query = "";
        if (limit == -1) {
            query = "SELECT d.doctorId, lastName, firstName, middleName, dateOfBirth, gender, " +
                "specializationId, yearsOfExperience, isDeleted " +
                "FROM doctor d " +
                "JOIN doctorSpecialization ds ON d.doctorId = ds.doctorId " +
                "ORDER BY d.doctorId " +
                "OFFSET :offset ROWS";
        } else if (limit > 0) {
            query = "SELECT d.doctorId, lastName, firstName, middleName, dateOfBirth, gender, " +
                "specializationName, yearsOfExperience, isDeleted " +
                "FROM doctor d " +
                "JOIN doctorSpecialization ds ON d.doctorId = ds.doctorId " +
                "OFFSET :offset ROWS " +
                "FETCH NEXT :limit ROWS ONLY";
        }
        return client.sql(query)
            .param("limit", limit)
            .param("offset", offset)
            .query(DoctorSpecialInfoDTO.class)
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
    public void deleteDoctor(Integer doctorId) {
        client.sql("UPDATE doctor SET isDeleted = 1 WHERE doctorId = ?")
            .param(doctorId)
            .update();
    }

    @Override
    public void addSpecializationToDoctor(Integer doctorId, Integer specializationId, Integer yearsOfExperience) {
        client.sql("INSERT INTO doctorSpecializationInsertV (doctorId, specializationId, yearsOfExperience) VALUES (?, ?, ?)")
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

    @Override
    public Integer addDoctor(DoctorAddRequestDTO doctorAddRequestDTO) {
        return client.sql("INSERT INTO doctor (lastName, firstName, middleName, dateOfBirth, gender, education, " +
            "phoneNumber, emailAddress) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")
            .param(doctorAddRequestDTO.getLastName())
            .param(doctorAddRequestDTO.getFirstName())
            .param(doctorAddRequestDTO.getMiddleName())
            .param(doctorAddRequestDTO.getDateOfBirth())
            .param(doctorAddRequestDTO.getGender())
            .param(doctorAddRequestDTO.getEducation())
            .param(doctorAddRequestDTO.getPhoneNumber())
            .param(doctorAddRequestDTO.getEmailAddress())
            .query(Integer.class)
            .single();
    }

    @Override
    public void updateDoctor(Integer doctorId, DoctorAddRequestDTO doctorAddRequestDTO) {
        String sql = "UPDATE doctor SET lastName = ?, firstName = ?, middleName = ?, dateOfBirth = ?, gender = ?, " +
            "education = ?, phoneNumber = ?, emailAddress = ? WHERE doctorId = ?";

        client.sql(sql)
            .param(doctorAddRequestDTO.getLastName())
            .param(doctorAddRequestDTO.getFirstName())
            .param(doctorAddRequestDTO.getMiddleName())
            .param(doctorAddRequestDTO.getDateOfBirth())
            .param(doctorAddRequestDTO.getGender())
            .param(doctorAddRequestDTO.getEducation())
            .param(doctorAddRequestDTO.getPhoneNumber())
            .param(doctorAddRequestDTO.getEmailAddress())
            .param(doctorId)
            .update();
    }

    @Override
    public void restoreDoctor(Integer doctorId) {
        client.sql("UPDATE doctor SET isDeleted = 0 WHERE doctorId = ?")
            .param(doctorId)
            .update();
    }

    @Override
    public List<DoctorShortInfo> getDoctorsForSpecialization(Integer specializationId) {
        return client.sql(
            "SELECT d.doctorId, lastName, firstName, middleName, dateOfBirth, gender, " +
            "yearsOfExperience " +
            "FROM doctor d " +
            "JOIN doctorSpecialization ds ON d.doctorId = ds.doctorId " +
            "WHERE specializationId = :specializationId")
            .param("specializationId", specializationId)
            .query(DoctorShortInfo.class)
            .list();
    }
}
