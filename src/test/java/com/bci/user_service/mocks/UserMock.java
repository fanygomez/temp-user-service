package com.bci.user_service.mocks;

import com.bci.user_service.domain.models.User;
import com.bci.user_service.dto.user.UserGetRespDto;
import com.bci.user_service.dto.user.UserReqDto;
import com.bci.user_service.dto.user.UserRespDto;
import com.bci.user_service.dto.user.token.LoginReqDto;
import com.bci.user_service.dto.user.token.LoginRespDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.bci.user_service.mocks.PhoneMock.mockPhoneDto;

public class UserMock {
    public static final String TOKEN_EX = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNjg0OGYwZi1mYzlhLTQ0ZjAtYTA0OC0yMTcwMTRhZGNhMmMiLCJpYXQiOjE3NDM3ODE2OTEsImV4cCI6MTc0Mzg2ODA5MX0.Uh9FRf2h64xrjlg4h6z3iosTBahkM4kRwbindxgiPSM";
    public static final UUID UUID_EX = UUID.fromString("b7fbf759-1204-42fa-8028-41cf68d4fff8");
    public static LocalDateTime now = LocalDateTime.now();
    public static User mockUserEntity(){
        var user = new User();
        user.setId(UUID_EX);
        user.setName("Stefhani");
        user.setEmail("stefhani.gomez@gmail.com");
        user.setPassword("$2a$10$rqWot6zLTr3djKH9DgRpveTmMGm.HMvkvOrElpcTlLhL6vf0XdCQa");

        LocalDateTime now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);
        user.setActive(true);
        user.setToken(TOKEN_EX);
        return user;
    }
    public static UserReqDto mockUserReqDto(){
        var reqDto = new UserReqDto();
        reqDto.setName("Stefhani");
        reqDto.setEmail("stefhani.gomez@gmail.com");
        reqDto.setPassword("Test@123");
        reqDto.setPhones(List.of(mockPhoneDto()));
        return reqDto;
    }
    public static LoginReqDto mockLoginReqDto(){
        return new LoginReqDto(mockUserReqDto().getEmail(),mockUserReqDto().getPassword());
    }
    public static UserRespDto mockUserRespDto(){
        var user = new UserRespDto();
        user.setId(UUID_EX);
        LocalDateTime now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);
        user.setIsActive(true);
        user.setToken(TOKEN_EX);
        return user;
    }
    public static UserGetRespDto mockUserGetRespDto(){
        var user = new UserGetRespDto();
        user.setId(UUID_EX);
        user.setName("Stefhani");
        LocalDateTime now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);
        user.setToken(TOKEN_EX);
        return user;
    }
    public static LoginRespDto mockLoginRespDto(){
        return new LoginRespDto(TOKEN_EX);
    }

}
