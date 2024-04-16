package additionalRecords;

import chess.ChessGame;

public record GameIDJoinRequest(ChessGame.TeamColor playerColor, int gameID) {

}
