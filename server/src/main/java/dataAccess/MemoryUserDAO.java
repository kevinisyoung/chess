package dataAccess;

public class MemoryUserDAO implements UserDAO{
    @Override
    public void clearAll() {
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
