package server;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.io.ConnectionManager;

import java.io.IOException;
import java.util.Timer;


@WebSocket
public class WebSocketHandler {

    String status = "await input";
    String responseString = "";

    @OnWebSocketConnect
    public void onConnect(Session session) throws Exception {
        Server.gameSessions.put(session, 0);
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        Server.gameSessions.remove(session);
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws Exception {
        if (status.equals("await input")){
            switch (message){
                case "move":
                    responseString = "Sure. What do you want to move? Enter like: 'A1 B2' to move from A1 to B2.";
                    status = "await move input";
                    break;
            }
        }
        else if (status.equals("await move input")){
            if (message.matches("[A-Za-z][1-8]\s[A-Za-z][1-8]")){
                responseString = "Move made.";
            }
            else {
                responseString = "Sorry, move was not understood properly. Please enter like: 'A1 B2' to move from A1 to B2.";
            }
        }
        System.out.printf("Received: %s", message);
        session.getRemote().sendString(responseString);
    }
}