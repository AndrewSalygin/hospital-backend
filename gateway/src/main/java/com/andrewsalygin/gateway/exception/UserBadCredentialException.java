package com.andrewsalygin.gateway.exception;

import org.springframework.http.HttpStatus;

public class UserBadCredentialException extends HospitalException {

    public UserBadCredentialException() {
        super("UserBadCredentialException", "Неправильная почта или пароль", HttpStatus.CONFLICT);
    }
}
