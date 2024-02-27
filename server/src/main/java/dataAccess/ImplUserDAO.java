package dataAccess;

public class ImplUserDAO implements UserDAO{
    @Override
    public void clearAll() {
        System.out.println("USER CLEARED");
    }
}
