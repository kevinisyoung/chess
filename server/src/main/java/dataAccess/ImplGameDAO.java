package dataAccess;

public class ImplGameDAO implements GameDAO{
    @Override
    public void clearAll() {
        System.out.println("GAME CLEARED");
    }
}
