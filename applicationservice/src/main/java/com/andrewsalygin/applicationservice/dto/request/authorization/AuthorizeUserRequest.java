package com.andrewsalygin.applicationservice.dto.request.authorization;

public record AuthorizeUserRequest(
    String email,
    String password
) {
}
