package service;

import Exceptions.DataAccessException;
import Exceptions.UserAlreadyExistsException;
import additionalRecords.LoginData;
import dataAccess.DatabaseUserAuthDAO;

import model.AuthData;
import model.UserData;

import java.util.UUID;

public class UserAuthService {

    DatabaseUserAuthDAO Dao;


    public UserAuthService(){
        Dao = new DatabaseUserAuthDAO();
    }
    public AuthData register(UserData user) throws UserAlreadyExistsException, DataAccessException {

        //verify no username exists in DB
        if (Dao.getUser(user.username()).username() != null){
            throw new UserAlreadyExistsException("Error: already taken");
        }

        Dao.insertUser(user);
        //have auth dao create auth token and insert that
        return login(new LoginData(user.username(), user.password()));
    }
    public AuthData login(LoginData user) throws DataAccessException {
        //verify user exists
        if (Dao.getUser(user.username()) == null || Dao.getUser(user.username()).username() == null){
            throw new DataAccessException("error: Unauthorized");
        }
        var testLoginResult = Dao.getUser(user.username());
        if (!Dao.getUser(user.username()).password().equals(user.password())){
            throw new DataAccessException("error: Wrong password");
        }

        //create new authToken here
        //insert that authToken into DB using AuthDAO
        //create new AuthData using that authtoken

        String generatedAuth = generateAuth();
        Dao.insertAuth(user.username(),generatedAuth);
        return new AuthData(generatedAuth, user.username());
    }
    public void logout(String authToken) throws DataAccessException {
        Dao.removeAuth(authToken);
    }

    public void clearAll(){
        Dao.clearAll();
    }


    public String generateAuth(){
        return UUID.randomUUID().toString();
    }

    public AuthData getAuth(String authToken) throws DataAccessException {
        var result = Dao.getAuth(authToken);
        return result;
    }


}
