package additionalRecords;

public record GameJoinRequest(String autToken, chess.ChessGame.TeamColor playerColor, int gameID, String playerName) {
}
