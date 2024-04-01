import Exceptions.DataAccessException;
import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;
import model.GameData;
import server.ResponseException;
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
        ChessBoard tempPhaseFiveBoard = new ChessBoard();
        tempPhaseFiveBoard.resetBoard();

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
                            System.out.println("Type player color to join as (b = black, w = white, s = spectator)");
                            var colorInput = scanner.next();
                            ChessGame.TeamColor teamColor = null;
                            if (colorInput.equals("b")){
                                 teamColor = ChessGame.TeamColor.BLACK;
                            }
                            else if (colorInput.equals("w")){
                                 teamColor = ChessGame.TeamColor.WHITE;
                            }
                            else if (colorInput.equals("s")){
                                 teamColor = null;
                                System.out.println("Game joined as spectator.");
                            }
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
//                ChessBoard tempPhaseFiveBoard = new ChessBoard();
//                tempPhaseFiveBoard.resetBoard();
//                printBoard(tempPhaseFiveBoard);
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

    public static void printBoard(ChessBoard board, boolean isWhitePOV){
        final String ANSI_START = "\u001B[";
        final String ANSI_RESET = "0";
        final String ANSI_RED_FOREGROUND = "31";
        final String ANSI_BLUE_FOREGROUND = "34";
        final String ANSI_WHITE_BACKGROUND = "47";
        final String ANSI_BLACK_BACKGROUND = "40";
        final String ANSI_END = "m";


        String black_on_grey = "\u001b[30;100;1m";
        String[] header = {" ", "a", "b", "c", "d", "e", "f", "g", "h", " "};

        System.out.println("\n");
        //print header
        int startRow = isWhitePOV ? 1 : 8;
        int endRow = isWhitePOV ? 9 : 0;
        int rowIncrement = isWhitePOV ? 1 : -1;

        int startCol = isWhitePOV ? 0 : 9;
        int endCol = isWhitePOV ? 10 : -1;
        int colIncrement = isWhitePOV ? 1 : -1;

        for (int i = startCol; i != endCol; i+=colIncrement){
            System.out.print(black_on_grey + " " + header[i] + " ");
        }
        System.out.print(ANSI_START + ANSI_RESET + ANSI_END);
        System.out.print("\n");
        //print board
        for (int row = startRow; row != endRow; row += rowIncrement) {
            for (int col = startCol; col != endCol; col += colIncrement) {
                if (col == 0 || col == 9){
                    System.out.print(black_on_grey + " " + row + " ");
                    continue;
                }
                ChessPosition tempPos = new ChessPosition(row, col);
                var tempPiece = board.getPiece(tempPos);
                String letterToPrint = " ";
                String foreGroundToPrint = "";
                String backgroundToPrint = ANSI_BLACK_BACKGROUND;
                if ((row+col) % 2 == 0){
                    backgroundToPrint = ANSI_WHITE_BACKGROUND;
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
                        foreGroundToPrint = ANSI_RED_FOREGROUND;
                    } else if (tempPiece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                        foreGroundToPrint = ANSI_BLUE_FOREGROUND;
                    }
                }
                System.out.print(ANSI_START + foreGroundToPrint + ";" + backgroundToPrint + ANSI_END +" " + letterToPrint + " ");
            }
                System.out.print(ANSI_START + ANSI_RESET + ANSI_END + "\n");
        }
        //print footer
        for (int i = startCol; i != endCol; i+=colIncrement){
            System.out.print(black_on_grey + " " + header[i] + " ");
        }
        System.out.print(ANSI_START + ANSI_RESET + ANSI_END + "\n");
    }
}