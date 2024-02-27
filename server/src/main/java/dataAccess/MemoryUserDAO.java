package dataAccess;

import model.AuthData;
import model.UserData;

import java.util.HashSet;

public class MemoryUserDAO implements UserDAO{

    HashSet<AuthData> userDatabase = new HashSet<AuthData>();
    @Override
    public void clearAll() {
        userDatabase.clear();
        System.out.println("USER CLEARED");
    }

    @Override
    public UserData getUser(String username) {
        System.out.println("GETUSER");
        return null;
    }

    @Override
    public void insertUser(UserData user) {
        System.out.println("INSERTUSER");
    }
}
