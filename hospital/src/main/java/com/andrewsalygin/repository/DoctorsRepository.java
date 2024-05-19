package com.andrewsalygin.repository;

import com.andrewsalygin.dto.doctor.DoctorFullInfoDTO;
import com.andrewsalygin.dto.doctor.DoctorInfoDTO;
import com.andrewsalygin.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.dto.doctor.DoctorSpecializationDTO;
import com.andrewsalygin.dto.surgeries.SurgeryShortInfoDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface DoctorsRepository {

    List<SurgeryShortInfoDTO> getSurgeriesForDoctor(Integer doctorId);

    DoctorInfoDTO getFirstAvailableDoctorBySpecializationAndExperience(String specializationName);

    List<DoctorShortInfoDTO> getDoctors(Integer limit, Integer offset);

    DoctorFullInfoDTO getDoctor(Integer doctorId);

    List<DoctorSpecializationDTO> getDoctorSpecializations(Integer doctorId);

    void deleteDoctor(Integer doctorId);

    void addSpecializationToDoctor(Integer doctorId, Integer specializationId, Integer yearsOfExperience);

    void changeSpecializationExperienceDoctor(Integer doctorId, Integer specializationId, Integer yearsOfExperience);
}
