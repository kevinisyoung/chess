package clientTests;

import chess.ChessGame;
import model.GameData;
import org.junit.jupiter.api.*;
import facade.ResponseException;
import server.Server;
import facade.ServerFacade;

import java.util.HashSet;
import java.util.UUID;


public class ServerFacadeTests {

    private static Server server;
    private static ServerFacade serverFacade;

    private static String testUsername = "";

    private boolean isLoggedIn = false;


    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        serverFacade = new ServerFacade("http://localhost:"+port);
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }

    @AfterEach
    public void logout() throws ResponseException {
        if (isLoggedIn){
            serverFacade.logout();
        }
    }

    @Test
    public void registerCorrect() {
        testUsername = UUID.randomUUID().toString();
        Assertions.assertDoesNotThrow(() -> serverFacade.register(testUsername, "TESTPASSWORD", "TESTEMAIL"));
        isLoggedIn = true;
    }
    @Test
    public void registerUserAlreadyExists() {
        testUsername = UUID.randomUUID().toString();
        Assertions.assertDoesNotThrow(() -> serverFacade.register(testUsername, "TESTPASSWORD", "TESTEMAIL"));
        Assertions.assertThrows(Exception.class, () -> serverFacade.register(testUsername, "TESTPASSWORD", "TESTEMAIL"));
        isLoggedIn = true;
    }
    @Test
    public void registerIncomplete() {
        testUsername = UUID.randomUUID().toString();
        Assertions.assertThrows(Exception.class, () -> serverFacade.register(testUsername, null, "TESTEMAIL"));
        Assertions.assertThrows(Exception.class, () -> serverFacade.register(testUsername, "TESTPASSWORD", null));
    }

    @Test
    public void loginCorrect(){
        testUsername = UUID.randomUUID().toString();
        Assertions.assertDoesNotThrow(() -> serverFacade.register(testUsername, "TESTPASSWORD", "TESTEMAIL"));
        Assertions.assertDoesNotThrow(() -> serverFacade.logout());
        Assertions.assertDoesNotThrow(() -> serverFacade.login(testUsername, "TESTPASSWORD"));
        isLoggedIn = true;
    }
    @Test
    public void loginWrongPassword(){
        testUsername = UUID.randomUUID().toString();
        Assertions.assertDoesNotThrow(() -> serverFacade.register(testUsername, "TESTPASSWORD", "TESTEMAIL"));
        isLoggedIn = true;
    }
    @Test
    public void loginWrongUsername(){
        testUsername = UUID.randomUUID().toString();
        Assertions.assertDoesNotThrow(() -> serverFacade.register(testUsername, "TESTPASSWORD", "TESTEMAIL"));
        Assertions.assertDoesNotThrow(() -> serverFacade.logout());
        Assertions.assertThrows(Exception.class, () -> serverFacade.login("WRONGTESTUSERNAME", "TESTPASSWORD"));
    }

    @Test
    public void logoutCorrect() {
        testUsername = UUID.randomUUID().toString();
        Assertions.assertDoesNotThrow(() -> serverFacade.register(testUsername, "TESTPASSWORD", "TESTEMAIL"));
        Assertions.assertDoesNotThrow(() -> serverFacade.logout());
    }

    @Test
    public void logoutNotLoggedIn() {
        testUsername = UUID.randomUUID().toString();
        Assertions.assertNotEquals("",testUsername);
    }

    @Test
    public void createGameCorrect() {
        testUsername = UUID.randomUUID().toString();
        Assertions.assertDoesNotThrow(() -> serverFacade.register(testUsername, "TESTPASSWORD", "TESTEMAIL"));
        HashSet<GameData> oldGames = Assertions.assertDoesNotThrow(() -> (HashSet<GameData>) serverFacade.listGames());
        int oldGamesNum = oldGames.size();
        //create the new game
        Assertions.assertDoesNotThrow(() -> serverFacade.createGame("TEST-GAME"));

        HashSet<GameData> newGames = Assertions.assertDoesNotThrow(() -> (HashSet<GameData>) serverFacade.listGames());
        int newGamesNum = newGames.size();

        Assertions.assertTrue(newGamesNum > oldGamesNum);
        isLoggedIn = true;

    }

    @Test
    public void createGameIncorrect() {
        testUsername = UUID.randomUUID().toString();
        Assertions.assertThrows(Exception.class, () -> serverFacade.createGame("TEST-GAME"));
    }

    @Test
    public void listGamesCorrect() {
        testUsername = UUID.randomUUID().toString();
        Assertions.assertDoesNotThrow(() -> serverFacade.register(testUsername, "TESTPASSWORD", "TESTEMAIL"));
        HashSet<GameData> games = Assertions.assertDoesNotThrow(() -> (HashSet<GameData>) serverFacade.listGames());
        isLoggedIn = true;
    }

    @Test
    public void listGamesIncorrect() {
        Assertions.assertThrows(Exception.class, () -> serverFacade.listGames());

    }
    @Test
    public void joinGameCorrect() {
        testUsername = UUID.randomUUID().toString();
        Assertions.assertDoesNotThrow(() -> serverFacade.register(testUsername, "TESTPASSWORD", "TESTEMAIL"));
        int gameCreatedID = Assertions.assertDoesNotThrow(() -> serverFacade.createGame("TEST-GAME"));
        Assertions.assertDoesNotThrow(() ->  serverFacade.joinGame(gameCreatedID, ChessGame.TeamColor.WHITE));
        isLoggedIn = true;
    }

    @Test
    public void joinGameIncorrect() {
        testUsername = UUID.randomUUID().toString();
        Assertions.assertDoesNotThrow(() -> serverFacade.register(testUsername, "TESTPASSWORD", "TESTEMAIL"));
        int gameCreatedID = Assertions.assertDoesNotThrow(() -> serverFacade.createGame("TEST-GAME")) + 99;
        Assertions.assertDoesNotThrow(() ->  serverFacade.joinGame(gameCreatedID, ChessGame.TeamColor.WHITE));
        isLoggedIn = true;
    }


}
