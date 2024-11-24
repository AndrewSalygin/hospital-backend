package com.andrewsalygin.specializationservice.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends HospitalException {

    public UserNotFoundException() {
        super("UserNotFoundException", "Пользователь не найден", HttpStatus.NOT_FOUND);
    }
}