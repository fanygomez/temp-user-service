package com.bci.user_service.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.bci.user_service.components.utils.constants.GeneralField.LOCAL_DATE_TIME_FORMAT_PATTERN;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRespDto {
    private UUID id;
    private String token;
    private Boolean isActive;
    @DateTimeFormat(pattern = LOCAL_DATE_TIME_FORMAT_PATTERN)
    @JsonFormat(pattern = LOCAL_DATE_TIME_FORMAT_PATTERN)
    private LocalDateTime created;
    @DateTimeFormat(pattern = LOCAL_DATE_TIME_FORMAT_PATTERN)
    @JsonFormat(pattern = LOCAL_DATE_TIME_FORMAT_PATTERN)
    private LocalDateTime modified;
    @DateTimeFormat(pattern = LOCAL_DATE_TIME_FORMAT_PATTERN)
    @JsonFormat(pattern = LOCAL_DATE_TIME_FORMAT_PATTERN)
    private LocalDateTime lastLogin;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
