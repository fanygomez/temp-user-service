package com.bci.user_service.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGetRespDto extends UserRespDto implements Serializable {
    private String name;
// todo: remover token de respuesta
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
