package View;

import java.util.Scanner;

/** This Class is used for interactions with the view (console)
 *  To display messages to the user and getting input. **/
public class GameView {
    // A Scanner to read user input
    private final Scanner scanner;

    public GameView() {
        this.scanner = new Scanner(System.in);
    }

    // For sending the messages
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String getUserInput(String prompt) {
        return scanner.nextLine();
    }
    public int promptVehicleSelection() {

        System.out.println("Select your vehicle: \n1 for Car \n2 for Truck \n3 for Bus");

        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice >= 1 && choice <= 3) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}


