package com.andrewsalygin.repository.interfaces;

import com.andrewsalygin.dto.admin.UserInfo;
import com.andrewsalygin.dto.security.UserCredentials;
import java.util.List;

public interface UsersRepository {

    UserCredentials findByEmail(String username);

    void changeUserRights(Integer userId, String role);

    void deleteUser(Integer userId);

    List<UserInfo> getUsers(Integer limit, Integer offset);
}
