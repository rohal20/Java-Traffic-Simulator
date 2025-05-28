package Model.GameMapPackage;

import Model.VehiclePackage.Vehicle;

import java.util.LinkedList;
import java.util.List;

/** this is the lane class using linked list to add the list of
 * vehicles in the lane to make prior decision, like adding the
 * vehicle or removing the vehicle from the lane.
 *
 */
public class Lane {
    private List<Vehicle> vehicles;

    public Lane() {
        this.vehicles = new LinkedList<>();
    }
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}