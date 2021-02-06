package com.backdev.happy.wblserver.auth.exception;

import com.backdev.happy.wblserver.global.exception.BusinessException;
import com.backdev.happy.wblserver.global.exception.ErrorCode;
import com.backdev.happy.wblserver.global.exception.FieldError;

import java.util.Collections;
import java.util.List;

public class WrongPasswordException extends BusinessException {

    public WrongPasswordException() {
        super(ErrorCode.LOGIN_INPUT_INVALID,
                Collections.singletonList(new FieldError("Password", "", "Wrong Value")),
                "Login Input Invalid");
    }

    public WrongPasswordException(List<FieldError> errors) {
        super(ErrorCode.LOGIN_INPUT_INVALID, errors, "Login Input Invalid");
    }
}
