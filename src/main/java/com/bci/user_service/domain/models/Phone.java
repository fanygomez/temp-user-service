package com.bci.user_service.domain.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(schema = "sch_security" ,name="tbl_user_phones")
@Entity
public class Phone extends BaseEntity{
    @Id
    @GeneratedValue
    private UUID id;

    private String number;
    private String cityCode;
    private String countryCode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.setCreated(now);
    }
    @PreUpdate
    public void preUpdate() {
        this.setModified(LocalDateTime.now());
    }
}
