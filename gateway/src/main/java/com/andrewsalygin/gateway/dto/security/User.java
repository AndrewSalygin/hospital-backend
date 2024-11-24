package com.andrewsalygin.gateway.dto.security;

public record User(
    Integer userId,
    String email,
    String role
) {
}
