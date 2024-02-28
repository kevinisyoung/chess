package additionalRecords;

public record GameJoinRequest(String autToken, String playerColor, int gameID, String playerName) {
}
