package com.backdev.happy.wblserver.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BusinessException e) {
        ErrorResponse response = new ErrorResponse(e.errorCode,e.errors);
        return ResponseEntity.status(e.errorCode.status).body(response);
    }
}
