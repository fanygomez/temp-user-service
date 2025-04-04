package com.bci.user_service.components.entry_point;

import com.bci.user_service.components.exceptions.GeneralException;
import com.bci.user_service.dto.base.ErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        String errorMessage = "Error de autenticación";


        if (authException.getCause() instanceof GeneralException exception) {
            httpStatus = exception.getHttpStatusCode();
            errorMessage = exception.getMessage();
        } else if (authException.getMessage() != null) {
            errorMessage = authException.getMessage();
        }

        response.setStatus(httpStatus.value());
        response.setContentType("application/json");

        var errorResponse = new ErrorResponseDto(errorMessage);

        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        try (PrintWriter writer = response.getWriter()) {
            writer.write(jsonResponse);
            writer.flush();
        }
    }
}
