package dataAccess;

import model.AuthData;

import java.util.HashSet;

public class MemoryAuthDAO implements AuthDAO{

    HashSet<AuthData> authDatabase = new HashSet<AuthData>();

    @Override
    public void getAuth() {
        System.out.println("GET AUTHDAO");
    }

    @Override
    public void insertAuth() {
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

}
