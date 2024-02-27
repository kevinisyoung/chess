package dataAccess;

import model.AuthData;
import model.UserData;

import java.util.HashSet;

public class MemoryUserAuthDAO implements UserAuthDAO{

    HashSet<AuthData> authDatabase = new HashSet<AuthData>();

    @Override
    public void getAuth() {
        System.out.println("GET AUTHDAO");
    }

    @Override
    public void insertAuth(String auth) {
        System.out.println("INSERTAUTH");
    }

    @Override
    public void removeAuth() {
        System.out.println("REMOVEAUTH");
    }

    @Override
    public void clearAll() {
        authDatabase.clear();
        System.out.println("AUTH CLEARED");
    }

    @Override
    public UserData getUser(String username) {
        return null;
    }

    @Override
    public void insertUser(UserData user) {

    }

}
