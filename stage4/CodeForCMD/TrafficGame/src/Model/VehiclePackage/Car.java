package Model.VehiclePackage;

/** SubClass of Vehicle "CAR" **/
public class Car extends Vehicle {

    /** Setting, "Car" is the vehicle type, 120 is the speed, and 100 is the maximum health of the car.**/
    public Car() {
        super("Car", 120, 100);
    }
    /** Override the abstract start method from the Vehicle class.**/
    public void initializeAndStart() {
        super.initializeAndStart(); // Call the Vehicle's start method
    }
}

