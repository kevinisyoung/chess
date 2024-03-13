package dataAccess;

import Exceptions.DataAccessException;
import additionalRecords.GameJoinRequest;
import chess.ChessGame;
import com.google.gson.Gson;
import model.GameData;
import model.UserData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;

public class DatabaseGameDAO implements GameDAO{

    public DatabaseGameDAO() {
        try{
            configureGameDatabase();
        } catch (SQLException | DataAccessException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            configureGameDatabase();
            System.out.println("CONNECTION TO DB ESTABLISHED.");
        } catch (SQLException | DataAccessException e) {
            e.printStackTrace();
        }
    }

    public static void makeSQLCalls() throws SQLException, DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            // Execute SQL statements on the connection here
        }
    }

    static void configureGameDatabase() throws SQLException, DataAccessException {
        DatabaseManager.createDatabase();

        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog("chess");

            var createGameTable = """
                    CREATE TABLE  IF NOT EXISTS game (
            id INT NOT NULL AUTO_INCREMENT,
            gameName VARCHAR(255) NOT NULL,
            whiteUsername VARCHAR(255) NULL,
            blackUsername VARCHAR(255) NULL,
            game TEXT NULL,
            PRIMARY KEY (id)
            )""";


            try (var createTableStatement = conn.prepareStatement(createGameTable)) {
                createTableStatement.executeUpdate();
            }
        }
    }



    @Override
    public void clearAll() {
        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("truncate table game")) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void createGame(GameData gameData) {
        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("INSERT INTO game (gameName, game) VALUES(?, ?)")) {
                preparedStatement.setString(1, gameData.gameName());

                // Serialize and store the friend JSON.
                ChessGame tempGame = new ChessGame();
                var json = new Gson().toJson(tempGame);
                preparedStatement.setString(2, json);

                preparedStatement.executeUpdate();
            }

        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameData getGame(int id) {
        int idRecieved = -1;
        String gameNameRecieved = null;
        String whiteUsernameRecieved = null;
        String blackUsernameRecieved = null;
        ChessGame gameRecieved = null;

        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("SELECT * FROM game WHERE id =?;")) {
                preparedStatement.setInt(1, id);
                try (var rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        idRecieved = rs.getInt("id");
                        gameNameRecieved = rs.getString("gameName");
                        whiteUsernameRecieved = rs.getString("whiteUsername");
                        blackUsernameRecieved = rs.getString("blackUsername");
                        String gameJSON = rs.getString("game");
                        Gson gson = new Gson();
                        gameRecieved = gson.fromJson(gameJSON,ChessGame.class);
                    }
                }
            }
        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }
        return new GameData(idRecieved,whiteUsernameRecieved,blackUsernameRecieved,gameNameRecieved,gameRecieved);
    }

    @Override
    public void updateGame(GameJoinRequest gameJoinRequest) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog("chess");
            String columnName = null;

            // Determine which column to update
            if (gameJoinRequest.playerColor() == ChessGame.TeamColor.BLACK) {
                columnName = "blackUsername";
            } else if (gameJoinRequest.playerColor() == ChessGame.TeamColor.WHITE) {
                columnName = "whiteUsername";
            }

            if (columnName != null) {
                // Construct the SQL statement with the column name
                String sql = "UPDATE game SET " + columnName + " = ? WHERE id = ?";

                try (var preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, gameJoinRequest.playerName());
                    preparedStatement.setInt(2, gameJoinRequest.gameID());

                    preparedStatement.executeUpdate();
                }
            }

        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashSet<GameData> getGames() {
        int idRecieved = -1;
        String gameNameRecieved = null;
        String whiteUsernameRecieved = null;
        String blackUsernameRecieved = null;
        ChessGame gameRecieved = null;

        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog("chess");
            try (var preparedStatement = conn.prepareStatement("SELECT * FROM game WHERE id =?;")) {
                preparedStatement.setInt(1, id);
                try (var rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        idRecieved = rs.getInt("id");
                        gameNameRecieved = rs.getString("gameName");
                        whiteUsernameRecieved = rs.getString("whiteUsername");
                        blackUsernameRecieved = rs.getString("blackUsername");
                        String gameJSON = rs.getString("game");
                        Gson gson = new Gson();
                        gameRecieved = gson.fromJson(gameJSON,ChessGame.class);
                    }
                }
            }
        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }
        return new GameData(idRecieved,whiteUsernameRecieved,blackUsernameRecieved,gameNameRecieved,gameRecieved);
    }

}
