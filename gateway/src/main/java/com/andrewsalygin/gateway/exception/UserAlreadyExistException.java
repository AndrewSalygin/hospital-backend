package com.andrewsalygin.gateway.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends HospitalException {

    public UserAlreadyExistException() {
        super("UserAlreadyExistException", "Пользователь уже существует", HttpStatus.CONFLICT);
    }
}
