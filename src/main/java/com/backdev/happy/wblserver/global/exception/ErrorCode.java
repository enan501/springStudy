package com.backdev.happy.wblserver.global.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE(400,"C001","Bad Request: Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C002", "Bad Request: Entity Not Found"),
    INVALID_TYPE_VALUE(400, "C003", "Bad Request: Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C004", "Access is Denied"),
    NOT_FOUND(404,"C005","Not Found"),
    METHOD_NOT_ALLOWED(405, "C006", " Invalid Input Value"),
    DUPLICATED_INPUT_VALUE(409, "C007", "Conflict: Duplicated Input Value"),
    INTERNAL_SERVER_ERROR(500, "C008","Internal Server Error"),


    // Member
    LOGIN_INPUT_INVALID(400, "M002", "Login input is invalid"),

    EXPIRED_TOKEN(401, "T001", "Invalid Token: Token expired"),
    INVALID_TOKEN(401, "T002", "Invalid Token");

    int status;
    String code;
    String message;
}
