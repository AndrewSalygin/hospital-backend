package com.andrewsalygin.repository;

import com.andrewsalygin.dto.authorization.AuthorizeUserRequestDTO;
import com.andrewsalygin.dto.security.User;

public interface AuthRepository {

    Integer performLogin(AuthorizeUserRequestDTO authorizeUserRequestDTO);

    Integer performRegistration(AuthorizeUserRequestDTO authorizeUserRequestDTO);

    String getEncodedPasswordByEmail(String email);

    Integer getUserIdByEmail(String email);

    User getUserById(Integer userId);
}
