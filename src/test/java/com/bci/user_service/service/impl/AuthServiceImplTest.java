package com.bci.user_service.service.impl;

import com.bci.user_service.components.exceptions.GeneralException;
import com.bci.user_service.components.jwt.JwtTokenService;
import com.bci.user_service.dto.user.token.LoginReqDto;
import com.bci.user_service.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static com.bci.user_service.mocks.UserMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @InjectMocks
    AuthServiceImpl authService;
    @Mock
    IUserService userService;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    JwtTokenService jwtTokenService;
    @Mock
    Logger log;
    @Test
    void login_success() {
        when(userService.findByEmail(anyString())).thenReturn(mockUserEntity());
        when(passwordEncoder.matches(anyString(),anyString())).thenReturn(true);
        when(jwtTokenService.generateToken(mockUserEntity().getId())).thenReturn(TOKEN_EX);
        userService.updateUserTokenByUserId(any(UUID.class),anyString());
        var response = authService.login(new LoginReqDto(mockUserReqDto().getEmail(),mockUserReqDto().getPassword()));

        assertNotNull(response);
        assertEquals(TOKEN_EX,response.token());
        verify(passwordEncoder,atMostOnce()).matches(anyString(),anyString());
    }
    @Test
    void login_throwGeneralException() {
        when(userService.findByEmail(anyString())).thenReturn(mockUserEntity());
        when(passwordEncoder.matches(anyString(),anyString())).thenReturn(false);

        var exception = assertThrows(GeneralException.class,() -> authService.login(new LoginReqDto(mockUserReqDto().getEmail(),mockUserReqDto().getPassword())));

        assertNotNull(exception);
        assertEquals("Credenciales inválidas", exception.getMessage());
    }
}