package model;

import chess.ChessGame;

public record GameData(int gameID, String whiteUsername, String blackUsername, String gameName, ChessGame game) {

    public GameData setGameID(int newID){
        return new GameData(newID, whiteUsername, blackUsername, gameName, game);
    }

    public GameData setWhiteUsername(String newWhiteUsername){
        return new GameData(gameID, newWhiteUsername, blackUsername, gameName, game);
    }

    public GameData setBlackUsername(String newBlackUsername){
        return new GameData(gameID, whiteUsername, newBlackUsername, gameName, game);
    }

    public GameData setGameName(String newGameName){
        return new GameData(gameID, whiteUsername, blackUsername, newGameName, game);
    }

    public GameData setGame(ChessGame newGame){
        return new GameData(gameID, whiteUsername, blackUsername, gameName, newGame);
    }
}
