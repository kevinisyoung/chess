package additionalRecords;

import model.GameData;

public record GamesList(java.util.HashSet<GameData> games) {
}
