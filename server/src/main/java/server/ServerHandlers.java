package server;

import com.google.gson.Gson;
import service.AuthService;
import service.GameService;
import service.UserService;

import spark.Request;
import spark.Response;

public class ServerHandlers {
    private final UserService userService;
    private final GameService gameService;
    private final AuthService authService;
    public ServerHandlers(){
        userService = new UserService();
        gameService = new GameService();
        authService = new AuthService();
    }

    public Object clearAllHandler(Request req, Response res){
        /*
        Clear ALL data from the database
               This includes users and all game data.
               No authorization authToken is required.
         */
        System.out.println("clearAllHandler Called");
        try {
            userService.clearAll();
            gameService.clearAll();
            authService.clearAll();

            res.status(200);
            return new Gson().toJson("");
        } catch (Exception e){
            res.status(500);
            return new Gson().toJson(e.getMessage());
        }
    }
    public Object registerHandler(Request req, Response res){

        /*
        Register a user
            If successful, an authorization authToken is returned.
            You may use the authToken with future requests that require authorization.
            No authorization authToken is required to call this endpoint.
         */

        return null;
    }
    public Object loginHandler(Request req, Response res){
        /*
        Log in a user
            If successful, an authorization authToken is returned.
            You may use the authToken with future requests that require authorization.
            No authorization authToken is required to call this endpoint.
         */
        return null;
    }
    public Object logoutHandler(Request req, Response res){
        /*
        Logs out an authenticated user
            An authToken is required to call this endpoint.
         */
        return null;
    }
    public Object listGamesHandler(Request req, Response res){
        /*
        Lists all the games in the database
            This API does not take a request body.
            The response JSON lists all the games, including the board.
            An authToken is required to call this endpoint.
         */
        return null;
    }
    public Object createGameHandler(Request req, Response res){
        /*
        Create a new Chess Game
            The request body must contain a name for the game.
            The response JSON contains the ID of created game, or if failed,
            an error message describing the reason.
            An authToken is required to call this endpoint.
         */
        return null;
    }
    public Object joinGameHandler(Request req, Response res){
        /*
        Join a Chess Game
            The request body must contain the game ID.
            If no color is specified then the user is joined as an observer.
            An authToken is required to call this endpoint.
         */
        return null;
    }
}


/*
        Spark.post("/user", (req,res) -> ServerHandlers.registerHandler());
        Spark.post("/session", (req,res) -> ServerHandlers.loginHandler());
        Spark.delete("/session", (req,res) -> ServerHandlers.logoutHandler());
        Spark.get("/game", (req,res) -> ServerHandlers.listGamesHandler());
        Spark.post("/game", (req,res) -> ServerHandlers.createGameHandler());
        Spark.put("/game", (req,res) -> ServerHandlers.joinGameHandler());
        Spark.delete("/db", (req,res) -> ServerHandlers.clearAllHandler());

 */