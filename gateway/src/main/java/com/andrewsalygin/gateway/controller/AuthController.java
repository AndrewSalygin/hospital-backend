package com.andrewsalygin.gateway.controller;

import com.andrewsalygin.gateway.dto.auth.AuthorizeUserRequest;
import com.andrewsalygin.gateway.dto.auth.JWTToken;
import com.andrewsalygin.gateway.dto.auth.RegisterUserRequest;
import com.andrewsalygin.gateway.service.JdbcAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AuthController {

    private final JdbcAuthService authService;

    @RequestMapping(
        method = RequestMethod.POST,
        value = "/auth/login",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    public Mono<ResponseEntity<JWTToken>> performLogin(@RequestBody AuthorizeUserRequest authorizeUserRequest) {
        return authService.performLogin(authorizeUserRequest.getEmail(), authorizeUserRequest.getPassword())
            .map(jwtToken -> ResponseEntity.ok(jwtToken))
            .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @RequestMapping(
        method = RequestMethod.POST,
        value = "/auth/registration",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    public Mono<ResponseEntity<JWTToken>> performRegistration(@RequestBody RegisterUserRequest registerUserRequest) {
        return authService.performRegistration(registerUserRequest.getEmail(), registerUserRequest.getPassword())
            .map(jwtToken -> ResponseEntity.ok(jwtToken))
            .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
