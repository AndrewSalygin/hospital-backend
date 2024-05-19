package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.Specialization;
import com.andrewsalygin.hospital.model.UserInfoForAdminPanel;
import com.andrewsalygin.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcUsersService implements UsersService {

    @Override
    public ResponseEntity<Void> changeUserRights(Integer id, String role) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserInfoForAdminPanel>> getUsers(Integer limit, Integer offset) {
        return null;
    }
}
