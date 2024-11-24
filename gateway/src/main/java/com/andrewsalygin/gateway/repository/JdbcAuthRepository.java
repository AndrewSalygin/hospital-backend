package com.andrewsalygin.gateway.repository;

import com.andrewsalygin.gateway.dto.auth.AuthorizeUserRequestDTO;
import com.andrewsalygin.gateway.dto.security.User;
import com.andrewsalygin.gateway.exception.UserAlreadyExistException;
import com.andrewsalygin.gateway.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcAuthRepository {

    private final JdbcClient client;

    private static final String EMAIL_FIELD = "email";

    private static final String PASSWORD_FIELD = "password";

    public Integer performLogin(AuthorizeUserRequestDTO authorizeUserRequestDTO) {
        return client.sql("SELECT userId FROM users WHERE email = :email AND password = :password")
            .param(EMAIL_FIELD, authorizeUserRequestDTO.email())
            .param(PASSWORD_FIELD, authorizeUserRequestDTO.password())
            .query(Integer.class)
            .optional().orElseThrow(UserNotFoundException::new);
    }

    public Integer performRegistration(AuthorizeUserRequestDTO authorizeUserRequestDTO) throws UserAlreadyExistException {
        boolean userExists = client.sql("SELECT COUNT(*) FROM users WHERE email = :email")
            .param(EMAIL_FIELD, authorizeUserRequestDTO.email())
            .query(Boolean.class)
            .optional().orElse(false);

        if (userExists) {
            throw new UserAlreadyExistException();
        }

        return client.sql("INSERT INTO users (email, password, role) OUTPUT INSERTED.userId VALUES (:email, :password, :role)")
            .param(EMAIL_FIELD, authorizeUserRequestDTO.email())
            .param(PASSWORD_FIELD, authorizeUserRequestDTO.password())
            .param("role","PATIENT")
            .query(Integer.class)
            .optional().orElseThrow(() -> new RuntimeException("Failed to create user for unknown reasons"));
    }

    public String getEncodedPasswordByEmail(String email) {
        return client.sql("SELECT password FROM users WHERE email = :email")
            .param(EMAIL_FIELD, email)
            .query(String.class)
            .optional()
            .orElseThrow(UserNotFoundException::new);
    }

    public Integer getUserIdByEmail(String email) {
        return client.sql("SELECT userId FROM users WHERE email = :email")
            .param(EMAIL_FIELD, email)
            .query(Integer.class)
            .optional()
            .orElseThrow(UserNotFoundException::new);
    }

    public User getUserById(Integer userId) {
        return client.sql("SELECT userId, email, role FROM users WHERE userId = :userId")
            .param("userId", userId)
            .query(User.class).optional().orElseThrow(UserNotFoundException::new);
    }
}
