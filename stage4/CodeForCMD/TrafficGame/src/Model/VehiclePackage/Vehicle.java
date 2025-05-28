package Model.VehiclePackage;

/** Abstract Class: Vehicle
 * Base Class for all vehicles types.**/
public abstract class Vehicle {
    String name; // Type Of Vehicle
    int speed; // Speed of Vehicle
    Health health; // Health object representing the health

    public Vehicle(String name, int speed, int maxHealth) {
        this.name = name; // Assign vehicle
        this.speed = speed; // Assign Speed
        this.health = new Health(maxHealth); // Initializes the health


    }

    /** return The name of the vehicle.**/
    public String getName() {return name;}

    /** return The health of the vehicle. **/
    public int getCurrentHealth() {
        return health.getCurrentHealth();
    }

    public int reduceHealth() {
        return health.decrease(20);
    }

    /** Method to check initialization and start the vehicle, optionally using a local class for specific tasks. */
    public void initializeAndStart() {

        //local class PreStartCheck to check for the pre start of the car
        class PreStartCheck {
            boolean checkInitialization() {
                return name != null && speed > 0;
            }
        }

        PreStartCheck checker = new PreStartCheck();
        if (checker.checkInitialization()) {

            System.out.println(name + " has started successfully with a speed of " + speed + " and health of " + health.getMaxHealth() +".");
        } else {
            System.out.println("Vehicle not properly initialized.");
        }
    }
}