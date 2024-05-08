package com.andrewsalygin.repository;

import com.andrewsalygin.dto.security.UserCredentials;
import java.util.List;

public interface UsersRepository {

    UserCredentials findByEmail(String username);

}
