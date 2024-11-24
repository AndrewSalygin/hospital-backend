package com.andrewsalygin.gateway.dto.auth;

import jakarta.validation.constraints.Email;

public record AuthorizeUserRequestDTO(@Email String email, String password) {
}
