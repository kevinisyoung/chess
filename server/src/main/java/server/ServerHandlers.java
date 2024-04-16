package server;

import Exceptions.DataAccessException;
import Exceptions.UserAlreadyExistsException;
import additionalRecords.*;
import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import model.UserData;
import service.GameService;
import service.UserAuthService;

import spark.Request;
import spark.Response;

import java.nio.file.AccessDeniedException;
import java.util.Map;

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

        System.out.println("clearAllHandler Called");
        try {
            userAuthService.clearAll();
            gameService.clearAll();

            res.status(200);
            return new Gson().toJson(new Object());
        } catch (Exception e){
            res.status(400);
            return new Gson().toJson(Map.of("message", e.getMessage()));
        }
    }
    public Object registerHandler(Request req, Response res){
        System.out.println("registerHandler Called");
        UserData registerRequestData;
        try {
            var reqBody = req.body();
            registerRequestData = gson.fromJson(req.body(), UserData.class);
            if (registerRequestData.username() == null || registerRequestData.password() == null || registerRequestData.email() == null){
                res.status(400);
                return new Gson().toJson(Map.of("message", "Error: request did not contain necessary values"));
            }
            //success
            AuthData returnedAuthData = userAuthService.register(registerRequestData);
            res.status(200);
            return new Gson().toJson(new AuthResponse(returnedAuthData.username(), returnedAuthData.authToken()));
        } catch (UserAlreadyExistsException e){
            res.status(403);
            return new Gson().toJson(Map.of("message", e.getMessage()));
        } catch (Exception e){
            res.status(500);
            return new Gson().toJson(Map.of("message", e.getMessage()));
        }

    }
    public Object loginHandler(Request req, Response res){
        System.out.println("loginHandler called");
        LoginData loginRequestData;
        try {
            loginRequestData = gson.fromJson(req.body(), LoginData.class);
            if (loginRequestData.username() == null || loginRequestData.password() == null){
                res.status(400);
                return new Gson().toJson(new Object());
            }
            //success
            AuthData returnedAuthData = userAuthService.login(loginRequestData);
            res.status(200);
            return new Gson().toJson(new AuthResponse(returnedAuthData.username(), returnedAuthData.authToken()));
        } catch (DataAccessException e) {
            res.status(401);
            return new Gson().toJson(Map.of("message", e.getMessage()));
        } catch (Exception e){
            res.status(500);
            return new Gson().toJson(Map.of("message", e.getMessage()));
        }

    }
    public Object logoutHandler(Request req, Response res){
        System.out.println("logoutHandler called");
        String authToken;
        try {
            authToken = req.headers("authorization");
            //success
            userAuthService.logout(authToken);
            res.status(200);
            return new Gson().toJson(new Object());
        } catch (DataAccessException e){
            res.status(401);
            return new Gson().toJson(Map.of("message", "Error: Logout failed"));
        }

    }
    public Object listGamesHandler(Request req, Response res) throws DataAccessException {
        System.out.println("listGamesHandler called");
            String authToken = req.headers("authorization");

            if (userAuthService.getAuth(authToken) == null){
                res.status(401);
                return new Gson().toJson(Map.of("message", "Error: User logged in improperly"));
            }
            GamesList gamesList = gameService.listGames();
            return new Gson().toJson(gamesList);

    }
    public Object createGameHandler(Request req, Response res){
        System.out.println("createGame called");
        try {
            String authToken = req.headers("authorization");
            if (userAuthService.getAuth(authToken) == null){
                res.status(401);
                return new Gson().toJson(Map.of("message", "Error: User logged in improperly"));
            }
            GameNameRequest gameName = gson.fromJson(req.body(), GameNameRequest.class);
            if (gameName == null || gameName.gameName().isEmpty()){
                res.status(400);
                return new Gson().toJson(Map.of("message", "Error: game name invalid"));
            }
            //success
            GameRequest gameRequest = new GameRequest(authToken, gameName.gameName());



            res.status(200);
            return new Gson().toJson(gameService.createGame(gameRequest));
        } catch (Exception e){
            res.status(401);
            return new Gson().toJson(Map.of("message", "Error: User logged in improperly"));
        }


    }
    public Object joinGameHandler(Request req, Response res){
        System.out.println("joinGame called");
        try {
            String authToken = req.headers("authorization");
            if (userAuthService.getAuth(authToken) == null){
                res.status(401);
                return new Gson().toJson(Map.of("message", "Error: User logged in improperly"));
            }
            GameIDJoinRequest gameIDJoinRequest = gson.fromJson(req.body(), GameIDJoinRequest.class);
            if (gameIDJoinRequest.gameID() < 1){
                res.status(400);
                return new Gson().toJson(Map.of("message", "Error: game ID invalid"));
            }
            //success
            //get username
            AuthData returnedAuthData = userAuthService.getAuth(authToken);
            GameJoinRequest gameJoinRequest = new GameJoinRequest(authToken, gameIDJoinRequest.playerColor(), gameIDJoinRequest.gameID(), returnedAuthData.username());
            gameService.joinGame(gameJoinRequest);
            res.status(200);
            return new Gson().toJson(new Object());
        } catch (DataAccessException e){
            res.status(403);
            return new Gson().toJson(Map.of("message", e.getMessage()));
        }
    }
}


