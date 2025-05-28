package Controller;

import Model.GameMapPackage.Map;
import Model.GameMapPackage.TrafficLight;
import Model.GameMapPackage.TrafficSignal;
import Model.PlayerPackage.Bots;
import Model.PlayerPackage.MyPlayer;
import Model.TrafficException;
import Model.VehiclePackage.Vehicle;
import Model.VehiclePackage.VehicleReputation;
import View.GameView;

/** This Class was created to support the MVC design. In this class we have the main
logic of how the game works such as loading the GameMap xml file creating the map and
 then going into game as the user works through the roads and intersections. **/

public class GameController {
    private GameView view;
    private Map gameMap;
    private MyPlayer myPlayer;
    private Bots bots;
    private VehicleReputation reputation;
    private int playerLane;
    private int TRounds;


    public GameController() {

        MapConfig config = MapLoader.loadConfig("MapDesign.xml");
        this.view = new GameView();
        this.gameMap = new Map(config.getMapLanes()); // Number of lanes from XML
        this.myPlayer = new MyPlayer();
        this.bots = new Bots();
        this.reputation = new VehicleReputation();
        this.TRounds = config.getRounds(); // Number of rounds from XML
        this.playerLane = config.getBotVehicleLane(); // Starting lane might be adjusted based on XML, or for bot usage

    }

    public void startGame(int vehicleChoice) {

        myPlayer.selectVehicle(vehicleChoice);

        Vehicle playerVehicle = myPlayer.getVehicle();
        Vehicle botVehicle = bots.selectRandomVehicle();

        gameMap.addVehicleToLane(playerVehicle, playerLane);
        gameMap.addVehicleToLane(botVehicle, this.playerLane);

        while (playerVehicle.getCurrentHealth() > 0) {
            laneWork(gameMap, playerVehicle, playerLane);
            IntersectionWork(gameMap, playerVehicle, playerLane);
        }

        view.displayMessage("Game over! Your total reputation points: " + reputation.getPoints());

    }

    private void AmIALive(Vehicle player){
        if (player.getCurrentHealth() <= 0) {
            view.displayMessage("*********Game over!*********");
            view.displayMessage("Game over! Your total reputation points: " + reputation.getPoints());
            System.exit(0);

        }
    }
    private void laneWork(Map gameMap, Vehicle player, int playerLane) {
        for (int round = 1; round <= this.TRounds; round++) {
            reputation.adjustPoints((currentPoints, pointsToAdd) -> currentPoints + pointsToAdd, 5);
            view.displayMessage("--------ROUND " + round + "--------");

            String move = view.promptLaneMove();

            switch (move.toUpperCase()) {
                case "S":
                    if (gameMap.isVehicleInFront(player, playerLane)) {
                        player.reduceHealth(); //reduceHealth() method decreases the player's health.
                        view.displayMessage("You hit a vehicle in front. Your HEALTH is now: " + player.getCurrentHealth());
                        AmIALive(player);
                    } else {
                        view.displayMessage("You move forward safely.");
                    }
                    break;
                case "R":
                    if (playerLane < 2) { // Check if not in the rightmost lane
                        gameMap.removeVehicleFromLane(player, playerLane);
                        playerLane++;
                        gameMap.addVehicleToLane(player, playerLane);
                    } else {
                        player.reduceHealth();
                        view.displayMessage("Cannot move right. This is the first lane. Your HEALTH is: " + player.getCurrentHealth());
                        AmIALive(player);
                    }
                    break;
                case "L":
                    if (playerLane > 0) { // Check if not in the leftmost lane
                        gameMap.removeVehicleFromLane(player, playerLane);
                        playerLane--;
                        gameMap.addVehicleToLane(player, playerLane);
                    } else {
                        player.reduceHealth();
                        view.displayMessage("Cannot move left. This is the first lane. Your HEALTH is: " + player.getCurrentHealth());
                        AmIALive(player);
                    }
                    break;
                case "C":
                    gameMap.displayMap();
                    round--; // Adjust round counter to not count this as a move
                    break;
                case "Q":
                    view.displayMessage("*********Game over!*********");
                    view.displayMessage("Game over! Your total reputation points: " + reputation.getPoints());
                    System.exit(0);
                default:
                    view.displayMessage("Invalid command. Please try again.");
                    round--; // Adjust round counter to not count this as a move
                    break;
            }

            if (player.getCurrentHealth() <= 0) {
                view.displayMessage("Invalid command. Please type 'R' to move right, or 'L' to move left.");
                round--;
            }
        }
    }


    private void IntersectionWork(Map gameMap, Vehicle player, int playerLane) {
        reputation.adjustPoints((currentPoints, pointsToAdd) -> currentPoints + pointsToAdd, 5);
        view.displayMessage("******************************************************");
        view.displayMessage("You Have Entered an Intersection.");

        TrafficLight trafficLight = new TrafficLight(TrafficSignal.RED);
        boolean makeAnotherChoice = true;

        while (makeAnotherChoice) {
            int action = view.promptIntersectionAction();

            try {
                switch (action) {
                    case 1: // Go straight
                        if (!(playerLane == 1 || playerLane == 2) || !trafficLight.isSignalGreen()) {
                            throw new TrafficException();
                        }
                        view.displayMessage("You went straight");
                        makeAnotherChoice = false;
                        break;
                    case 2: // Turn left
                        if (playerLane != 0 || !trafficLight.isSignalGreen()) {
                            throw new TrafficException();
                        }
                        view.displayMessage("You went left");
                        makeAnotherChoice = false;
                        break;
                    case 3: // Turn right
                        if (playerLane != 2 || !trafficLight.isSignalGreen()) {
                            throw new TrafficException();
                        }
                        view.displayMessage("You went right");
                        makeAnotherChoice = false;
                        break;
                    case 4: // Stop at the light
                        System.out.println("You've stopped at the light.");
                        trafficLight.setSignal(TrafficSignal.GREEN);
                        view.displayMessage("The light turns green. Please choose your action again.");
                        break;
                    default:
                        view.displayMessage("Invalid choice. Please select 1, 2, 3, or 4.");
                        break;
                }
            } catch (TrafficException e) {
                view.displayMessage(e.getMessage());
                player.reduceHealth();
                AmIALive(player);
                makeAnotherChoice = false; // Exit the loop
                }
            }
        }
    }
