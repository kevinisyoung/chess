package service;

import Exceptions.DataAccessException;
import Exceptions.UserAlreadyExistsException;
import additionalRecords.AuthToken;
import additionalRecords.LoginData;
import dataAccess.DatabaseUserAuthDAO;
import dataAccess.MemoryUserAuthDAO;
import dataAccess.UserAuthDAO;

import model.AuthData;
import model.UserData;

import java.util.UUID;

public class UserAuthService {

    DatabaseUserAuthDAO DAO;


    public UserAuthService(){
        DAO = new DatabaseUserAuthDAO();
    }
    public AuthData register(UserData user) throws UserAlreadyExistsException, DataAccessException {

        //verify no username exists in DB
        if (DAO.getUser(user.username()) != null){
            throw new UserAlreadyExistsException("Error: already taken");
        }

        DAO.insertUser(user);
        //have auth dao create auth token and insert that
        return login(new LoginData(user.username(), user.password()));
    }
    public AuthData login(LoginData user) throws DataAccessException {
        //verify user exists
        if (DAO.getUser(user.username()) == null){
            throw new DataAccessException("error: Unauthorized");
        }

        if (!DAO.getUser(user.username()).password().equals(user.password())){
            throw new DataAccessException("error: Wrong password");
        }

        //create new authToken here
        //insert that authToken into DB using AuthDAO
        //create new AuthData using that authtoken

        String generatedAuth = generateAuth();
        DAO.insertAuth(user.username(),generatedAuth);
        return new AuthData(generatedAuth, user.username());
    }
    public void logout(String authToken) throws DataAccessException {
        if (DAO.getAuth(authToken) == null){
            throw new DataAccessException("Error: user not found");
        }
        DAO.removeAuth(authToken);
    }

    public void clearAll(){
        DAO.clearAll();
    }


    public String generateAuth(){
        return UUID.randomUUID().toString();
    }

    public AuthData getAuth(String authToken){
        return DAO.getAuth(authToken);
    }


}
