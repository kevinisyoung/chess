package dataAccess;

import model.AuthData;
import model.UserData;

public interface UserAuthDAO {

    public void getAuth();
    public void insertAuth(String username, String authToken);
    public void removeAuth(AuthData user);
    public void clearAll();

    public UserData getUser(String username);
    public void insertUser(UserData user);
}
