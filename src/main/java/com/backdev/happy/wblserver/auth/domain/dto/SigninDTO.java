package com.backdev.happy.wblserver.auth.domain.dto;

import javax.validation.Valid;

@Valid
public class SigninDTO {
    String name;
    String password;
}
