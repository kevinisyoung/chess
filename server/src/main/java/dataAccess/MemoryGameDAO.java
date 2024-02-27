package dataAccess;

import model.AuthData;

import java.util.HashSet;

public class MemoryGameDAO implements GameDAO{

    HashSet<AuthData> gameDatabase = new HashSet<AuthData>();
    @Override
    public void clearAll() {
        gameDatabase.clear();
        System.out.println("GAME CLEARED");
    }

    @Override
    public void createGame() {
        System.out.println("CREATEGAME");
    }

    @Override
    public void getGame() {
        System.out.println("GETGAME");
    }

    @Override
    public void listGames() {
        System.out.println("LISTGAMES");

    }

    @Override
    public void updateGame() {

    }
}
