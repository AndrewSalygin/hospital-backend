package com.andrewsalygin.repository;

import com.andrewsalygin.dto.authorization.AuthUserInfo;
import com.andrewsalygin.dto.security.User;

public interface AuthRepository {

    Integer performLogin(AuthUserInfo authUserInfo);

    Integer performRegistration(AuthUserInfo authUserInfo);

    String getEncodedPasswordByEmail(String email);

    Integer getUserIdByEmail(String email);

    User getUserById(Integer userId);
}
