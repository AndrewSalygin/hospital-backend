package com.andrewsalygin.gateway.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizeUserRequest {

    private String email;

    private String password;
}
