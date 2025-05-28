package Model;

/**
 * A customized exception if the user breaks the traffic laws
 *  like moving on the red light
 */

public class TrafficException extends Exception {

    public TrafficException() {
        super("You have violated traffic laws.");
    }
}
