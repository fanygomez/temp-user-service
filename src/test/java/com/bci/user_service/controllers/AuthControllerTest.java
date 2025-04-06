package com.bci.user_service.controllers;

import com.bci.user_service.dto.user.token.LoginReqDto;
import com.bci.user_service.service.IAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.bci.user_service.components.utils.constants.APIField.AUTH_API;
import static com.bci.user_service.mocks.UserMock.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private IAuthService authService;
    ObjectMapper mapper;
    @Mock
    Logger log;
    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void login() throws Exception {
        when(authService.login(any(LoginReqDto.class)))
                .thenReturn(mockLoginRespDto());

        mockMvc.perform(post(AUTH_API.concat("/token"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(mockLoginReqDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.token").value(TOKEN_EX));
    }
}