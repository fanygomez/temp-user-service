package com.bci.user_service.domain.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.bci.user_service.mocks.PhoneMock.mockPhoneEntity;
import static com.bci.user_service.mocks.UserMock.mockUserEntity;
import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {
    @Mock
    Phone phone;
    @BeforeEach
    void setUp() {
        phone = new Phone();
    }
    @Test
    void setId() {
        phone.setId(mockPhoneEntity().getId());

        assertEquals(mockPhoneEntity().getId(), phone.getId());
    }
    @Test
    void setNumber() {
        phone.setNumber(mockPhoneEntity().getNumber());

        assertEquals(mockPhoneEntity().getNumber(), phone.getNumber());
    }
    @Test
    void setCityCode() {
        phone.setCityCode(mockPhoneEntity().getCityCode());

        assertEquals(mockPhoneEntity().getCityCode(), phone.getCityCode());
    }

    @Test
    void setCountryCode() {
        phone.setCountryCode(mockPhoneEntity().getCountryCode());

        assertEquals(mockPhoneEntity().getCountryCode(), phone.getCountryCode());
    }

    @Test
    void setUser() {
        phone.setUser(mockUserEntity());

        assertEquals(mockUserEntity().getId(), phone.getUser().getId());
    }
}