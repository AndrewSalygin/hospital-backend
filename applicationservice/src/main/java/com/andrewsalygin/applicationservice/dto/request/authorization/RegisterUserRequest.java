package com.andrewsalygin.applicationservice.dto.request.authorization;

public record RegisterUserRequest(
    String email,
    String password
) {
}
