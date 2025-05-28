package GameMapPackage;

import VehiclePackage.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class Map {
    // A list for all the lanes within the map.
    private List<Lane> lanes;
    // A list for intersections within the map.
    private List<Intersection> intersections;

        public Map(int numberOfLanes) {
        lanes = new ArrayList<>();
        //number of lanes and add them to the list.
        for (int i = 0; i < numberOfLanes; i++) {
            lanes.add(new Lane());
        }

    }

    // This method adds a vehicle to a specified lane.
    public void addVehicleToLane(Vehicle vehicle, int laneNumber) {
        // Check if laneNumber is within bounds.
        if (laneNumber >= 0 && laneNumber < lanes.size()) {
            // Add vehicle.
            lanes.get(laneNumber).addVehicle(vehicle);
        } else {
            System.out.println("Invalid lane number."); // the lane is not valid, they will take damage (future).
        }
    }

    // Removes a vehicle from a specified lane if the lane number is valid.
    public void removeVehicleFromLane(Vehicle vehicle, int laneNumber) {
        // Check if laneNumber is within bounds.
        if (laneNumber >= 0 && laneNumber < lanes.size()) {
            // Remove the vehicle
            lanes.get(laneNumber).removeVehicle(vehicle);
        } else {
            System.out.println("Invalid lane number."); // the lane is not valid, they will take damage (future).
        }
    }

    // Displays the current map, showing which vehicles are in which lanes.
    public void displayMap() {
        int laneCount = 1; // Counter for lane numbers.
        for (Lane lane : lanes) {
            System.out.print("Lane " + laneCount++ + ": "); // Print the lane number.
            for (Vehicle vehicle : lane.getVehicles()) {
                System.out.print(vehicle.getName() + " "); // Print each vehicle's name.
            }
            System.out.println(); // New line for the next lane's vehicles.
        }
    }

    public boolean isVehicleInFront(Vehicle player, int laneNumber) {

        Lane lane = lanes.get(laneNumber);
        List<Vehicle> vehicles = lane.getVehicles(); // returns a list of vehicles in the lane.
        int playerIndex = vehicles.indexOf(player); // Find the index of the player's vehicle in the list.

        // Check if there is any vehicle in front of the player's vehicle.
        for (int i = playerIndex + 1; i < vehicles.size(); i++) {
            if (vehicles.get(i) != null) {
                // Find a vehicle in front of the player.
                return true;
            }
        }

        return false;
    }



}
