package com.andrewsalygin.dto.authorization;

import jakarta.validation.constraints.Email;

public record AuthUserInfo(@Email String email, String password) {
}
