package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.security.UserCredentials;
import com.andrewsalygin.exception.UserNotFoundException;
import com.andrewsalygin.repository.UsersRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcUsersRepository implements UsersRepository {

    private final JdbcClient client;

    private static final String EMAIL_FIELD = "email";

    @Override
    public UserCredentials findByEmail(String email) {
        return client.sql("SELECT email, password, role, is_banned FROM users WHERE email = :email")
            .param(EMAIL_FIELD, email)
            .query(UserCredentials.class)
            .optional().orElseThrow(UserNotFoundException::new);
    }
}
