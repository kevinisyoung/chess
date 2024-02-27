package service;

import dataAccess.ImplUserDAO;
import dataAccess.UserDAO;
import model.AuthData;
import model.UserData;

public class UserService {

    UserDAO DAO;

    public UserService(){
        DAO = new ImplUserDAO();
    }
    public AuthData register(UserData user) {
        //verify no username exists in DB

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
