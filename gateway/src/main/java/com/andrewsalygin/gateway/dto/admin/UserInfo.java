package com.andrewsalygin.gateway.dto.admin;

public record UserInfo(
    Long userId,
    String email,
    String role
) {
}
