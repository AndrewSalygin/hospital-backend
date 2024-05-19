package com.andrewsalygin.service;

import com.andrewsalygin.hospital.model.DoctorFullInfo;
import com.andrewsalygin.hospital.model.DoctorInfo;
import com.andrewsalygin.hospital.model.DoctorShortInfo;
import com.andrewsalygin.hospital.model.DoctorSpecialization;
import com.andrewsalygin.hospital.model.SurgeryShortInfo;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface DoctorsService {

    ResponseEntity<List<SurgeryShortInfo>> getSurgeriesForDoctor(Integer doctorId);

    ResponseEntity<DoctorInfo> getFirstAvailableDoctorBySpecializationAndExperience(String specializationName);

    ResponseEntity<List<DoctorShortInfo>> getDoctors(Integer limit, Integer offset);

    ResponseEntity<DoctorFullInfo> getDoctor(Integer doctorId);

    ResponseEntity<List<DoctorSpecialization>> getDoctorSpecializations(Integer doctorId);

    ResponseEntity<Void> deleteDoctor(Integer doctorId);

    ResponseEntity<Void> addSpecializationToDoctor(Integer doctorId, Integer specializationId, Integer yearsOfExperience);

    ResponseEntity<Void> changeSpecializationExperienceDoctor(Integer doctorId, Integer specializationId, Integer yearsOfExperience);
}
