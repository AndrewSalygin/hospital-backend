package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.Specialization;
import com.andrewsalygin.hospital.model.UserInfoForAdminPanel;
import com.andrewsalygin.service.AdminUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcAdminUsersService implements AdminUsersService {

    @Override
    @Transactional
    public ResponseEntity<List<IdResponse>> addSpecialization(String specializationName) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<Void> changeUserRights(Integer id, String role) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteDoctor(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteSpecialization(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteUser(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<List<Specialization>> getSpecializations() {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<List<UserInfoForAdminPanel>> getUsers(Integer limit, Integer offset) {
        return null;
    }
}
