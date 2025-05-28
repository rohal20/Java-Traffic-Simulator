package VehiclePackage;

/** Health Class is used to manage the health of the players vehicle  **/
public class Health {

    /** maxHealth stores the maximum health a certain vehicle can have or starts with, it uses final for this reason as no future change will occur.
     *  currentHealth, displays the current health left of the vehicle throughout gameplay.  */
    private final int maxHealth;
    private int currentHealth;

    /** Constructor for the health class **/
    public Health(int maxHealth) {
        this.maxHealth = maxHealth; //set max health
        this.currentHealth = maxHealth; // Initially, set current health to max health
    }

    /** Method to retrieve the current health **/
    public int getCurrentHealth() {
        return currentHealth;
    }
    /** Method to retrieve the current health **/
    public int getMaxHealth() {
        return maxHealth;
    }
    /** Method to reduce health based on collision damage **/
    public int decrease(int amount) {
        this.currentHealth -= amount;
        return amount;
    }
}

