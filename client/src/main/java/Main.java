import server.ServerFacade;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean isLoggedIn = false;
        String username = "";
        ServerFacade serverFacade = new ServerFacade("http://localhost:8080");

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
//                            serverFacade.getAllGames();
                            System.out.println("All games:");
                            //for loop to cycle through all games and print out name of each with a number next to it
                            isLoggedIn = false;
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case "join":
                        //perform logout function
                        try {
                            var gameNameorID = scanner.next();
//                            serverFacade.joinGame
//                            serverFacade.getAllGames();
                            System.out.println("All games:");
                            //for loop to cycle through all games and print out name of each with a number next to it
                            isLoggedIn = false;
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        break;

                }
                System.out.println(responseOutput);

            }
        }
    }
}