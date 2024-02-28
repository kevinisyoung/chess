package dataAccess;

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

    @Override
    public void getAuth() {
        System.out.println("GET AUTHDAO");
    }

    @Override
    public void insertAuth(String username, String authToken) {
        authDatabase.add(new AuthData(authToken,username));
    }

    @Override
    public void removeAuth(AuthData user) {
        System.out.println("REMOVEAUTH");
    }

    @Override
    public void clearAll() {
        authDatabase.clear();
        System.out.println("AUTH CLEARED");
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

}
