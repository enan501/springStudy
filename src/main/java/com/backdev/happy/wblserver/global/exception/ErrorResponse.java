package com.backdev.happy.wblserver.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponse {
    int status;
    String message;
    String code;
    List<FieldError> errors;


    public ErrorResponse(ErrorCode errorCode){
        this.status = errorCode.status;
        this.code = errorCode.code;
        this.message = errorCode.message;
    }

    public ErrorResponse(ErrorCode errorCode, List<FieldError> errors){
        this(errorCode);
        this.errors = errors;

    }

}
