package com.andrewsalygin.service;

import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.Specialization;
import com.andrewsalygin.hospital.model.UserInfoForAdminPanel;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface AdminUsersService {

    ResponseEntity<List<IdResponse>> addSpecialization(String specializationName);

    ResponseEntity<Void> changeUserRights(Integer id, String role);

    ResponseEntity<Void> deleteDoctor(Integer id);

    ResponseEntity<Void> deleteSpecialization(Integer id);

    ResponseEntity<Void> deleteUser(Integer id);

    ResponseEntity<List<Specialization>> getSpecializations();

    ResponseEntity<List<UserInfoForAdminPanel>> getUsers(Integer limit, Integer offset);
}
