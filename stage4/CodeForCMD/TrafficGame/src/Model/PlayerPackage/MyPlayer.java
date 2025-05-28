package Model.PlayerPackage;

import Model.VehiclePackage.*;

public class MyPlayer {

    private Vehicle vehicle; // This will hold the selected vehicle.

    public MyPlayer() {
    }

    public void selectVehicle(int choice) {
        String vehicleType;
        switch (choice) {
            case 1:
                vehicleType = "Car";
                break;
            case 2:
                vehicleType = "Truck";
                break;
            case 3:
                vehicleType = "Bus";
                break;
            default:
                System.out.println("Invalid choice, defaulting to Car.");
                vehicleType = "Car"; // Default to Car if invalid choice
                break;
        }

        this.vehicle = VehicleFactory.createVehicle(vehicleType);
        if (this.vehicle != null) {
            System.out.println("You have selected: " + vehicle.getName());
            vehicle.initializeAndStart();
        } else {
            System.out.println("Vehicle creation failed.");
        }
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }
}
