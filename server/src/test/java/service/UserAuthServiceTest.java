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
        UserData userData = new UserData("username","password","email");

        UserAuthService userAuthService = new UserAuthService();
        userAuthService.register(userData);
        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
        assertEquals(1,userAuthService.DAO.getUserDatabase().size());

    }

    @Test
    void registerInvalid() throws UserAlreadyExistsException {
        UserAuthService userAuthService = new UserAuthService();

        UserData userData = new UserData("username","password","email");
        userAuthService.register(userData);

        assertThrows(UserAlreadyExistsException.class, () -> userAuthService.register(userData));
    }

    @Test
    void clearAllDatabase() throws UserAlreadyExistsException {
        UserAuthService userAuthService = new UserAuthService();

        UserData userData = new UserData("username","password","email");
        userAuthService.register(userData);
        userAuthService.clearAll();


        assertEquals(0,userAuthService.DAO.getAuthDatabase().size());
        assertEquals(0,userAuthService.DAO.getUserDatabase().size());

    }


    @Test
    void login() {
    }

    @Test
    void logout() {
    }



}