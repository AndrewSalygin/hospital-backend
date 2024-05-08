package com.andrewsalygin.service;

import com.andrewsalygin.hospital.model.JWTToken;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<JWTToken> performLogin(String email, String password);

    ResponseEntity<JWTToken> performRegistration(
        String firstName,
        String lastName,
        String email,
        String password,
        String role,
        String organization,
        String cityName,
        String districtName
    );
}
