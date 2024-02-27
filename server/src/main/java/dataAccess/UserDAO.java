package dataAccess;

public interface UserDAO {

    public void clearAll();
    public void getUser(String username);
    public void insertUser();

}
