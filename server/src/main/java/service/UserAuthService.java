package service;

import Exceptions.UserAlreadyExistsException;
import dataAccess.MemoryUserAuthDAO;
import dataAccess.UserAuthDAO;

import model.AuthData;
import model.UserData;

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


        AuthData responseData = new AuthData("AUTHTOKEN_HERE",user.username());

        return responseData;
    }
    public AuthData login(UserData user) {
        //create new authToken here
        //insert that authToken into DB using AuthDAO
        //create new AuthData using that authtoken

        AuthData responseData = new AuthData("AUTHTOKEN_HERE",user.username());
        return null;
    }
    public void logout(UserData user) {}

    public void clearAll(){
        DAO.clearAll();
    }


    public String generateAuth(){
        return "AUTH HERE";
    }


}
