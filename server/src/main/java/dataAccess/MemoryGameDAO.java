package dataAccess;

import additionalRecords.GamesList;
import model.AuthData;
import model.GameData;

import java.util.HashSet;

public class MemoryGameDAO implements GameDAO{

    HashSet<GameData> gameDatabase = new HashSet<GameData>();
    @Override
    public void clearAll() {
        gameDatabase.clear();
        System.out.println("GAME CLEARED");
    }

    @Override
    public void createGame(GameData gameData) {
        System.out.println("CREATEGAME");
        gameDatabase.add(gameData);
    }

    @Override
    public void getGame() {
        System.out.println("GETGAME");
    }


    @Override
    public void updateGame() {

    }

    @Override
    public HashSet<GameData> getGames() {
        return gameDatabase;
    }
}
