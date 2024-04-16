import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;
import model.GameData;
import facade.ResponseException;
import facade.ServerFacade;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean isLoggedIn = false;
        boolean isInGame = false;
        String username = "";
        ServerFacade serverFacade = new ServerFacade("http://localhost:8080");
        ChessBoard tempPhaseFiveBoard = new ChessBoard();
        tempPhaseFiveBoard.resetBoard();
        HashMap<Integer, Integer> actualIDs = new HashMap<Integer, Integer>();


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
                    case "print":
                        printBoard(tempPhaseFiveBoard, true);
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
                            isLoggedIn = true;
                        } catch (Exception e){
                            System.out.println("Sorry, your username/password combination were incorrect.");
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
                        System.out.print("--HELP MENU:--\n\"Games\": view games\n\"Logout\": Log out of account\n\"Quit\": Exit the program\n\"join\": join a game\n\"Create\": Create a game\n\"Join observer\": Join a game as an observer");
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
                            int incrementor = 0;
                            for (GameData game : games) {
                                incrementor++;
                                System.out.println("  " + incrementor + ": " + game.gameName() + ", black:" + game.blackUsername()+ ", white:" + game.whiteUsername());
                                actualIDs.put(incrementor, game.gameID());
                            }
                            //for loop to cycle through all games and print out name of each with a number next to it
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case "join":
                        try {
                            ChessGame.TeamColor teamColor = null;
                            System.out.println("Type game ID to join");
                            var gameID = scanner.nextInt();
                                System.out.println("Type player color to join as (b = black, w = white, s = spectator)");
                                var colorInput = scanner.next();
                                if (colorInput.equals("b")){
                                     teamColor = ChessGame.TeamColor.BLACK;
                                }
                                else if (colorInput.equals("w")){
                                     teamColor = ChessGame.TeamColor.WHITE;
                                }
                            int actualID = actualIDs.get(gameID);
                            serverFacade.joinGame(actualID, teamColor);
                            System.out.println("Game joined. Welcome bro.");
                            isLoggedIn = false;
                            isInGame = true;
//                            currGame = serverFacade.getGame();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case "join observer":
                        try {
                            ChessGame.TeamColor teamColor = null;
                                System.out.println("Joining game as spectator.");
                                teamColor = null;
                            System.out.println("Type game ID to join");
                            var gameID = scanner.nextInt();
                            serverFacade.joinGame(gameID, teamColor);
                            System.out.println("Game joined. Welcome bro.");
                            isLoggedIn = false;
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
                            try {
                                serverFacade.createGame(gameName);
                                System.out.println("Game created. View game by typing \"games\"");
                            } catch (ResponseException e){
                                System.out.println("Sorry, something happened and this failed lol!");
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                }
                System.out.println(responseOutput);

            }
            while (isInGame) {
                System.out.println("\n\u001b[32m-*-*-*IN GAME*-*-*-");
                System.out.print("\n\u001b[39m[IN_GAME] >>> ");
                printBoard(tempPhaseFiveBoard, true);
                printBoard(tempPhaseFiveBoard, false);
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
                }
            }
        }
    }

    public static void printBoard(ChessBoard board, boolean isWhitePOV){
        final String ansiStart = "\u001B[";
        final String ansiReset = "0";
        final String ansiRedForeground = "31";
        final String ansiBlueForeground = "34";
        final String ansiWhiteBackground = "47";
        final String ansiBlackBackground = "40";
        final String ansiEnd = "m";


        String blackOnGrey = "\u001b[30;100;1m";
        String[] header = {" ", "a", "b", "c", "d", "e", "f", "g", "h", " "};

        System.out.println("\n");
        //print header
        int startRow = isWhitePOV ? 8 : 1;
        int endRow = isWhitePOV ? 0 : 9;
        int rowIncrement = isWhitePOV ? -1 : 1;

        int startCol = isWhitePOV ? 0 : 9;
        int endCol = isWhitePOV ? 10 : -1;
        int colIncrement = isWhitePOV ? 1 : -1;

        int startColRight = isWhitePOV ? 9 : 0;
        int endColRight = isWhitePOV ? -1 : 10;
        int colIncrementRight = isWhitePOV ? -1 : 1;



        for (int i = startCol; i != endCol; i+=colIncrement){
            System.out.print(blackOnGrey + " " + header[i] + " ");
        }
        System.out.print(ansiStart + ansiReset + ansiEnd);
        System.out.print("\n");
        //print board
        for (int row = startRow; row != endRow; row += rowIncrement) {
            for (int col = startCol; col != endCol; col += colIncrement) {
                if (col == 0 || col == 9){
                    System.out.print(blackOnGrey + " " + row + " ");
                    continue;
                }
                ChessPosition tempPos = new ChessPosition(row, col);
                var tempPiece = board.getPiece(tempPos);
                String letterToPrint = " ";
                String foreGroundToPrint = "";
                String backgroundToPrint = ansiWhiteBackground;
                if ((row+col) % 2 == 0){
                    backgroundToPrint = ansiBlackBackground;
                }
                if (tempPiece != null) {
                    if (tempPiece.getPieceType() == ChessPiece.PieceType.PAWN) {
                        letterToPrint = "P";
                    }
                    if (tempPiece.getPieceType() == ChessPiece.PieceType.ROOK) {
                        letterToPrint = "R";
                    }
                    if (tempPiece.getPieceType() == ChessPiece.PieceType.KNIGHT) {
                        letterToPrint = "N";
                    }
                    if (tempPiece.getPieceType() == ChessPiece.PieceType.BISHOP) {
                        letterToPrint = "B";
                    }
                    if (tempPiece.getPieceType() == ChessPiece.PieceType.KING) {
                        letterToPrint = "K";
                    }
                    if (tempPiece.getPieceType() == ChessPiece.PieceType.QUEEN) {
                        letterToPrint = "Q";
                    }

                    if (tempPiece.getTeamColor() == ChessGame.TeamColor.BLACK) {
                        foreGroundToPrint = ansiRedForeground;
                    } else if (tempPiece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                        foreGroundToPrint = ansiBlueForeground;
                    }
                }
                System.out.print(ansiStart + foreGroundToPrint + ";" + backgroundToPrint + ansiEnd +" " + letterToPrint + " ");
            }
                System.out.print(ansiStart + ansiReset + ansiEnd + "\n");
        }
        //print footer
        for (int i = startCol; i != endCol; i+=colIncrement){
            System.out.print(blackOnGrey + " " + header[i] + " ");
        }
        System.out.print(ansiStart + ansiReset + ansiEnd + "\n");
    }
}