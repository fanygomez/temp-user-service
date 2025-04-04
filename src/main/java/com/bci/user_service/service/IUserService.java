package com.bci.user_service.service;

import com.bci.user_service.domain.models.User;
import com.bci.user_service.dto.user.UserGetRespDto;
import com.bci.user_service.dto.user.UserReqDto;
import com.bci.user_service.dto.user.UserRespDto;

import java.util.UUID;

public interface IUserService {
    UserRespDto saveUser(UserReqDto reqDto);
    UserGetRespDto getUserById(UUID id);
    User findByEmail(String email);
    void updateUserTokenByUserId(UUID userId, String newToken);
}
