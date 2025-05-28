package PlayerPackage;
import VehiclePackage.*;


import java.util.Scanner;

//class for the user MyPlayer
public class MyPlayer {

    private Vehicle vehicle; // This will hold the selected vehicle.

    // Constructor
    public MyPlayer() {
    }

    public void selectVehicle() {
        Scanner scanner = new Scanner(System.in);
        //providing the menu to the user to select any vehicle they like from the list.
        System.out.println("Select your vehicle: \n1 for Car \n2 for Truck \n3 for Bus");
        boolean choiceMade = false;
        while (!choiceMade) {
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    this.vehicle = new Car();
                    choiceMade = true;
                    break;
                case "2":
                    this.vehicle = new Truck();
                    choiceMade = true;
                    break;
                case "3":
                    this.vehicle = new Bus();
                    choiceMade = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
                    break; //will break if any other character is entered apart from the valid choices.
            }
        }
        System.out.println("You have selected: " + vehicle.getName());
        vehicle.initializeAndStart(); // If you want to call the start here.
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }
}

