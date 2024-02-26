import chess.*;
import server.Server;

public class Main {
    public static void main(String[] args) {
        var server = new Server();
        int sparkPort = server.run(8080);
        System.out.println("♕ 240 Chess Server running on port: " + sparkPort);

//        server.stop();
//        System.out.println("Server stopped");
    }
}