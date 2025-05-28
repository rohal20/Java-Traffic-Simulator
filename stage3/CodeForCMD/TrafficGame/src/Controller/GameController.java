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

    public void startGame() {
        int vehicleChoice = view.promptVehicleSelection(); // Get vehicle choice from the user
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

        /** If health drops to below 0 GAME ENDS **/
        for (int round = 1; round < this.TRounds; round++) {
            //reputation.addPoints(5);
            //using lambda expression to re adjust the reputation points
            reputation.adjustPoints((currentPoints, pointsToAdd) -> currentPoints + pointsToAdd, 5);
            view.displayMessage("--------ROUND " + round + "--------");
            String prompt = "Type 'Q' to End Game, \nType 'S' to go straight, Type 'R' to move to the right lane, Type 'L' to move to the left lane, " +
                    "\nType 'C' to request information of vehicle’s surroundings";
            view.displayMessage(prompt);
            String move = view.getUserInput(prompt); // Reading the user's move.

            switch (move.toUpperCase()) { // Use toUpperCase to handle both lowercase and uppercase inputs
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
                    gameMap.displayMap();  //while pressed the user will get to know the surroundings
                    round--;
                    break;
                case "Q":
                    view.displayMessage("*********Game over!*********");
                    view.displayMessage("Game over! Your total reputation points: " + reputation.getPoints());
                    System.exit(0);


                default: // Not counting invalid attempts as rounds
                    view.displayMessage("Invalid command. Please type 'R' to move right, or 'L' to move left.");
                    round--;
                    //continue;
            }

        }
    }

    private void IntersectionWork(Map gameMap, Vehicle player, int playerLane) {
        //using lambda expression to re adjust the reputation points
        reputation.adjustPoints((currentPoints, pointsToAdd) -> currentPoints + pointsToAdd, 5);
        view.displayMessage("******************************************************");
        view.displayMessage("You Have Entered an Intersection.");
        view.displayMessage("1 to go straight, 2 for left turn, 3 for right turn, 4 to stop at the light");

        TrafficLight trafficLight = new TrafficLight(TrafficSignal.RED);
        boolean makeAnotherChoice = true;

        if (trafficLight.isSignalGreen()) {
            view.displayMessage("The traffic light is green.");
        } else {
            view.displayMessage("The traffic light is red.");
        }

        while (makeAnotherChoice) {
            gameMap.displayMap();
            String prompt2 = "Choose your action:";
            view.displayMessage(prompt2);
            String inp = view.getUserInput(prompt2);// Reading the user's input.
            try {
                switch (inp) {
                    case "1":
                        if (!(playerLane == 1 || playerLane == 2) || !trafficLight.isSignalGreen()) {
                            throw new TrafficException();
                        }
                        view.displayMessage("You went straight");
                        makeAnotherChoice = false;
                        break;
                    case "2":
                        if (playerLane != 0 || !trafficLight.isSignalGreen()) {
                            throw new TrafficException();
                        }
                        view.displayMessage("You went left");
                        makeAnotherChoice = false;
                        break;
                    case "3":
                        if (playerLane != 2 || !trafficLight.isSignalGreen()) {
                            throw new TrafficException();
                        }
                        view.displayMessage("You went right");
                        makeAnotherChoice = false;
                        break;
                    case "4":
                        System.out.println("You've stopped at the light.");
                        trafficLight.setSignal(TrafficSignal.GREEN);
                        view.displayMessage("The light turns green. Please choose your action again.");
                        break;
                    case "Q":
                        view.displayMessage("*********Game over!*********");
                        view.displayMessage("Game over! Your total reputation points: " + reputation.getPoints());
                        System.exit(0);
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
