package com.andrewsalygin.controller;

import com.andrewsalygin.exception.UserBadCredentialException;
import com.andrewsalygin.hospital.api.AuthApi;
import com.andrewsalygin.hospital.model.AuthorizeUserRequest;
import com.andrewsalygin.hospital.model.JWTToken;
import com.andrewsalygin.hospital.model.RegisterUserRequest;
import com.greencats.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<JWTToken> performLogin(AuthorizeUserRequest authorizeUserRequest) {
        return authService.performLogin(authorizeUserRequest.getEmail(), authorizeUserRequest.getPassword());
    }

    @Override
    public ResponseEntity<JWTToken> performRegistration(RegisterUserRequest registerUserRequest) {
        if (registerUserRequest.getRole().equals("ADMIN")) {
            throw new UserBadCredentialException();
        }
        return authService.performRegistration(
            registerUserRequest.getFirstName(),
            registerUserRequest.getLastName(),
            registerUserRequest.getEmail(),
            registerUserRequest.getPassword(),
            registerUserRequest.getRole(),
            registerUserRequest.getOrganization(),
            registerUserRequest.getCityName(),
            registerUserRequest.getDistrictName()
        );
    }
}
