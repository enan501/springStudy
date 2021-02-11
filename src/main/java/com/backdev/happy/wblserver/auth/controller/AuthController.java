package com.backdev.happy.wblserver.auth.controller;

import com.backdev.happy.wblserver.auth.domain.dto.SigninDTO;
import com.backdev.happy.wblserver.auth.domain.dto.SignupDTO;
import com.backdev.happy.wblserver.auth.domain.dto.TokenDTO;
import com.backdev.happy.wblserver.auth.domain.entity.Member;
import com.backdev.happy.wblserver.auth.exception.DuplicatedNameException;
import com.backdev.happy.wblserver.auth.service.AuthService;
import com.backdev.happy.wblserver.global.exception.FieldError;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/auth")
@Log4j2
public class AuthController{

    private final AuthService authService;

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDTO signupDTO){
        authService.checkDuplicated(signupDTO);
        authService.createUser(signupDTO);
        return ResponseEntity.ok("");
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<TokenDTO> signin(@RequestBody SigninDTO signinDTO, HttpServletRequest request, HttpServletResponse response){
        Member member = authService.signin(signinDTO);
        TokenDTO tokenDTO = authService.getToken(member);
        return ResponseEntity.ok().body(tokenDTO);
    }

}