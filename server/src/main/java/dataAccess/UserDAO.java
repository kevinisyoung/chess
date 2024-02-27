package dataAccess;

import model.UserData;

public interface UserDAO {

    public void clearAll();
    public UserData getUser(String username);
    public void insertUser(UserData user);

}
