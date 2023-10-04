package com.demo.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler(value = {APIException.class})
    public ResponseEntity<APIExceptionPayload> handleException(APIException e) {
        return new ResponseEntity<>(new APIExceptionPayload(
                e.getMessage(),
                HttpStatus.BAD_GATEWAY,
                LocalDateTime.now()
        ), HttpStatus.BAD_REQUEST);
    }
}
