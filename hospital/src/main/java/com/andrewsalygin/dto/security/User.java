package com.andrewsalygin.dto.security;

public record User(
    Integer userId,
    String email,
    String role
) {
}
