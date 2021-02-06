package com.backdev.happy.wblserver.auth.domain.dto;

import lombok.Getter;

import javax.validation.Valid;

@Getter
@Valid
public class SigninDTO {
    String name;
    String password;
}
