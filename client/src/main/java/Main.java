import chess.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isLoggedIn = false;

        while (!isLoggedIn) {
            System.out.printf("\nWelcome to chess. Awaiting your input now, type \"help\" for possible commands.\n");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
            String responseOutput = "";

            switch (userInput){
                case "help": responseOutput = "--HELP MENU:--\n\"Register\": Create account\n\"Login\": Log into account\n\"Quit\": Exit the program";
			    break;
                case "login":
	        }
            System.out.println(responseOutput);

        }

    }
}