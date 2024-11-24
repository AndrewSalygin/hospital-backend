package com.andrewsalygin.applicationservice.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class RegisterUserRequestDTO {
    private final String email;
    private final String password;
}
