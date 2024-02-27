package dataAccess;

public class MemoryGameDAO implements GameDAO{
    @Override
    public void clearAll() {
        System.out.println("GAME CLEARED");
    }

    @Override
    public void createGame() {
        System.out.println("CREATEGAME");
    }

    @Override
    public void getGame() {
        System.out.println("GETGAME");
    }

    @Override
    public void listGames() {
        System.out.println("LISTGAMES");

    }

    @Override
    public void updateGame() {

    }
}
