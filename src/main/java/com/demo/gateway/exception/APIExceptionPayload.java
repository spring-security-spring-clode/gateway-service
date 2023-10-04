package com.demo.gateway.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class APIExceptionPayload {
    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime dateTime;

    // for error details
    //private final Throwable errorDetails;
}
