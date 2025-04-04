package com.bci.user_service.service.impl;

import com.bci.user_service.components.exceptions.DuplicateDataException;
import com.bci.user_service.components.exceptions.GeneralException;
import com.bci.user_service.components.jwt.JwtTokenService;
import com.bci.user_service.domain.models.User;
import com.bci.user_service.domain.repositories.IUserRepository;
import com.bci.user_service.dto.user.UserGetRespDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static com.bci.user_service.mocks.UserMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    IUserRepository userRepository;
    @Mock
    JwtTokenService jwtTokenService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    Logger log;
    @Test
    void saveUser_success() {
        when(userRepository.findByEmailAndIsActiveIsTrue(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("");
        when(userRepository.save(any())).thenReturn(mockUserEntity());
        when(jwtTokenService.generateToken(mockUserEntity().getId())).thenReturn("");
        userRepository.updateToken(anyString(),any(UUID.class));

        var response = userService.saveUser(mockUserReqDto());

        assertNotNull(response);
        assertEquals(mockUserEntity().getId(),response.getId());
        verify(userRepository,atMostOnce()).findByEmailAndIsActiveIsTrue(anyString());
    }
    @Test
    void saveUser_throwDuplicateDataException() {
        when(userRepository.findByEmailAndIsActiveIsTrue(anyString())).thenReturn(Optional.of(mockUserEntity()));

        var exception = assertThrows(DuplicateDataException.class,() -> userService.saveUser(mockUserReqDto()));

        assertNotNull(exception);
        assertEquals("El correo ya registrado", exception.getMessage());
        verify(passwordEncoder,times(0)).encode(mockUserReqDto().getPassword());
    }

    @Test
    void getUserById_success() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(mockUserEntity()));
        when(modelMapper.map( any(User.class),eq(UserGetRespDto.class))).thenReturn(mockUserGetRespDto());
        var response = userService.getUserById(UUID.fromString("b7fbf759-1204-42fa-8028-41cf68d4fff8"));

        assertNotNull(response);
        assertEquals(mockUserEntity().getId(),response.getId());
        verify(modelMapper,times(1)).map( any(User.class),eq(UserGetRespDto.class));
    }
    @Test
    void getUserById_throwGeneralException() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        var exception = assertThrows(GeneralException.class,() -> userService.getUserById(UUID.fromString("8813d8db-9490-4ee0-a567-e1ec93067e78")));

        assertNotNull(exception);
        assertEquals("No encontro data con ese identificador", exception.getMessage());
        verify(modelMapper,times(0)).map( any(),eq(UserGetRespDto.class));
    }

    @Test
    void findByEmail() {
        when(userRepository.findByEmailAndIsActiveIsTrue(anyString())).thenReturn(Optional.of(mockUserEntity()));

        var response = userService.findByEmail(mockUserReqDto().getEmail());

        assertNotNull(response);
        assertEquals(mockUserEntity().getId(),response.getId());
    }
}