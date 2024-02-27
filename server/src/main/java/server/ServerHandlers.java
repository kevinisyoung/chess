package server;

import Exceptions.UserAlreadyExistsException;
import com.google.gson.Gson;
import model.AuthData;
import model.UserData;
import org.eclipse.jetty.server.Authentication;
import returnRecords.AuthResponse;
import service.GameService;
import service.UserAuthService;

import spark.Request;
import spark.Response;

public class ServerHandlers {
    UserAuthService userAuthService;
    GameService gameService;
    Gson gson;
    public ServerHandlers(){
        gson = new Gson();
        userAuthService = new UserAuthService();
        gameService = new GameService();
    }

    public Object clearAllHandler(Request req, Response res){
        /*
        Clear ALL data from the database
               This includes users and all game data.
               No authorization authToken is required.
         */
        System.out.println("clearAllHandler Called");
        try {
            userAuthService.clearAll();
            gameService.clearAll();

            res.status(200);
            return new Gson().toJson(new Object());
        } catch (Exception e){
            res.status(400);
            return new Gson().toJson(e.getMessage());
        }
    }
    public Object registerHandler(Request req, Response res){
        System.out.println("registerHandler Called");
        UserData requestData;
        try {
            requestData = gson.fromJson(req.body(), UserData.class);
            if (requestData.username() == null || requestData.password() == null || requestData.email() == null){
                res.status(400);
                return new Gson().toJson(new Object());
            }
            //success
            AuthData returnedAuthData = userAuthService.register(requestData);
            res.status(200);
            return new Gson().toJson(new AuthResponse(returnedAuthData.username(), returnedAuthData.authToken()));
        } catch (UserAlreadyExistsException e){
            res.status(403);
            return new Gson().toJson(e.getMessage());
        } catch (Exception e){
            res.status(500);
            return new Gson().toJson(e.getMessage());
        }

        /*
        Register a user
            If successful, an authorization authToken is returned.
            You may use the authToken with future requests that require authorization.
            No authorization authToken is required to call this endpoint.
         */

//        return null;
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