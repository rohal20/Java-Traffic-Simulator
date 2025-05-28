package PlayerPackage;
import VehiclePackage.*;

import java.util.Random;

//this is the class for bots to be generated and select their vehicles randomly.
public class Bots {
    public Vehicle selectRandomVehicle() {
        Random random = new Random();
        int choice = random.nextInt(3); // Generates a random number between 0 and 2

        switch (choice) {
            case 0:
                return new Car();

            case 1:
                return new Truck();

            case 2:
                return new Bus();

            default:
                return new Car(); //if nothing is selected the default vehicle is car

        }
    }
}
