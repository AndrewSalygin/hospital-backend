package com.andrewsalygin.gateway.exception;

import org.springframework.http.HttpStatus;

public class PatientNotFoundException extends HospitalException {

    public PatientNotFoundException() {
        super("PatientNotFoundException", "Пользователь не найден", HttpStatus.NOT_FOUND);
    }
}
