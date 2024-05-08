package com.andrewsalygin.dto.user;

public record UserCreateInfo(
    String email,
    String password
) {
}
