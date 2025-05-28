package View;

import java.util.Scanner;

/**
 * This is our view class.
 * We are making use of the scanner.
 * we have a method to display a message, get userInput,
 * prompt vehicle selection, prompt lane options,
 * prompt intersection options, prompt for username.
 */

public class GameView {
    private final Scanner scanner;

    public GameView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String getUserInput(String prompt) {
        displayMessage(prompt); // Display the prompt to the user
        return scanner.nextLine();
    }

    public int promptVehicleSelection() {
        return promptWithChoices("Select your vehicle: \n1 for Car \n2 for Truck \n3 for Bus", 1, 3);
    }

    public String promptLaneMove() {
        String prompt = "Type 'Q' to End Game, \nType 'S' to go straight, Type 'R' to move to the right lane, Type 'L' to move to the left lane, \nType 'C' to request information of vehicle’s surroundings";
        return getUserInput(prompt);
    }
    public String promptForUsername() {
        System.out.println("Please enter your username:");
        return scanner.nextLine();
    }

    public int promptIntersectionAction() {
        return promptWithChoices("1 to go straight, 2 for left turn, 3 for right turn, 4 to stop at the light", 1, 4);
    }

    private int promptWithChoices(String prompt, int minChoice, int maxChoice) {
        while (true) {
            try {
                int choice = Integer.parseInt(getUserInput(prompt));
                if (choice >= minChoice && choice <= maxChoice) {
                    return choice;
                } else {
                    displayMessage("Invalid choice. Please select between " + minChoice + " and " + maxChoice + ".");
                }
            } catch (NumberFormatException e) {
                displayMessage("Invalid input. Please enter a number.");
            }
        }
    }
}
