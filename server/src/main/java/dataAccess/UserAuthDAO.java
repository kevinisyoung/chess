package dataAccess;

import model.UserData;

public interface UserAuthDAO {

    public void getAuth();
    public void insertAuth(String username);
    public void removeAuth();
    public void clearAll();

    public UserData getUser(String username);
    public void insertUser(UserData user);
}
