package com.andrewsalygin.repository.interfaces;

import com.andrewsalygin.dto.security.UserCredentials;

public interface UsersRepository {

    UserCredentials findByEmail(String username);

}
