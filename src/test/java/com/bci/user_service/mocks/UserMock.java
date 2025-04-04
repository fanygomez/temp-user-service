package com.bci.user_service.mocks;

import com.bci.user_service.domain.models.User;
import com.bci.user_service.dto.user.UserGetRespDto;
import com.bci.user_service.dto.user.UserReqDto;
import com.bci.user_service.dto.user.UserRespDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class UserMock {// UserRespDto
public static LocalDateTime now = LocalDateTime.now();
    public static User mockUserEntity(){
        var user = new User();
        user.setId(UUID.fromString("b7fbf759-1204-42fa-8028-41cf68d4fff8"));
        user.setName("Stefhani");
        user.setEmail("stefhani.gomez@gmail.com");
        user.setPassword("Test.123");

        LocalDateTime now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);

        user.setActive(true);
        user.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNjg0OGYwZi1mYzlhLTQ0ZjAtYTA0OC0yMTcwMTRhZGNhMmMiLCJpYXQiOjE3NDM3ODE2OTEsImV4cCI6MTc0Mzg2ODA5MX0.Uh9FRf2h64xrjlg4h6z3iosTBahkM4kRwbindxgiPSM");
        return user;
    }
    public static UserReqDto mockUserReqDto(){
        var reqDto = new UserReqDto();
        reqDto.setName("Stefhani");
        reqDto.setEmail("stefhani.gomez@gmail.com");
        reqDto.setPassword("Test.123");
        reqDto.setPhones(new ArrayList<>());
        return reqDto;
    }
    public static UserRespDto mockUserRespDtoEntity(){
        var user = new UserRespDto();
        user.setId(UUID.fromString("b7fbf759-1204-42fa-8028-41cf68d4fff8"));
        LocalDateTime now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);
        user.setIsActive(true);
        user.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNjg0OGYwZi1mYzlhLTQ0ZjAtYTA0OC0yMTcwMTRhZGNhMmMiLCJpYXQiOjE3NDM3ODE2OTEsImV4cCI6MTc0Mzg2ODA5MX0.Uh9FRf2h64xrjlg4h6z3iosTBahkM4kRwbindxgiPSM");
        return user;
    }
    public static UserGetRespDto mockUserGetRespDto(){
        var user = new UserGetRespDto();
        user.setId(UUID.fromString("b7fbf759-1204-42fa-8028-41cf68d4fff8"));
        user.setName("Stefhani");
        LocalDateTime now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);
        user.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNjg0OGYwZi1mYzlhLTQ0ZjAtYTA0OC0yMTcwMTRhZGNhMmMiLCJpYXQiOjE3NDM3ODE2OTEsImV4cCI6MTc0Mzg2ODA5MX0.Uh9FRf2h64xrjlg4h6z3iosTBahkM4kRwbindxgiPSM");
        return user;
    }

}
