package com.backdev.happy.wblserver.bakery.controller;

import com.backdev.happy.wblserver.bakery.dto.BakeryDto;
import com.backdev.happy.wblserver.member.controller.MemberController;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class BakeryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @Autowired
    BakeryController bakeryController;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(bakeryController)
                .apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("(valid) ROLE_SELLER 유저의 Bakery 등록")
    @WithMockUser(roles = "SELLER")
    void register_bakery_by_valid_seller() throws Exception {

        //given
        BakeryDto.CreateRequest request = BakeryDto.CreateRequest.builder()
                .memberId(18L)
                .name("test bakery")
                .address("test address")
                .img("test img")
                .introduction("test introduction")
                .build();
        //when
        ResultActions result = mockMvc.perform(post("/bakeries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );

        //then
        result.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("(invalid) ROLE_CUSTOMER 유저의 Bakery 등록")
    @WithMockUser(roles = "CUSTOMER")
    void register_bakery_by_invalid_customer() throws Exception {
        //given
        BakeryDto.CreateRequest request = BakeryDto.CreateRequest.builder()
                .memberId(18L)
                .name("test bakery")
                .address("test address")
                .img("test img")
                .introduction("test introduction")
                .build();

        //when
        ResultActions result = mockMvc.perform(post("/bakeries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );

        //then
        result.andExpect(status().isForbidden())
                .andDo(print());
    }

}
