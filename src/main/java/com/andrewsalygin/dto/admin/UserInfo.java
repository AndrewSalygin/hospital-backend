package com.andrewsalygin.dto.admin;

public record UserInfo(
    Long userId,
    String email,
    String role
) {
}
