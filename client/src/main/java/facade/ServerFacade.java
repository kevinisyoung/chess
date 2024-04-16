package facade;

import additionalRecords.*;
import chess.ChessGame;
import com.google.gson.Gson;
import model.GameData;
import model.UserData;


import java.io.*;
import java.net.*;
import java.util.HashSet;


public class ServerFacade {

    private final String serverUrl;

    private String userAuthStored = "";
    private String usernameStored = "";

    public ServerFacade(String url) {
        serverUrl = url;
    }

    public void register(String username, String password, String email) throws ResponseException {
        UserData registerData = new UserData(username,password,email);
        var path = "/user";
        var response = this.makeRequest("POST", path, registerData, AuthResponse.class );
        setUserAuthStored(response.authToken());
        setUsernameStored(response.username());
    }

    public void login(String username, String password) throws ResponseException {
        var loginData = new LoginData(username,password);
        var path = "/session";
        var response = this.makeRequest("POST", path, loginData, AuthResponse.class );
        setUserAuthStored(response.authToken());
        setUsernameStored(response.username());
    }

    public void logout() throws ResponseException {
        var path = "/session";
        var authTokenRecord = new AuthToken(userAuthStored);
        this.makeRequest("DELETE", path, authTokenRecord, authTokenRecord.getClass());
        usernameStored = "";
        userAuthStored = "";
    }

    public int createGame(String gameName) throws ResponseException {
        var path = "/game";
            var gameRequest = new GameRequest(userAuthStored,gameName);
            var gameAnswer = this.makeRequest("POST", path, gameRequest, GameResponse.class);
            return gameAnswer.gameID();
    }
    public HashSet<GameData> listGames() throws ResponseException {
        var path = "/game";

        var authTokenRecord = new AuthToken(userAuthStored);
        var response = this.makeRequest("GET", path, authTokenRecord, GamesList.class);
        return response.games();
    }
    public void joinGame(int gameID, ChessGame.TeamColor teamColor) throws ResponseException {
        var path = "/game";
        try{
            var gameJoinRequest = new GameJoinRequest(userAuthStored,teamColor,gameID,usernameStored);
            this.makeRequest("PUT", path, gameJoinRequest, GameResponse.class);
        } catch (ResponseException e){
            e.printStackTrace();
        }
    }

    private <T> T makeRequest(String method, String path, Object request, Class<T> responseClass) throws ResponseException {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);

            if (request.getClass() == AuthToken.class){
                String authRecord = new Gson().toJson(((AuthToken) request).authToken());
                if (authRecord.charAt(0) == '\"'){
                    authRecord = authRecord.substring(1);
                }
                if (authRecord.charAt(authRecord.length()-1) == '\"'){
                    authRecord = authRecord.substring(0,authRecord.length()-1);
                }
                http.setRequestProperty("authorization", authRecord);
            }
            else if (request.getClass() == GameJoinRequest.class){
                http.setRequestProperty("authorization", userAuthStored);
            }
            else if (request.getClass() == GameRequest.class){
                http.setRequestProperty("authorization", userAuthStored);
            }
            if (!method.equals("GET")) {
                http.setDoOutput(true);
                writeBody(request, http);
            }
            http.connect();
            throwIfNotSuccessful(http);
            return readBody(http, responseClass);
        } catch (Exception ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }



    private static void writeBody(Object request, HttpURLConnection http) throws IOException {
        if (request != null) {
            http.addRequestProperty("Content-Type", "application/json");
            String reqData = new Gson().toJson(request);
            try (OutputStream reqBody = http.getOutputStream()) {
                reqBody.write(reqData.getBytes());
            }
        }
    }

    private void throwIfNotSuccessful(HttpURLConnection http) throws IOException, ResponseException {
        var status = http.getResponseCode();
        if (!isSuccessful(status)) {
            throw new ResponseException(status, "failure: " + status);
        }
    }

    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException {
        T response = null;
        if (http.getContentLength() < 0) {
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass != null) {
                    response = new Gson().fromJson(reader, responseClass);
                }
            }
        }
        return response;
    }


    private boolean isSuccessful(int status) {
        return status / 100 == 2;
    }

    private void setUserAuthStored(String auth){
        userAuthStored = auth;
        if (userAuthStored.charAt(0) == '\"'){
            userAuthStored = userAuthStored.substring(1);
        }
        if (userAuthStored.charAt(userAuthStored.length()-1) == '\"'){
            userAuthStored = userAuthStored.substring(0,userAuthStored.length()-2);
        }
    }
    private void setUsernameStored(String username){
        usernameStored = username;
        if (usernameStored.charAt(0) == '\"'){
            usernameStored = usernameStored.substring(1);
        }
        if (usernameStored.charAt(usernameStored.length()-1) == '\"'){
            usernameStored = usernameStored.substring(0,usernameStored.length()-2);
        }
    }
}