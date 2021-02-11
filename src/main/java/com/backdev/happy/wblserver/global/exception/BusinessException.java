package com.backdev.happy.wblserver.global.exception;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BusinessException extends RuntimeException {
    ErrorCode errorCode;
    List<FieldError> errors;
    String message;
}
