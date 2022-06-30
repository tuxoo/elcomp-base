package com.elcompbase.advice;

import com.elcompbase.model.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Handle exception [{}]", e.getMessage());
        HttpStatus status = Optional.ofNullable(e.getClass().getAnnotation(ResponseStatus.class)).map(ResponseStatus::value)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        ErrorResponse responseBody = new ErrorResponse(e.getMessage(), formatter.format(Instant.now()));

        return ResponseEntity.status(status).body(responseBody);
    }
}
