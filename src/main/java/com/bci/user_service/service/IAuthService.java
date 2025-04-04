package com.bci.user_service.service;

import com.bci.user_service.dto.user.token.LoginReqDto;
import com.bci.user_service.dto.user.token.LoginRespDto;

public interface IAuthService {
    LoginRespDto login(LoginReqDto loginRequest);
}
