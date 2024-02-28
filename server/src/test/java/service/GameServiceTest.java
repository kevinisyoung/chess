package service;

import Exceptions.DataAccessException;
import Exceptions.UserAlreadyExistsException;
import additionalRecords.GameRequest;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    @Test
    void createGameCorrect() throws UserAlreadyExistsException, DataAccessException {
        UserData userData = new UserData("username","password","email");

        UserAuthService userAuthService = new UserAuthService();
        GameService gameService = new GameService();
        AuthData authData = userAuthService.register(userData);

        String authToken = authData.authToken();
        gameService.createGame(new GameRequest(authToken,"Test"));

        assertEquals(1,gameService.DAO.getGames().size());

    }

    @Test
    void createGameIncorrect() throws UserAlreadyExistsException, DataAccessException {
        UserData userData = new UserData("username","password","email");

        UserAuthService userAuthService = new UserAuthService();
        GameService gameService = new GameService();
        AuthData authData = userAuthService.register(userData);

        String authToken = "123";
        gameService.createGame(new GameRequest(authToken,"Test"));

        assertEquals(1,gameService.DAO.getGames().size());

    }
}