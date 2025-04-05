package com.bci.user_service.domain.models;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;
@MappedSuperclass
public abstract class BaseEntity  {
    private LocalDateTime created;
    private LocalDateTime modified;

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
}
