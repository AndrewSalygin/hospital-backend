package com.andrewsalygin.controller;

import com.andrewsalygin.hospital.api.AdminApi;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.Specialization;
import com.andrewsalygin.hospital.model.UserInfoForAdminPanel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AdminUsersController implements AdminApi {

    @Override
    public ResponseEntity<List<IdResponse>> addSpecialization(String specializationName) {
        return AdminApi.super.addSpecialization(specializationName);
    }

    @Override
    public ResponseEntity<Void> changeUserRights(Integer id, String role) {
        return AdminApi.super.changeUserRights(id, role);
    }

    @Override
    public ResponseEntity<Void> deleteDoctor(Integer id) {
        return AdminApi.super.deleteDoctor(id);
    }

    @Override
    public ResponseEntity<Void> deleteSpecialization(Integer id) {
        return AdminApi.super.deleteSpecialization(id);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {
        return AdminApi.super.deleteUser(id);
    }

    @Override
    public ResponseEntity<List<Specialization>> getSpecializations() {
        return AdminApi.super.getSpecializations();
    }

    @Override
    public ResponseEntity<List<UserInfoForAdminPanel>> getUsers(Integer limit, Integer offset) {
        return AdminApi.super.getUsers(limit, offset);
    }
}
