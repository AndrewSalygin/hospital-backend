package com.andrewsalygin.applicationservice.repository.interfaces;

import com.andrewsalygin.applicationservice.dto.doctor.DoctorAddRequestDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorInfoDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorSpecialInfoDTO;
import com.andrewsalygin.applicationservice.dto.surgeries.SurgeryShortInfoDTO;
import com.andrewsalygin.applicationservice.model.DoctorShortInfo;
import java.util.List;

public interface DoctorsRepository {

    List<SurgeryShortInfoDTO> getSurgeriesForDoctor(Integer doctorId, Integer limit, Integer offset);

    DoctorInfoDTO getFirstAvailableDoctorBySpecializationAndExperience(String specializationName);

    List<DoctorSpecialInfoDTO> getDoctors(Integer limit, Integer offset);

    DoctorFullInfoDTO getDoctor(Integer doctorId);

    void deleteDoctor(Integer doctorId);

    void addSpecializationToDoctor(Integer doctorId, Integer specializationId, Integer yearsOfExperience);

    void changeSpecializationExperienceDoctor(Integer doctorId, Integer specializationId, Integer yearsOfExperience);

    Integer addDoctor(DoctorAddRequestDTO doctorAddRequestDTO);

    void updateDoctor(Integer doctorId, DoctorAddRequestDTO doctorAddRequestDTO);

    void restoreDoctor(Integer doctorId);

    List<DoctorShortInfo> getDoctorsForSpecialization(Integer specializationId);
}
