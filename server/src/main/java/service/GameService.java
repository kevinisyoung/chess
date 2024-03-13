package service;

import Exceptions.DataAccessException;
import additionalRecords.*;
import dataAccess.DatabaseGameDAO;
import dataAccess.GameDAO;
import dataAccess.MemoryGameDAO;
import dataAccess.MemoryUserAuthDAO;
import model.GameData;

import java.sql.SQLException;

public class GameService {
    DatabaseGameDAO DAO;
    int gameIDIncrementer = 0;
    public GameService() {
        DAO = new DatabaseGameDAO();
    }
    public void clearAll(){
        DAO.clearAll();
    }

    public GamesList listGames(){
        return new GamesList(DAO.getGames());
    }



    public GameResponse createGame(GameRequest gameInfo){
        int currentId = DAO.getGames().size() + 1;
        GameData gameData = new GameData(currentId, null, null, gameInfo.gameName(), null);
        DAO.createGame(gameData);

        return new GameResponse(currentId);
    }

    public void joinGame(GameJoinRequest gameJoinRequest) throws DataAccessException {
        try {DAO.updateGame(gameJoinRequest);}
        catch (DataAccessException e){
            throw new DataAccessException("error: " + e.getMessage());
        }
    }
}
