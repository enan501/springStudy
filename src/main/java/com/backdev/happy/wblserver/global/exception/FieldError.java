package com.backdev.happy.wblserver.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@ToString
@Getter
public class FieldError implements Serializable {
    String field;
    String value;
    String reason;
}
