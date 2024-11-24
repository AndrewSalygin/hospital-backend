package com.andrewsalygin.gateway.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest {

    private String email;

    private String password;
}