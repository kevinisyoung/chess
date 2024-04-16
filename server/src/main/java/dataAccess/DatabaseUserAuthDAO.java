package dataAccess;

import Exceptions.DataAccessException;
import model.AuthData;
import model.UserData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUserAuthDAO implements UserAuthDAO{

    public DatabaseUserAuthDAO() {
        try {
            //makeSQLCalls();
            configureUserDatabase();
            configureAuthDatabase();
            System.out.println("CONNECTION TO DB ESTABLISHED.");
        } catch (SQLException | DataAccessException e) {
            e.printStackTrace();
        }
    }

    public void configureUserDatabase() throws SQLException, DataAccessException {
        DatabaseManager.createDatabase();

        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog(DatabaseManager.getConnection().getCatalog());

            var createTable = """
                    CREATE TABLE IF NOT EXISTS user (
            username VARCHAR(255) NOT NULL,
            password VARCHAR(255) NOT NULL,
            email VARCHAR(255) NOT NULL,
            PRIMARY KEY (username)
            )""";

            try (var createTableStatement = conn.prepareStatement(createTable)) {
                createTableStatement.executeUpdate();
            }
        }
    }

    public void configureAuthDatabase() throws SQLException, DataAccessException {
        DatabaseManager.createDatabase();

        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog(DatabaseManager.getConnection().getCatalog());

            var createTable = """
                    CREATE TABLE IF NOT EXISTS auth (
            username VARCHAR(255) NOT NULL,
            authToken VARCHAR(255) NOT NULL,
            PRIMARY KEY (authToken)
            )""";

            try (var createTableStatement = conn.prepareStatement(createTable)) {
                createTableStatement.executeUpdate();
            }
        }
    }


    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        String usernameRecieved = null;
        String authTokenRecieved = null;

        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog(DatabaseManager.getConnection().getCatalog());
            try (var preparedStatement = conn.prepareStatement("SELECT * FROM auth WHERE authToken =?;")) {
                preparedStatement.setString(1, authToken);
                try (var rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        usernameRecieved = rs.getString("username");
                        authTokenRecieved = rs.getString("authToken");
                    }
                }
            }
        } catch (SQLException | DataAccessException e) {
            throw new DataAccessException();
        }
        if (authTokenRecieved == null || usernameRecieved == null){
            return null;
        }
        return new AuthData(authTokenRecieved,usernameRecieved);
    }

    @Override
    public void insertAuth(String username, String authToken) {
        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog(DatabaseManager.getConnection().getCatalog());
            try (var preparedStatement = conn.prepareStatement("INSERT INTO auth (username,authToken) VALUES(?,?);")) {
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,authToken);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeAuth(String authToken) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            if (getAuth(authToken) == null){
                throw new DataAccessException("Error: There was no auth found there");
            }
            conn.setCatalog(DatabaseManager.getConnection().getCatalog());
            try (var preparedStatement = conn.prepareStatement("DELETE FROM auth WHERE authToken=?;")) {
                preparedStatement.setString(1,authToken);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | DataAccessException e) {
            throw new DataAccessException();
        }
    }

    @Override
    public void clearAll() {
        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog(DatabaseManager.getConnection().getCatalog());
            try (var preparedStatement = conn.prepareStatement("truncate table auth")) {
                preparedStatement.executeUpdate();
            }
            try (var preparedStatement = conn.prepareStatement("truncate table user")) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserData getUser(String username) {
        String usernameRecieved = null;
        String passwordRecieved = null;
        String emailRecieved = null;

        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog(DatabaseManager.getConnection().getCatalog());
            try (var preparedStatement = conn.prepareStatement("SELECT * FROM user WHERE username =?;")) {
                preparedStatement.setString(1, username);
                try (var rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        usernameRecieved = rs.getString("username");
                        passwordRecieved = rs.getString("password");
                        emailRecieved = rs.getString("email");
                    }
                }
            }
        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }

        return new UserData(usernameRecieved, passwordRecieved, emailRecieved);
    }

    @Override
    public void insertUser(UserData user) {
        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog(DatabaseManager.getConnection().getCatalog());
            try (var preparedStatement = conn.prepareStatement("INSERT INTO user (username,password,email) VALUES(?,?,?);")) {
                preparedStatement.setString(1,user.username());
                preparedStatement.setString(2,user.password());
                preparedStatement.setString(3,user.email());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
