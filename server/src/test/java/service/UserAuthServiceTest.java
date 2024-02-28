package service;

import Exceptions.UserAlreadyExistsException;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Test;
import server.Server;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class UserAuthServiceTest {

    @Test
    void registerInserts() throws UserAlreadyExistsException {
        HashSet<AuthData> authDatabase = new HashSet<AuthData>();
        HashSet<UserData> userDatabase = new HashSet<UserData>();

        UserAuthService userAuthService = new UserAuthService();
        GameService gameService = new GameService();

        UserData userData = new UserData("username","password","email");
        userAuthService.register(userData);

        assertNotNull(authDatabase);
        assertNotNull(userDatabase);
    }

    @Test
    void registerInvalid() throws UserAlreadyExistsException {
        HashSet<AuthData> authDatabase = new HashSet<AuthData>();
        HashSet<UserData> userDatabase = new HashSet<UserData>();

        UserAuthService userAuthService = new UserAuthService();
        GameService gameService = new GameService();

        UserData userData = new UserData("username","password","email");
        userAuthService.register(userData);

        assertThrows(UserAlreadyExistsException.class, () -> userAuthService.register(userData));
    }

    @Test
    void login() {
    }

    @Test
    void logout() {
    }

    @Test
    void clearAllDatabase() {
    }

}