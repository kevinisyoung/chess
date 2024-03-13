package dataAccessTests;

import Exceptions.DataAccessException;
import chess.ChessGame;
import dataAccess.DatabaseGameDAO;
import model.GameData;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class dataAccessTests {

    @Test
    void clearAll() {
    }

    @Test
    void createGameGetGameTest() throws SQLException, DataAccessException {
        var testDAO = new DatabaseGameDAO();

        var gameName = UUID.randomUUID().toString();
        var testGame = new ChessGame();
        GameData testGameData = new GameData(0,null,null,"MONKEY!",new ChessGame());

        testDAO.createGame(testGameData);
        assertNotNull(testDAO.getGame(1));
    }

    @Test
    void updateGameTest() {
    }

    @Test
    void getGames() {
    }
}