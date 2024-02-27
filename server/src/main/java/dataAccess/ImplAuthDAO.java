package dataAccess;

public class ImplAuthDAO implements AuthDAO{

    @Override
    public void clearAll() {
        System.out.println("AUTH CLEARED");
    }
}
