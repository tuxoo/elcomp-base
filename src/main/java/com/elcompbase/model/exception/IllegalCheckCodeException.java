package com.elcompbase.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalCheckCodeException extends RuntimeException {

    public IllegalCheckCodeException(String message) {
        super(message);
    }
}
