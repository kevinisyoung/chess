package clientTests;

import model.GameData;
import org.junit.jupiter.api.*;
import server.ResponseException;
import server.Server;
import server.ServerFacade;


public class ServerFacadeTests {

    private static Server server;
    private static ServerFacade serverFacade;


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


    @Test
    public void sampleTest() {
        Assertions.assertTrue(true);
    }


    @Test
    public void testLogoutCorrect() {
//        serverFacade.register("newUser","newPassword", "newEmail");
        serverFacade.login("joe","joe");
        Assertions.assertDoesNotThrow(() -> serverFacade.logout());
    }

    @Test
    public void listGamesCorrect() {
//        serverFacade.register("newUser","newPassword", "newEmail");
        serverFacade.login("joe","joe");
        var games = Assertions.assertDoesNotThrow(() -> serverFacade.listGames());
        Assertions.assertEquals(1,games.size());
        int iteration = 0;
        System.out.println("Games:");
        for (GameData game : games) {
            iteration++;
            System.out.println("  " + iteration + ": " + game.gameName());
        }
    }

}
