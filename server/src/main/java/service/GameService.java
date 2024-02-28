package service;

import Exceptions.DataAccessException;
import additionalRecords.*;
import dataAccess.GameDAO;
import dataAccess.MemoryGameDAO;
import dataAccess.MemoryUserAuthDAO;
import model.GameData;

public class GameService {
    MemoryGameDAO DAO;
    int gameIDIncrementer = 0;
    public GameService(){
        DAO = new MemoryGameDAO();
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
