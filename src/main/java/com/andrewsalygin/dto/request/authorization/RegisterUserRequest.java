package com.andrewsalygin.dto.request.authorization;

public record RegisterUserRequest(
    String email,
    String password
) {
}
