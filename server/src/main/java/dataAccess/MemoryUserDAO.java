package dataAccess;

import model.AuthData;

import java.util.HashSet;

public class MemoryUserDAO implements UserDAO{

    HashSet<AuthData> userDatabase = new HashSet<AuthData>();
    @Override
    public void clearAll() {
        userDatabase.clear();
        System.out.println("USER CLEARED");
    }

    @Override
    public void getUser(String username) {
        System.out.println("GETUSER");
    }

    @Override
    public void insertUser() {
        System.out.println("INSERTUSER");
    }
}
