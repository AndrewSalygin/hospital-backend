package com.andrewsalygin.dto.request.authorization;

public record AuthorizeUserRequest(
    String email,
    String password
) {
}
