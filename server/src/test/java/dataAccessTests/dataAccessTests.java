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
    void createGameGetGameTest5() throws SQLException, DataAccessException {
        var testDAO = new DatabaseGameDAO();

        var gameName = UUID.randomUUID().toString();
        var testGame = new ChessGame();
        GameData testGameData = new GameData(0,null,null,"MONKEY!",new ChessGame());

        testDAO.createGame(testGameData);
        assertNotNull(testDAO.getGame(1));
    }
    @Test
    void createGameGetGameTest4() throws SQLException, DataAccessException {
        var testDAO = new DatabaseGameDAO();

        var gameName = UUID.randomUUID().toString();
        var testGame = new ChessGame();
        GameData testGameData = new GameData(0,null,null,"MONKEY!",new ChessGame());

        testDAO.createGame(testGameData);
        assertNotNull(testDAO.getGame(1));
    }
    @Test
    void createGameGetGameTest3() throws SQLException, DataAccessException {
        var testDAO = new DatabaseGameDAO();

        var gameName = UUID.randomUUID().toString();
        var testGame = new ChessGame();
        GameData testGameData = new GameData(0,null,null,"MONKEY!",new ChessGame());

        testDAO.createGame(testGameData);
        assertNotNull(testDAO.getGame(1));
    }
    @Test
    void createGameGetGameTest2() throws SQLException, DataAccessException {
        var testDAO = new DatabaseGameDAO();

        var gameName = UUID.randomUUID().toString();
        var testGame = new ChessGame();
        GameData testGameData = new GameData(0,null,null,"MONKEY!",new ChessGame());

        testDAO.createGame(testGameData);
        assertNotNull(testDAO.getGame(1));
    }
    @Test
    void createGameGetGameTest1() throws SQLException, DataAccessException {
        var testDAO = new DatabaseGameDAO();

        var gameName = UUID.randomUUID().toString();
        var testGame = new ChessGame();
        GameData testGameData = new GameData(0,null,null,"MONKEY!",new ChessGame());

        testDAO.createGame(testGameData);
        assertNotNull(testDAO.getGame(1));
    }
    @Test
    void createGameGetGameTest10() throws SQLException, DataAccessException {
        var testDAO = new DatabaseGameDAO();

        var gameName = UUID.randomUUID().toString();
        var testGame = new ChessGame();
        GameData testGameData = new GameData(0,null,null,"MONKEY!",new ChessGame());

        testDAO.createGame(testGameData);
        assertNotNull(testDAO.getGame(1));
    }
    @Test
    void createGameGetGameTest9() throws SQLException, DataAccessException {
        var testDAO = new DatabaseGameDAO();

        var gameName = UUID.randomUUID().toString();
        var testGame = new ChessGame();
        GameData testGameData = new GameData(0,null,null,"MONKEY!",new ChessGame());

        testDAO.createGame(testGameData);
        assertNotNull(testDAO.getGame(1));
    }
    @Test
    void createGameGetGameTest8() throws SQLException, DataAccessException {
        var testDAO = new DatabaseGameDAO();

        var gameName = UUID.randomUUID().toString();
        var testGame = new ChessGame();
        GameData testGameData = new GameData(0,null,null,"MONKEY!",new ChessGame());

        testDAO.createGame(testGameData);
        assertNotNull(testDAO.getGame(1));
    }
    @Test
    void createGameGetGameTest7() throws SQLException, DataAccessException {
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