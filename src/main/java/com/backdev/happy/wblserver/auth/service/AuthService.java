package com.backdev.happy.wblserver.auth.service;

import com.backdev.happy.wblserver.auth.domain.dto.SigninDTO;
import com.backdev.happy.wblserver.auth.domain.dto.SignupDTO;
import com.backdev.happy.wblserver.auth.domain.dto.TokenDTO;
import com.backdev.happy.wblserver.auth.domain.entity.Member;


public interface AuthService {

    void checkDuplicated(SignupDTO signupDTO);

    void createUser(SignupDTO signupDTO);

    Member signin(SigninDTO signinDTO);

    TokenDTO getToken(Member member);
}