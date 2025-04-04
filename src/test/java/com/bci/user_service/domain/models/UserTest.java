package com.bci.user_service.domain.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.bci.user_service.mocks.UserMock.mockUserEntity;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Mock
    User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testId() {
        user.setId(mockUserEntity().getId());
        assertEquals(mockUserEntity().getId(), user.getId());
    }

    @Test
    void testName() {
        user.setName(mockUserEntity().getName());

        assertEquals(mockUserEntity().getName(), user.getName());
    }

    @Test
    void testEmail() {
        user.setEmail(mockUserEntity().getEmail());

        assertEquals(mockUserEntity().getEmail(), user.getEmail());
    }

    @Test
    void testPassword() {
        user.setPassword(mockUserEntity().getPassword());

        assertEquals(mockUserEntity().getPassword(), user.getPassword());
    }

    @Test
    void testCreated() {
        user.setCreated(mockUserEntity().getCreated());

        assertEquals(mockUserEntity().getCreated(), user.getCreated());
    }

    @Test
    void testModified() {
        user.setModified(mockUserEntity().getModified());

        assertEquals(mockUserEntity().getModified(), user.getModified());
    }

    @Test
    void testLastLogin() {
        user.setLastLogin(mockUserEntity().getLastLogin());

        assertEquals(mockUserEntity().getLastLogin(), user.getLastLogin());
    }
}