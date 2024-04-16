package server;

import Exceptions.DataAccessException;
import websocket.WebSocketHandler;
import spark.*;

import java.sql.SQLException;

public class Server {
    ServerHandlers serverHandlers;
    private final WebSocketHandler webSocketHandler;

    public Server() {
        serverHandlers = new ServerHandlers();
        webSocketHandler = new WebSocketHandler();
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.webSocket("/connect",webSocketHandler);


        Spark.delete("/db", serverHandlers::clearAllHandler);
        Spark.post("/user", serverHandlers::registerHandler);
        Spark.post("/session", serverHandlers::loginHandler);
        Spark.delete("/session", serverHandlers::logoutHandler);
        Spark.get("/game", serverHandlers::listGamesHandler);
        Spark.post("/game", serverHandlers::createGameHandler);
        Spark.put("/game", serverHandlers::joinGameHandler);

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
