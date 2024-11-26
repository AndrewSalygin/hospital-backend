package com.andrewsalygin.applicationservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StringWrapper {

    private String string;

    public StringWrapper() {
    }

    public StringWrapper(String string) {
        this.string = string;
    }
}
