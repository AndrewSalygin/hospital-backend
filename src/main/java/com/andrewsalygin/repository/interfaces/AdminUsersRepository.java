package com.andrewsalygin.repository.interfaces;

import com.andrewsalygin.dto.admin.UserInfo;
import java.util.List;

public interface AdminUsersRepository {

    void changeUserRights(Integer id, String role);

    void deleteUser(Integer id);

    List<UserInfo> getUsers(Integer limit, Integer offset);
}
