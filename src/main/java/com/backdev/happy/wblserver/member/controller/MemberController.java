package com.backdev.happy.wblserver.member.controller;

import com.backdev.happy.wblserver.global.config.security.JwtTokenProvider;
import com.backdev.happy.wblserver.member.domain.Member;
import com.backdev.happy.wblserver.member.dto.MemberDto;
import com.backdev.happy.wblserver.member.service.MemberManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberManageService memberManageService;
    private final JwtTokenProvider jwtTokenProvider; // jwt 쓴다면 이용할 예정

    @GetMapping(value = "/a/hello")
    public String hello(){
        return "hello world";
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<String> signIn(@RequestBody MemberDto.SignInRequest request) {
        Member member = memberManageService.signInMember(request);
        // return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(customer.getMsrl()), customer.getRoles()));
        return new ResponseEntity<>("success to sign in: "+member.toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signUp(@RequestBody MemberDto.SignUpRequest request) {
        Member member = memberManageService.signUpMember(request);
        return new ResponseEntity<>("success to sign up: "+member.toString(), HttpStatus.OK);
    }

}