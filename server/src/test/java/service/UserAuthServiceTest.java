package service;

import Exceptions.DataAccessException;
import Exceptions.UserAlreadyExistsException;
import additionalRecords.GameRequest;
import additionalRecords.LoginData;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Test;
import server.Server;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class UserAuthServiceTest {

    @Test
    void registerInserts() throws UserAlreadyExistsException, DataAccessException {
        UserData userData = new UserData("username","password","email");

        UserAuthService userAuthService = new UserAuthService();
        userAuthService.register(userData);
        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
        assertEquals(1,userAuthService.DAO.getUserDatabase().size());

    }

    @Test
    void registerInvalid() throws UserAlreadyExistsException, DataAccessException {
        UserAuthService userAuthService = new UserAuthService();

        UserData userData = new UserData("username","password","email");
        userAuthService.register(userData);

        assertThrows(UserAlreadyExistsException.class, () -> userAuthService.register(userData));
    }

    @Test
    void clearAllDatabase() throws UserAlreadyExistsException, DataAccessException {
        UserAuthService userAuthService = new UserAuthService();

        UserData userData = new UserData("username","password","email");
        userAuthService.register(userData);
        userAuthService.clearAll();


        assertEquals(0,userAuthService.DAO.getAuthDatabase().size());
        assertEquals(0,userAuthService.DAO.getUserDatabase().size());

    }

    @Test
    void loginIncorrect() throws DataAccessException, UserAlreadyExistsException {

        UserData userData = new UserData("username","password","email");

        UserAuthService userAuthService = new UserAuthService();
        userAuthService.register(userData);
        userAuthService.login(new LoginData(userData.username(), userData.password()));
        assertThrows(DataAccessException.class, () -> userAuthService.login(new LoginData("WRONGUSERNAME", userData.password())));
    }

    @Test
    void logoutCorrect() throws UserAlreadyExistsException, DataAccessException {
        UserData userData = new UserData("username","password","email");

        UserAuthService userAuthService = new UserAuthService();
        AuthData authData = userAuthService.register(userData);
        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());

        userAuthService.logout(authData.authToken());
        assertEquals(0,userAuthService.DAO.getAuthDatabase().size());
    }

    @Test
    void logoutIncorrect() throws UserAlreadyExistsException, DataAccessException {
        UserData userData = new UserData("username","password","email");

        UserAuthService userAuthService = new UserAuthService();
        AuthData authData = userAuthService.register(userData);
        assertEquals(1,userAuthService.DAO.getAuthDatabase().size());
        assertThrows(DataAccessException.class, () -> userAuthService.logout("123"));
    }



}