package VehiclePackage;

/** SubClass of Vehicle "BUS" **/
public class Bus extends Vehicle {

    /** Setting, "Bus" is the vehicle type, 60 is the speed, and 140 is the maximum health of the Bus.**/
    public Bus() {
        super("Bus", 60, 140);
    }
    /** Override the abstract start method from the Vehicle class.**/
    public void initializeAndStart() {
        super.initializeAndStart(); // Call the Vehicle's start method
    }
}

