package com.nathanrhoden.velofinder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RiderNotFoundException extends RuntimeException {

    public RiderNotFoundException(String message) {
        super(message);
    }
}
