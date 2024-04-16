package service;

import Exceptions.DataAccessException;
import additionalRecords.*;
import dataAccess.DatabaseGameDao;
import model.GameData;

public class GameService {
    DatabaseGameDao dao;
    int gameIDIncrementer = 0;
    public GameService() {
        dao = new DatabaseGameDao();
    }
    public void clearAll(){
        dao.clearAll();
    }

    public GamesList listGames(){
        return new GamesList(dao.getGames());
    }



    public GameResponse createGame(GameRequest gameInfo){
        int currentId = dao.getGames().size() + 1;
        GameData gameData = new GameData(currentId, null, null, gameInfo.gameName(), null);
        dao.createGame(gameData);

        return new GameResponse(currentId);
    }

    public void joinGame(GameJoinRequest gameJoinRequest) throws DataAccessException {
        try {
            dao.updateGame(gameJoinRequest);}
        catch (DataAccessException e){
            throw new DataAccessException("error: " + e.getMessage());
        }
    }
}
