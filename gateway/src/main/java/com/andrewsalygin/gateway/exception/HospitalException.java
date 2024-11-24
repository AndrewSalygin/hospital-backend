package com.andrewsalygin.gateway.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HospitalException extends RuntimeException {
    private final String description;
    private final HttpStatus status;

    public HospitalException(String description, String message, HttpStatus status) {
        super(message);
        this.description = description;
        this.status = status;
    }
}
