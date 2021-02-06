package com.backdev.happy.wblserver.auth.exception;

import com.backdev.happy.wblserver.global.exception.BusinessException;
import com.backdev.happy.wblserver.global.exception.ErrorCode;
import com.backdev.happy.wblserver.global.exception.FieldError;

import java.util.Collections;
import java.util.List;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String field, String value) {
        super(ErrorCode.LOGIN_INPUT_INVALID,
                Collections.singletonList(new FieldError(field, value, "Not Found")),
                "Login Input Invalid");
    }

    public UserNotFoundException() {
        super(ErrorCode.LOGIN_INPUT_INVALID, Collections.emptyList(), "Login Input Invalid");
    }
}
