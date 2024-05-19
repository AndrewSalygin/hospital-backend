package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.UserInfoForAdminPanel;
import com.andrewsalygin.service.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcUsersService implements UsersService {

    @Override
    @Transactional
    public ResponseEntity<Void> changeUserRights(Integer id, String role) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteUser(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<List<UserInfoForAdminPanel>> getUsers(Integer limit, Integer offset) {
        return null;
    }
}
