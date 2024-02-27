package service;

import dataAccess.GameDAO;
import dataAccess.MemoryGameDAO;

public class GameService {
    GameDAO DAO;
    public GameService(){
        DAO = new MemoryGameDAO();
    }
    public void clearAll(){
        DAO.clearAll();
    }
}
