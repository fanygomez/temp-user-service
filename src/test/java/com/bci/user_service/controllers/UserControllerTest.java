package com.bci.user_service.controllers;

import com.bci.user_service.components.jwt.JwtTokenService;
import com.bci.user_service.domain.repositories.IUserRepository;
import com.bci.user_service.dto.user.UserReqDto;
import com.bci.user_service.service.IUserService;
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

import java.util.Optional;
import java.util.UUID;

import static com.bci.user_service.components.utils.constants.APIField.USER_API;
import static com.bci.user_service.mocks.UserMock.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private IUserService userService;
    ObjectMapper mapper;
    @Mock
    Logger log;
    @Autowired
    private JwtTokenService jwtTokenService;
    @MockBean
    IUserRepository userRepository;
    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }


    @Test
    void saveUser() throws Exception {
        when(userService.saveUser(any(UserReqDto.class)))
                .thenReturn(mockUserRespDto());

        mockMvc.perform(post(USER_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(mockUserReqDto())))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.isActive").exists())
                .andExpect(jsonPath("$.token").value(mockUserRespDto().getToken()));
    }
    @Test
     void getUserById_throwUnauthorized() throws Exception {
        mockMvc.perform(get(USER_API.concat("/").concat(String.valueOf(UUID_EX)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void getUserById() throws Exception {
        var token = jwtTokenService.generateToken(UUID_EX);
        mockUserGetRespDto().setToken(token);

        when(userRepository.existsByTokenAndIsActiveIsTrue(token)).thenReturn(true);
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(mockUserEntity()));
        when(userService.getUserById(any(UUID.class)))
                .thenReturn(mockUserGetRespDto());
        mockMvc.perform(get(USER_API.concat("/").concat(String.valueOf(UUID_EX)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer ".concat(token))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists());
    }
    @Test
    void getUserById_tokenNoValid() throws Exception {
        when(userService.getUserById(any(UUID.class)))
                .thenReturn(mockUserGetRespDto());
        var token = jwtTokenService.generateToken(UUID_EX);

        mockMvc.perform(get(USER_API.concat("/").concat("0255f11b-fe76-4bde-88a1-a7c2295bf8f3"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer ".concat(token))
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists());
    }
}