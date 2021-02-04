package com.backdev.happy.wblserver.auth.controller;

import com.backdev.happy.wblserver.auth.domain.dto.SigninDTO;
import com.backdev.happy.wblserver.auth.domain.dto.SignupDTO;
import com.backdev.happy.wblserver.auth.domain.entity.Member;
import com.backdev.happy.wblserver.auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/auth")
@Log4j2
public class AuthController{

    private final AuthService authService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDTO signupDTO){

        return ResponseEntity.ok("");
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<String> signin(@RequestBody SigninDTO signinDTO, HttpServletRequest request, RedirectAttributes redirectAttributes){
        return ResponseEntity.ok().body("");
    }



}