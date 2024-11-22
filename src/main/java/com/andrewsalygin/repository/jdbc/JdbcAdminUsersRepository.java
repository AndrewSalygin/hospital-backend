package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.admin.UserInfo;
import com.andrewsalygin.repository.interfaces.AdminUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcAdminUsersRepository implements AdminUsersRepository {

    @Override
    public void changeUserRights(Integer id, String role) {

    }

    @Override
    public void deleteUser(Integer id) {

    }

    @Override
    public List<UserInfo> getUsers(Integer limit, Integer offset) {
        return List.of();
    }
}
