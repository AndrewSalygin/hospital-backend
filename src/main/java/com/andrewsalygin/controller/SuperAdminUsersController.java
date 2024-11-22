package com.andrewsalygin.controller;

import com.andrewsalygin.hospital.api.SuperAdminUsersApi;
import com.andrewsalygin.hospital.model.UserInfoForAdminPanel;
import com.andrewsalygin.service.interfaces.UsersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SuperAdminUsersController implements SuperAdminUsersApi {

    private final UsersService usersService;

    @Override
    public ResponseEntity<List<UserInfoForAdminPanel>> getUsers(Integer limit, Integer offset) {
        return usersService.getUsers(limit, offset);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer userId) {
        return usersService.deleteUser(userId);
    }

    @Override
    public ResponseEntity<Void> changeUserRights(Integer userId, String role) {
        return usersService.changeUserRights(userId, role);
    }
}
