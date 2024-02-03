package com.dexa.exception;

import com.dexa.models.RestWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DexaGlobalExceptionHandler {
    @ExceptionHandler(DexaException.class)
    public ResponseEntity<?> dexaException(Exception ex) {
        return new RestWrapper<>()
                .responseWrapper(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        null);
    }
}
