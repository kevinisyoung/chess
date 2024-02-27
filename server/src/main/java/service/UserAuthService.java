package service;

import Exceptions.UserAlreadyExistsException;
import dataAccess.MemoryUserAuthDAO;
import dataAccess.UserAuthDAO;

import model.AuthData;
import model.UserData;

import java.util.UUID;

public class UserAuthService {

    UserAuthDAO DAO;


    public UserAuthService(){
        DAO = new MemoryUserAuthDAO();
    }
    public AuthData register(UserData user) throws UserAlreadyExistsException {
        //verify no username exists in DB
        if (DAO.getUser(user.username()) != null){
            throw new UserAlreadyExistsException("Error: already taken");
        }

        DAO.insertUser(user);
        //have auth dao create auth token and insert that
        return login(user);
    }
    public AuthData login(UserData user) {
        //create new authToken here
        //insert that authToken into DB using AuthDAO
        //create new AuthData using that authtoken
        String generatedAuth = generateAuth();
        DAO.insertAuth(user.username(),generatedAuth);
        return new AuthData(generatedAuth, user.username());
    }
    public void logout(UserData user) {}

    public void clearAll(){
        DAO.clearAll();
    }


    public String generateAuth(){
        return UUID.randomUUID().toString();
    }


}
