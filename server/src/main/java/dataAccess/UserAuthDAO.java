package dataAccess;

import Exceptions.DataAccessException;
import model.AuthData;
import model.UserData;

public interface UserAuthDAO {

    public AuthData getAuth(String authToken);
    public void insertAuth(String username, String authToken);
    public void removeAuth(String authToken) throws DataAccessException;
    public void clearAll();

    public UserData getUser(String username);
    public void insertUser(UserData user);
}
