package dataAccess;

import Exceptions.DataAccessException;
import additionalRecords.AuthToken;
import model.AuthData;
import model.UserData;

import java.util.HashSet;

public class MemoryUserAuthDAO implements UserAuthDAO{

    HashSet<AuthData> authDatabase = new HashSet<AuthData>();
    HashSet<UserData> userDatabase = new HashSet<UserData>();


    @Override
    public void insertUser(UserData user) {
        userDatabase.add(user);
    }

    public AuthData getAuth(String authToken) {
        for (AuthData auth : authDatabase){
            if (authToken.equals(auth.authToken())){
                return auth;
            }
        }
        return null;
    }


    @Override
    public void insertAuth(String username, String authToken) {
        authDatabase.add(new AuthData(authToken,username));
    }

    @Override
    public void removeAuth(String authToken) throws DataAccessException {
        System.out.println("REMOVEAUTH");
        boolean removed = false;
        for (AuthData auth : authDatabase){
            if (authToken.equals(auth.authToken())){
                authDatabase.remove(auth);
                removed = true;
                break;
            }
        }
        if (!removed){
            throw new DataAccessException();
        }
    }

    @Override
    public void clearAll() {
        authDatabase.clear();
        userDatabase.clear();
        System.out.println("USERAUTH CLEARED");

    }

    @Override
    public UserData getUser(String username) {
        for (UserData user : userDatabase){
            if (user.username().equals(username)){
                return user;
            }
        }
        return null;
    }

    public HashSet<AuthData> getAuthDatabase() {
        return authDatabase;
    }

    public HashSet<UserData> getUserDatabase() {
        return userDatabase;
    }
}
