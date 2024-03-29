import additionalRecords.GameRequest;
import chess.ChessGame;
import model.GameData;
import server.ServerFacade;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean isLoggedIn = false;
        boolean isInGame = false;
        String username = "";
        ServerFacade serverFacade = new ServerFacade("http://localhost:8080");
        ChessGame currGame;

        while (true) {
            while (!isLoggedIn) {
                System.out.println("\n\u001b[32mWelcome to chess. You are not logged in. Awaiting your input now, type \"help\" for possible commands.");
                System.out.print("\n\u001b[39m[LOGGED_OUT] >>> ");
                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.next();
                userInput = userInput.toLowerCase();

                String password = null;
                String email = null;

                switch (userInput) {
                    case "help":
                        System.out.print("\u001b[34m");
                        System.out.print("--HELP MENU:--\n\"Register\": Create account\n\"Login\": Log into account\n\"Quit\": Exit the program");
                        break;
                    case "quit":
                        System.exit(0);
                        break;
                    case "login":
                        System.out.print("\u001b[34m");
                        System.out.println("Please enter <username> <password>");
                        username = scanner.next();
                        password = scanner.next();
                        System.out.print("\u001b[37m");
                        System.out.println("Username: " + username + ", Password: " + password);
                        // perform login function
                        try {
                            serverFacade.login(username, password);
                            System.out.println("Login successful.");
                            isLoggedIn = true;
                        } catch (Exception e){
                            e.printStackTrace();
                            System.exit(0);
                        }
                        break;
                    case "register":
                        System.out.println("Enter the following information: <username> <password> <email>");
                        username = scanner.next();
                        password = scanner.next();
                        email = scanner.next();
                        System.out.println("Username: " + username + ", Password: " + password + ", Email: " + email);
                        // perform register function
                        try {
                            serverFacade.register(username, password,email);
                            System.out.println("Registration and login successful.");
                            isLoggedIn = true;
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                }
            }
            while (isLoggedIn) {
                System.out.print("\u001b[39m");
                System.out.println("\nWelcome, " + username + ". Thanks for coming to play, type \"help\" for help.");
                System.out.print("\n\u001b[35m[LOGGED_IN--"+username+"] >>> ");
                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.nextLine();
                userInput = userInput.toLowerCase();
                String responseOutput = "";

                String password;
                String email;

                switch (userInput) {
                    case "help":
                        System.out.print("\u001b[34m");
                        System.out.print("--HELP MENU:--\n\"Register\": Create account\n\"Logout\": Log out of account\n\"Quit\": Exit the program");
                        break;
                    case "logout":
                        //perform logout function
                        try {
                            serverFacade.logout();
                            System.out.println("Logout successful.");
                            isLoggedIn = false;
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case "games":
                        //perform logout function
                        try {
                            System.out.println("All games:");
                            HashSet<GameData> games = serverFacade.listGames();

                            for (GameData game : games) {

                                System.out.println("  " + game.gameID() + ": " + game.gameName() + ", black:" + game.blackUsername()+ ", white:" + game.whiteUsername());
                            }
                            //for loop to cycle through all games and print out name of each with a number next to it
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case "join":
                        try {
                            System.out.println("Type game ID to join");
                            var gameID = scanner.nextInt();
                            System.out.println("Type player color to join as (b = black, w = white");
                            var colorInput = scanner.next();
                            ChessGame.TeamColor teamColor = null;
                            if (colorInput.equals("b")){
                                 teamColor = ChessGame.TeamColor.BLACK;
                            }
                            else if (colorInput.equals("w")){
                                 teamColor = ChessGame.TeamColor.WHITE;
                            }
                            serverFacade.joinGame(gameID, teamColor);
                            System.out.println("Game joined. Welcome bro.");
                            isInGame = true;
//                            currGame = serverFacade.getGame();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case "create":
                        //perform logout function
                        try {
                            System.out.println("Please enter your game's name:");
                            var gameName = scanner.next();
                            serverFacade.createGame(gameName);
                            //for loop to cycle through all games and print out name of each with a number next to it
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                }
                System.out.println(responseOutput);

            }
            while (isInGame) {
                System.out.println("\n\u001b[32mWelcome to chess. You are not logged in. Awaiting your input now, type \"help\" for possible commands.");
                System.out.print("\n\u001b[39m[LOGGED_OUT] >>> ");
                Scanner scanner = new Scanner(System.in);
                String userInput = scanner.next();
                userInput = userInput.toLowerCase();

                String password = null;
                String email = null;

                switch (userInput) {
                    case "help":
                        System.out.print("\u001b[34m");
                        System.out.print("--HELP MENU:--\n\"Register\": Create account\n\"Login\": Log into account\n\"Quit\": Exit the program");
                        break;
                    case "quit":
                        System.exit(0);
                        break;
                    case "leave":
                        isInGame = false;
                        break;
                    case "login":
                        System.out.print("\u001b[34m");
                        System.out.println("Please enter <username> <password>");
                        username = scanner.next();
                        password = scanner.next();
                        System.out.print("\u001b[37m");
                        System.out.println("Username: " + username + ", Password: " + password);
                        // perform login function
                        try {
                            serverFacade.login(username, password);
                            System.out.println("Login successful.");
                            isLoggedIn = true;
                        } catch (Exception e){
                            e.printStackTrace();
                            System.exit(0);
                        }
                        break;
                    case "register":
                        System.out.println("Enter the following information: <username> <password> <email>");
                        username = scanner.next();
                        password = scanner.next();
                        email = scanner.next();
                        System.out.println("Username: " + username + ", Password: " + password + ", Email: " + email);
                        // perform register function
                        try {
                            serverFacade.register(username, password,email);
                            System.out.println("Registration and login successful.");
                            isLoggedIn = true;
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                }
            }
        }
    }
}