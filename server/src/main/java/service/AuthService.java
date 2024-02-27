package service;

import dataAccess.AuthDAO;
import dataAccess.MemoryAuthDAO;

public class AuthService {
    AuthDAO DAO;
    public AuthService(){
         DAO = new MemoryAuthDAO();
    }

    public String generateAuth(){
        return "AUTH HERE";
    }

    public void clearAll(){
        DAO.clearAll();
    }

//    public void create
}
