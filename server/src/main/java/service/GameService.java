package service;

import dataAccess.GameDAO;
import dataAccess.ImplGameDAO;
import dataAccess.ImplUserDAO;
import dataAccess.UserDAO;

public class GameService {
    GameDAO DAO;
    public GameService(){
        DAO = new ImplGameDAO();
    }
    public void clearAll(){
        DAO.clearAll();
    }
}
