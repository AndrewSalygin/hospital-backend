package com.andrewsalygin.service.interfaces;

import com.andrewsalygin.hospital.model.UserInfoForAdminPanel;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface UsersService {

    ResponseEntity<Void> changeUserRights(Integer id, String role);

    ResponseEntity<Void> deleteUser(Integer id);

    ResponseEntity<List<UserInfoForAdminPanel>> getUsers(Integer limit, Integer offset);
}
