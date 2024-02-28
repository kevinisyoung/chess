package model;

import chess.ChessGame;

public record GameData(int gameID, String whiteUsername, String blackUsername, String gameName, ChessGame game) {

    GameData setGameID(int newID){
        return new GameData(newID, whiteUsername, blackUsername, gameName, game);
    }

    GameData setWhiteUsername(String newWhiteUsername){
        return new GameData(gameID, newWhiteUsername, blackUsername, gameName, game);
    }

    GameData setBlackUsername(String newBlackUsername){
        return new GameData(gameID, whiteUsername, newBlackUsername, gameName, game);
    }

    GameData setGameName(String newGameName){
        return new GameData(gameID, whiteUsername, blackUsername, newGameName, game);
    }

    GameData setGame(ChessGame newGame){
        return new GameData(gameID, whiteUsername, blackUsername, gameName, newGame);
    }
}
