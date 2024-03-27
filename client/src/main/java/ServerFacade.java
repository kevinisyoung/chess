import Exceptions.DataAccessException;
import additionalRecords.*;
import com.google.gson.Gson;
import model.UserData;

import java.io.*;
import java.net.*;


public class ServerFacade {

    private final String serverUrl;

    public ServerFacade(String url) {
        serverUrl = url;
    }


//    public Game getGame(Game game) throws ResponseException {
//        var path = "/pet";
//        return this.makeRequest("POST", path, pet, Pet.class);
//    }

    public void logout() throws ResponseException {
        var path = "/session";
        this.makeRequest("DELETE", path, null, null);
    }

    public void register(String username, String password, String email) {
        UserData registerData = new UserData(username,password,email);
        var path = "/user";
        try{
            this.makeRequest("POST", path, registerData, AuthResponse.class );
        } catch (ResponseException e){
            e.printStackTrace();
        }
    }

    public void login(String username, String password) {
        var loginData = new LoginData(username,password);
        var path = "/user";
        try{
            this.makeRequest("POST", path, loginData, AuthResponse.class );
        } catch (ResponseException e){
            System.out.println("Sorry, your username/password combination were incorrect. Try again.");
            e.printStackTrace();
        }
    }


    public void deleteEverything() throws ResponseException {
        var path = "/db";
        this.makeRequest("DELETE", path, null, null);
    }

//    public Pet[] listPets() throws ResponseException {
//        var path = "/pet";
//        record listPetResponse(Pet[] pet) {
//        }
//        var response = this.makeRequest("GET", path, null, listPetResponse.class);
//        return response.pet();
//    }

    private <T> T makeRequest(String method, String path, Object request, Class<T> responseClass) throws ResponseException {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);

            writeBody(request, http);
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
}