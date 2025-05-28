package Model.VehiclePackage;

/**
 * This class was made to help with the Factory design pattern.
 * This factory is responsible for creating vehicle object based of
 * user input.
 */
public class VehicleFactory {

    public static Vehicle createVehicle(String type) {
        switch (type) {
            case "Car":
                return new Car();
            case "Truck":
                return new Truck();
            case "Bus":
                return new Bus();
            default:
                return null;
        }
    }
}
