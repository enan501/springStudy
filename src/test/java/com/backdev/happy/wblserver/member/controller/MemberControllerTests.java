package com.backdev.happy.wblserver.member.controller;


import com.backdev.happy.wblserver.member.dto.MemberDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class MemberControllerTests {

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MemberController memberController;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(memberController)
                .apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser(roles = "SELLER")
    void hello() throws Exception {
        mockMvc.perform(get("/members/a/hello")
                //.with(user("rob").roles("USER"))
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("ROLE_CUSTOMER 타입 계정 등록")
    void sign_up_customer() throws Exception {

        //given
        MemberDto.SignUpRequest request = MemberDto.SignUpRequest.builder()
                .name("test name")
                .role("ROLE_CUSTOMER")
                .phone("01033333333")
                .password("testpw")
                .build();

        //when
        ResultActions result = mockMvc.perform(post("/members/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );

        //then
        result.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("ROLE_SELLER 타입 계정 등록")
    void sign_up_seller() throws Exception {

        //given
        MemberDto.SignUpRequest request = MemberDto.SignUpRequest.builder()
                .name("test name")
                .role("ROLE_SELLER")
                .phone("01012213222")
                .password("testpw")
                .build();

        //when
        ResultActions result = mockMvc.perform(post("/members/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        );

        //then
        result.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("계정 로그인")
    void sign_in_valid_user() throws Exception {

        //given
        MemberDto.SignInRequest request = MemberDto.SignInRequest.builder()
                .phone("01022222222")
                .password("testpw")
                .build();

        //when
        ResultActions result = mockMvc.perform(post("/members/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );

        //then
        result.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("틀린 비밀번호로 로그인 시도")
    void sign_in_wrongpasserd() throws Exception {

        //given, when
        ResultActions result = mockMvc.perform(post("/members/signin")
                .param("password","wrongpassword")
                .param("phone","01033326847"));

        //then
        result.andExpect(status().isBadRequest())
                .andDo(print());
    }

}
