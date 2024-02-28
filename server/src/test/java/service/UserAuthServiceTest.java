package service;

import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class UserAuthServiceTest {

    @Test
    void register() {
        HashSet<AuthData> authDatabase = new HashSet<AuthData>();
        HashSet<UserData> userDatabase = new HashSet<UserData>();

        assertNotNull(authDatabase);
        assertNotNull(userDatabase);
    }

    @Test
    void login() {
    }

    @Test
    void logout() {
    }

    @Test
    void clearAll() {
    }
}