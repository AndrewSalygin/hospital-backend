package com.andrewsalygin.repository;

import com.andrewsalygin.dto.authorization.AuthUserInfo;

public interface AuthRepository {

    Long performLogin(AuthUserInfo authUserInfo);

    Long performRegistration(AuthUserInfo authUserInfo);

    String getEncodedPasswordByEmail(String email);

    Long getUserIdByEmail(String email);
}
