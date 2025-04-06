package com.bci.user_service.dto.user;

import com.bci.user_service.components.validator.ValidPasswordFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;
public class UserReqDto implements Serializable {
    @NotBlank(message = "El nombre es requerido")
    @Schema(name = "name", description = "name", example = "stefhani")
    private String name;
    @NotBlank(message = "El email es requerido")
    @Pattern(
            regexp = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Za-z]{2,}$",
            message = "Formato de email inválido"
    )
    @Schema(name = "email", description = "name", example = "stefhani.gomez@gmail.com")
    private String email;
    @NotBlank(message = "La contraseña es requerida")
    @ValidPasswordFormat
    @Schema(name = "password", description = "name", example = "Test.01@")
    private String password;
    @NotNull(message = "La lista de teléfonos no puede ser nula")
    @Size(min = 1, message = "Debe incluir al menos un teléfono")
    private List<PhoneDto> phones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneDto> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDto> phones) {
        this.phones = phones;
    }
}
