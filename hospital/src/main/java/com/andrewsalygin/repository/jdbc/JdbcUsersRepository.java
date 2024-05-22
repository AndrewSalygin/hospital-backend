package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.admin.UserInfo;
import com.andrewsalygin.dto.security.UserCredentials;
import com.andrewsalygin.exception.UserNotFoundException;
import com.andrewsalygin.repository.interfaces.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcUsersRepository implements UsersRepository {

    private final JdbcClient client;

    private static final String EMAIL_FIELD = "email";

    private static final String ROLE_FIELD = "role";

    private static final String ID_FIELD = "userId";

    @Override
    public UserCredentials findByEmail(String email) {
        return client.sql("SELECT email, password, role FROM users WHERE email = :email")
            .param(EMAIL_FIELD, email)
            .query(UserCredentials.class)
            .optional().orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void changeUserRights(Integer userId, String role) {
        client.sql("UPDATE users SET role = :role WHERE userId = :userId")
            .param(ROLE_FIELD, role)
            .param(ID_FIELD, userId)
            .update();
    }

    @Override
    public void deleteUser(Integer userId) {
        client.sql("DELETE FROM users WHERE userId = :userId")
            .param(ID_FIELD, userId)
            .update();
    }

    @Override
    public List<UserInfo> getUsers(Integer limit, Integer offset) {
        String query;
        if (limit == -1) {
            query = "SELECT userId, email, password, role FROM users " +
                "ORDER BY userId OFFSET :offset ROWS";
        } else {
            query = "SELECT userId, email, password, role FROM users " +
                "ORDER BY userId OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";
        }
        return client.sql(query)
            .param("offset", offset)
            .param("limit", limit)
            .query(UserInfo.class)
            .list();
    }
}
