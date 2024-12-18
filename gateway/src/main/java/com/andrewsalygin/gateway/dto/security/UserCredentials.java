package com.andrewsalygin.gateway.dto.security;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserCredentials(@Valid @Email String email, @Size(min = 8, max = 100) String password, String role) {
    public boolean isAdmin() {
        return role.equals("ADMIN");
    }
}
