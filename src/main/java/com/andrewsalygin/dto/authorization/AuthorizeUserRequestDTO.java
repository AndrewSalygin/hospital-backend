package com.andrewsalygin.dto.authorization;

import jakarta.validation.constraints.Email;

public record AuthorizeUserRequestDTO(@Email String email, String password) {
}
