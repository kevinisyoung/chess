package service;

import dataAccess.AuthDAO;
import dataAccess.ImplAuthDAO;
import dataAccess.ImplUserDAO;
import dataAccess.UserDAO;

public class AuthService {
    AuthDAO DAO;
    public AuthService(){
         DAO = new ImplAuthDAO();
    }

    public String generateAuth(){
        return "AUTH HERE";
    }

    public void clearAll(){
        DAO.clearAll();
    }
}
