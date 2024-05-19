package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.doctor.DoctorFullInfoDTO;
import com.andrewsalygin.dto.doctor.DoctorInfoDTO;
import com.andrewsalygin.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.dto.doctor.DoctorSpecializationDTO;
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
    public List<SurgeryShortInfoDTO> getSurgeriesForDoctor(Integer doctorId) {
        return List.of();
    }

    @Override
    public DoctorInfoDTO getFirstAvailableDoctorBySpecializationAndExperience(String specializationName) {
        return null;
    }

    @Override
    public List<DoctorShortInfoDTO> getDoctors(Integer limit, Integer offset) {
        return List.of();
    }

    @Override
    public DoctorFullInfoDTO getDoctor(Integer doctorId) {
        return null;
    }

    @Override
    public List<DoctorSpecializationDTO> getDoctorSpecializations(Integer doctorId) {
        return List.of();
    }

    @Override
    public void deleteDoctor(Integer doctorId) {

    }

    @Override
    public void addSpecializationToDoctor(Integer doctorId, Integer specializationId, Integer yearsOfExperience) {

    }

    @Override
    public void changeSpecializationExperienceDoctor(
        Integer doctorId,
        Integer specializationId,
        Integer yearsOfExperience
    ) {

    }
}
