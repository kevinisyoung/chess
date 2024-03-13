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

        gameIDIncrementer++;
        GameData gameData = new GameData(gameIDIncrementer, null, null, gameInfo.gameName(), null);
        DAO.createGame(gameData);

        return new GameResponse(gameIDIncrementer);
    }

    public void joinGame(GameJoinRequest gameJoinRequest) throws DataAccessException {
        DAO.updateGame(gameJoinRequest);
    }
}
