package service;

import Exceptions.DataAccessException;
import dataAccess.DatabaseManager;
import dataAccess.DatabaseUserAuthDAO;
import model.AuthData;
import model.UserData;
import org.eclipse.jetty.server.Authentication;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseUserAuthDAOTest {

    @Test
    void getAuth() {
        DatabaseUserAuthDAO testDAO = new DatabaseUserAuthDAO();

        var testUsername = UUID.randomUUID().toString();
        var testAuthToken = UUID.randomUUID().toString();

        testDAO.insertAuth(testUsername,testAuthToken);

        AuthData tempAuthData = new AuthData(testAuthToken,testUsername);
        assertEquals(tempAuthData,testDAO.getAuth(testAuthToken));

    }

    @Test
    void insertAndDeleteAuthTest() {
        DatabaseUserAuthDAO testDAO = new DatabaseUserAuthDAO();

        var testUsername = UUID.randomUUID().toString();
        var testAuthToken = UUID.randomUUID().toString();

        testDAO.insertAuth(testUsername,testAuthToken);

        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog("chess");

            try (var preparedStatement = conn.prepareStatement("SELECT * FROM auth WHERE authToken =?;")) {
                preparedStatement.setString(1,testAuthToken);
                try (var rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        var usernameReceived = rs.getString("username");
                        var authTokenRecieved = rs.getString("authToken");

                        assertEquals(testUsername, usernameReceived);
                        assertEquals(testAuthToken, authTokenRecieved);
                        //delete the auth data. it was found.
                        testDAO.removeAuth(testAuthToken);
                    }
                }
            }
        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void clearAll() {
        DatabaseUserAuthDAO testDAO = new DatabaseUserAuthDAO();

        var testUsername = UUID.randomUUID().toString();
        var testAuthToken = UUID.randomUUID().toString();

        testDAO.insertAuth(testUsername,testAuthToken);

        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog("chess");

            testDAO.configureUserDatabase();
            testDAO.configureAuthDatabase();

            var testUsername1 = UUID.randomUUID().toString();
            var testAuthToken1 = UUID.randomUUID().toString();
            var testUsername2 = UUID.randomUUID().toString();
            var testAuthToken2 = UUID.randomUUID().toString();
            var testUsername3 = UUID.randomUUID().toString();
            var testAuthToken3 = UUID.randomUUID().toString();


            try (var preparedStatement = conn.prepareStatement("SELECT * FROM auth WHERE authToken =?;")) {
                preparedStatement.setString(1,testAuthToken);
                try (var rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        var usernameReceived = rs.getString("username");
                        var authTokenReceived = rs.getString("authToken");

                        assertEquals(testUsername, usernameReceived);
                        assertEquals(testAuthToken, authTokenReceived);
                        //delete the auth data. it was found.
                        testDAO.removeAuth(testAuthToken);
                    }
                }
            }
        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getUserTest() {
        DatabaseUserAuthDAO testDAO = new DatabaseUserAuthDAO();

        var testUsername = UUID.randomUUID().toString();
        var testPassword = UUID.randomUUID().toString();
        var testEmail = UUID.randomUUID().toString();

        UserData testUserData = new UserData(testUsername,testPassword,testEmail);
        testDAO.insertUser(testUserData);

        assertEquals(testUserData,testDAO.getUser(testUserData.username()));
    }

    @Test
    void insertUserTest() {
        DatabaseUserAuthDAO testDAO = new DatabaseUserAuthDAO();

        var testUsername = UUID.randomUUID().toString();
        var testPassword = UUID.randomUUID().toString();
        var testEmail = UUID.randomUUID().toString();
        //execute the function then check if it was done properly
        testDAO.insertUser(new UserData(testUsername,testPassword,testEmail));

        try (var conn = DatabaseManager.getConnection()) {
            conn.setCatalog("chess");

            try (var preparedStatement = conn.prepareStatement("SELECT * FROM auth WHERE username =?;")) {
                preparedStatement.setString(1,testUsername);
                try (var rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        var usernameReceived = rs.getString("username");
                        var emailRecieved = rs.getString("email");

                        assertEquals(testUsername, usernameReceived);
                        assertEquals(testEmail, emailRecieved);
                    }
                }
            }
        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}