package com.backdev.happy.wblserver.auth.exception;

import com.backdev.happy.wblserver.global.exception.BusinessException;
import com.backdev.happy.wblserver.global.exception.ErrorCode;
import com.backdev.happy.wblserver.global.exception.FieldError;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DuplicatedNameException extends BusinessException {

    public DuplicatedNameException(String field, String value) {
        super(ErrorCode.DUPLICATED_INPUT_VALUE,
                Collections.singletonList(new FieldError(field, value, "Duplicated Value")),
                "Duplicated Value");
    }
    public DuplicatedNameException(List<FieldError> errors) {
        super(ErrorCode.DUPLICATED_INPUT_VALUE, errors, "Duplicated Value");
    }
}
