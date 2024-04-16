package service;

import Exceptions.DataAccessException;
import Exceptions.UserAlreadyExistsException;
import additionalRecords.LoginData;
import dataAccess.DatabaseUserAuthDAO;

import model.AuthData;
import model.UserData;

import java.util.UUID;

public class UserAuthService {

    DatabaseUserAuthDAO dao;


    public UserAuthService(){
        dao = new DatabaseUserAuthDAO();
    }
    public AuthData register(UserData user) throws UserAlreadyExistsException, DataAccessException {

        //verify no username exists in DB
        if (dao.getUser(user.username()).username() != null){
            throw new UserAlreadyExistsException("Error: already taken");
        }

        dao.insertUser(user);
        //have auth dao create auth token and insert that
        return login(new LoginData(user.username(), user.password()));
    }
    public AuthData login(LoginData user) throws DataAccessException {
        //verify user exists
        if (dao.getUser(user.username()) == null || dao.getUser(user.username()).username() == null){
            throw new DataAccessException("error: Unauthorized");
        }
        var testLoginResult = dao.getUser(user.username());
        if (!dao.getUser(user.username()).password().equals(user.password())){
            throw new DataAccessException("error: Wrong password");
        }

        //create new authToken here
        //insert that authToken into DB using AuthDAO
        //create new AuthData using that authtoken

        String generatedAuth = generateAuth();
        dao.insertAuth(user.username(),generatedAuth);
        return new AuthData(generatedAuth, user.username());
    }
    public void logout(String authToken) throws DataAccessException {
        dao.removeAuth(authToken);
    }

    public void clearAll(){
        dao.clearAll();
    }


    public String generateAuth(){
        return UUID.randomUUID().toString();
    }

    public AuthData getAuth(String authToken) throws DataAccessException {
        var result = dao.getAuth(authToken);
        return result;
    }


}
