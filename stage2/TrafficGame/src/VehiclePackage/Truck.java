package VehiclePackage;

/** SubClass of Vehicle "TRUCK" **/
public class Truck extends Vehicle {

    /** Setting, "Truck" is the vehicle type, 90 is the speed, and 120 is the maximum health of the truck.**/
    public Truck() {
        super("Truck", 90,120);
    }
    /** Override the abstract start method from the Vehicle class.**/
    @Override
    public void initializeAndStart() {
         super.initializeAndStart(); // Call the Vehicle's start method
    }
}

