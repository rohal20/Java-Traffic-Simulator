package VehiclePackage;

/** Class for Calculating Vehicle Reputation **/
public class VehicleReputation {
    private int points;


    public interface PointsOperation {
        int operate(int currentPoints, int pointsToAdd);
    }

    public VehicleReputation() {
        this.points = 0; // Starting with 0 points
    }

    // Method to add points
//    public void addPoints(int pointsToAdd) {
//        this.points += pointsToAdd;
//    }

    public void adjustPoints(PointsOperation operation, int pointsToAdd) {
        this.points = operation.operate(this.points, pointsToAdd);
    }
    // Method to retrieve the current points
    public int getPoints() {
        return this.points;
    }
}

