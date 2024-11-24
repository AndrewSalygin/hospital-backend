package com.andrewsalygin.applicationservice.service.interfaces;

import com.andrewsalygin.applicationservice.model.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface DoctorsService {

    ResponseEntity<List<SurgeryShortInfo>> getSurgeriesForDoctor(Integer doctorId, Integer limit, Integer offset);

    ResponseEntity<DoctorInfo> getFirstAvailableDoctorBySpecializationAndExperience(String specializationName);

    ResponseEntity<List<DoctorShortInfo>> getDoctors(Integer limit, Integer offset);

    ResponseEntity<DoctorFullInfo> getDoctor(Integer doctorId);

    ResponseEntity<List<DoctorSpecialization>> getDoctorSpecializations(Integer doctorId);

    ResponseEntity<Void> deleteDoctor(Integer doctorId);

    ResponseEntity<Void> addSpecializationToDoctor(Integer doctorId, Integer specializationId, Integer yearsOfExperience);

    ResponseEntity<Void> changeSpecializationExperienceDoctor(Integer doctorId, Integer specializationId, Integer yearsOfExperience);

    ResponseEntity<IdResponse> addDoctor(DoctorAddRequest doctorAddRequest);

    ResponseEntity<Void> updateDoctor(Integer doctorId, DoctorAddRequest doctorAddRequest);

    ResponseEntity<Void> restoreDoctor(Integer doctorId);

    ResponseEntity<List<DoctorShortInfo>> getDoctorsForSpecialization(Integer specializationId);
}
