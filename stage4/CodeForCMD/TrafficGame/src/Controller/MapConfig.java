package Controller;

/** Class to hold configuration settings for the gameMap **/

public class MapConfig {
    private int mapLanes;
    private int rounds;
    private int botVehicleLane;

    // Getters and setters
    public int getMapLanes() {
        return mapLanes;
    }

    public void setMapLanes(int mapLanes) {
        this.mapLanes = mapLanes;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getBotVehicleLane() {
        return botVehicleLane;
    }

    public void setBotVehicleLane(int botVehicleLane) {
        this.botVehicleLane = botVehicleLane;
    }
}
