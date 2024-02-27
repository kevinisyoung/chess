package service;

import Exceptions.UserAlreadyExistsException;
import dataAccess.MemoryUserDAO;
import dataAccess.UserDAO;
import model.AuthData;
import model.UserData;
import org.eclipse.jetty.server.Authentication;
import server.ServerHandlers;

public class UserService {

    UserDAO DAO;

    public UserService(){
        DAO = new MemoryUserDAO();
    }
    public AuthData register(UserData user) throws UserAlreadyExistsException {
        //verify no username exists in DB
        if (DAO.getUser(user.username()) != null){
            throw new UserAlreadyExistsException("Error: already taken");
        }

        DAO.insertUser(user);


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

}
