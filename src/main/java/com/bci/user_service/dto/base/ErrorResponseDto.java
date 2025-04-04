package com.bci.user_service.dto.base;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ErrorResponseDto {
    private String message;
    private List<String> errors;

    public ErrorResponseDto(String message) {
        this.message = message;
    }
}
