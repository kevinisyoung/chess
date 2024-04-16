package service;

import Exceptions.DataAccessException;
import additionalRecords.*;
import dataAccess.DatabaseGameDao;
import model.GameData;

public class GameService {
    DatabaseGameDao Dao;
    int gameIDIncrementer = 0;
    public GameService() {
        Dao = new DatabaseGameDao();
    }
    public void clearAll(){
        Dao.clearAll();
    }

    public GamesList listGames(){
        return new GamesList(Dao.getGames());
    }



    public GameResponse createGame(GameRequest gameInfo){
        int currentId = Dao.getGames().size() + 1;
        GameData gameData = new GameData(currentId, null, null, gameInfo.gameName(), null);
        Dao.createGame(gameData);

        return new GameResponse(currentId);
    }

    public void joinGame(GameJoinRequest gameJoinRequest) throws DataAccessException {
        try {
            Dao.updateGame(gameJoinRequest);}
        catch (DataAccessException e){
            throw new DataAccessException("error: " + e.getMessage());
        }
    }
}
