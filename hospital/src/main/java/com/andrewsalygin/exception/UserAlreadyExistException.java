package com.andrewsalygin.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends HospitalException {

    public UserAlreadyExistException() {
        super("UserAlreadyExistException", String.format("Пользователь уже существует"), HttpStatus.CONFLICT);
    }
}
