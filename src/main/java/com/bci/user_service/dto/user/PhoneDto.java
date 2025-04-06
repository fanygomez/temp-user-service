package com.bci.user_service.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class PhoneDto implements Serializable {
    @NotBlank(message = "El campo number es requerido")
    @Schema(name = "number", description = "Numero de telefono", example = "72903241")
    private String number;
    @NotBlank(message = "El campo cityCode es requerido")
    @Schema(name = "cityCode", description = "Codigo de cuidad", example = "1214")
    private String cityCode;
    @NotBlank(message = "El campo countryCode es requerido")
    @Schema(name = "countryCode", description = "Codigo de pais", example = "503")
    private String countryCode;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
