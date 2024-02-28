package service;

import additionalRecords.GameRequest;
import additionalRecords.GameResponse;
import dataAccess.GameDAO;
import dataAccess.MemoryGameDAO;
import dataAccess.MemoryUserAuthDAO;
import model.GameData;

public class GameService {
    MemoryGameDAO DAO;
    MemoryUserAuthDAO authDAO;
    int gameIDIncrementer = 0;
    public GameService(){
        DAO = new MemoryGameDAO();
    }
    public void clearAll(){
        DAO.clearAll();
    }



    public GameResponse createGame(GameRequest gameInfo){

        gameIDIncrementer++;
        GameData gameData = new GameData(gameIDIncrementer, null, null, gameInfo.gameName(), null);
        DAO.createGame(gameData);

        return new GameResponse(gameIDIncrementer);
    }
}
