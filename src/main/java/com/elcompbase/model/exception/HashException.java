package com.elcompbase.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class HashException extends RuntimeException {
    public HashException(Throwable cause) {
        super(cause);
    }
}
