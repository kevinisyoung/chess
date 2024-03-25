import chess.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isLoggedIn = false;
        String username = "";


        while (!isLoggedIn) {
            System.out.println("\nWelcome to chess. Awaiting your input now, type \"help\" for possible commands.");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.next();
            userInput = userInput.toLowerCase();
            String responseOutput = "";

            String password = null;
            String email = null;

            switch (userInput){
                case "help": responseOutput = "--HELP MENU:--\n\"Register\": Create account\n\"Login\": Log into account\n\"Quit\": Exit the program";
			    break;
                case "login":
                    System.out.print("Please enter <username> <password>");
                    username = scanner.next();
                    password = scanner.next();
                    responseOutput = "Username: " + username + ", Password: " + password;
                    System.out.println("\n\n\n\n\n\n\n");
                    // perform login function
                    isLoggedIn = true;
                    break;
                case "register":
                    System.out.println("Enter the following information: <username> <password> <email>");
                    username = scanner.next();
                    password = scanner.next();
                    email = scanner.next();
                    responseOutput = username + password + email;
                    break;
	        }
            System.out.println(responseOutput);

        }
        while (isLoggedIn) {
            System.out.println("\nWelcome, " + username + ". Awaiting your input now, type \"help\" for possible commands.");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                scanner.skip(".*");
            }
            String userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
            String responseOutput = "";

            String password;
            String email;

            switch (userInput){
                case "help": responseOutput = "--HELP MENU:--\n\"Register\": Create account\n\"Login\": Log into account\n\"Quit\": Exit the program";
			    break;
                case "login":
                    System.out.println("Enter the following information: <username> <password>");
                    username = scanner.next();
                    password = scanner.next();
                    responseOutput = username + password;
                    //perform login function
                    isLoggedIn = true;
                    break;
                case "register":
                    System.out.println("Enter the following information: <username> <password> <email>");
                    username = scanner.next();
                    password = scanner.next();
                    email = scanner.next();
                    responseOutput = username + password + email;
                    break;
	        }
            System.out.println(responseOutput);

        }

    }
}