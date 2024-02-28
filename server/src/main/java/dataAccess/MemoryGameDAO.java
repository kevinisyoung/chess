package dataAccess;

import Exceptions.DataAccessException;
import additionalRecords.GameJoinRequest;
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
    public void updateGame(GameJoinRequest gameJoinRequest) throws DataAccessException {
        for (GameData game : new HashSet<>(gameDatabase)) {
            if (game.gameID() == gameJoinRequest.gameID()) {
                GameData updatedGame;
                if ((gameJoinRequest.playerColor() != null && gameJoinRequest.playerColor().equals("WHITE") && game.whiteUsername() != null) || (gameJoinRequest.playerColor() != null && gameJoinRequest.playerColor().equals("BLACK") && game.blackUsername() != null)) {
                    throw new DataAccessException("Error: Color for that game already exists");
                }
                if (gameJoinRequest.playerColor() != null && gameJoinRequest.playerColor().equals("WHITE")) {
                    updatedGame = game.setWhiteUsername(gameJoinRequest.playerName());
                } else if (gameJoinRequest.playerColor() != null && gameJoinRequest.playerColor().equals("BLACK")) {
                    updatedGame = game.setBlackUsername(gameJoinRequest.playerName());
                } else {
                    continue;
                }
                gameDatabase.remove(game);
                gameDatabase.add(updatedGame);
            }
        }


    }

    @Override
    public HashSet<GameData> getGames() {
        return gameDatabase;
    }
}
