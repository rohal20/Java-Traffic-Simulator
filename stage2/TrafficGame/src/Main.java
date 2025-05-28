import java.util.*;

import GameMapPackage.Map;
import GameMapPackage.TrafficLight;
import GameMapPackage.TrafficSignal;
import PlayerPackage.Bots;
import PlayerPackage.MyPlayer;
import VehiclePackage.*;

public class Main {

    // Scanner to read input
    private static Scanner sc = new Scanner(System.in);
    public static VehicleReputation reputation = new VehicleReputation();

    public static void main(String[] args) {

        int playerLane = 1;
        Map gameMap = new Map(3); // Initialize the map with 3 lanes.
        MyPlayer myPlayer = new MyPlayer(); //get the user/ player
        Bots bot = new Bots();  //get the bots


        myPlayer.selectVehicle(); // Let the player select their vehicle.
        Vehicle player = myPlayer.getVehicle(); // Get the selected vehicle.
        Vehicle botVehicle = bot.selectRandomVehicle(); // Select a random vehicle for the bot.

        gameMap.addVehicleToLane(player, playerLane); // Add the vehicle to the first lane.
        gameMap.addVehicleToLane(botVehicle, 1); //add bot
        gameMap.displayMap(); // Display the map state to show the vehicle in the first lane.

        while (player.getCurrentHealth() > 0 ) {

            laneWork(gameMap, player, playerLane);
            IntersectionWork(gameMap, player, playerLane);
        }
        System.out.println("Game over! Your total reputation points: " + reputation.getPoints());
        sc.close(); // Close the scanner
    }
    private static void AmIALive(Vehicle player){
        if (player.getCurrentHealth() <= 0) {
            System.out.println("*********Game over!*********");
            System.out.println("Game over! Your total reputation points: " + reputation.getPoints());
            System.exit(0);

        }
    }
    private static void laneWork(Map gameMap, Vehicle player, int playerLane) {

        /** If health drops to below 0 GAME ENDS **/
        for (int round = 1; round < 5; round++) {
            //reputation.addPoints(5);
            //using lambda expression to re adjust the reputation points
            reputation.adjustPoints((currentPoints, pointsToAdd) -> currentPoints + pointsToAdd, 5);
            System.out.println("--------ROUND " + round + "--------");
            System.out.println("Type 'Q' to End Game, \nType 'S' to go straight, Type 'R' to move to the right lane, Type 'L' to move to the left lane, " +
                    "\nType 'C' to request information of vehicle’s surroundings");
            String move = sc.nextLine(); // Reading the user's move.

            switch (move.toUpperCase()) { // Use toUpperCase to handle both lowercase and uppercase inputs
                case "S":
                    if (gameMap.isVehicleInFront(player, playerLane)) {
                        player.reduceHealth(); // Assuming reduceHealth() method decreases the player's health.
                        System.out.println("You hit a vehicle in front. Your HEALTH is now: " + player.getCurrentHealth());
                        AmIALive(player);
                    } else {
                        System.out.println("You move forward safely.");
                    }
                    break;
                case "R":
                    if (playerLane < 2) { // Check if not in the rightmost lane
                        gameMap.removeVehicleFromLane(player, playerLane);
                        playerLane++;
                        gameMap.addVehicleToLane(player, playerLane);
                    } else {
                        player.reduceHealth();
                        System.out.println("Cannot move right. This is the first lane. Your HEALTH is: " + player.getCurrentHealth());
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
                        System.out.println("Cannot move left. This is the first lane. Your HEALTH is: " + player.getCurrentHealth());
                        AmIALive(player);
                    }
                    break;
                case "C":
                    gameMap.displayMap();  //while pressed the user will get to know the surroundings
                    round--;
                    break;
                case "Q":
                    System.out.println("*********Game over!*********");
                    System.out.println("Game over! Your total reputation points: " + reputation.getPoints());
                    System.exit(0);


                default: // Not counting invalid attempts as rounds
                    System.out.println("Invalid command. Please type 'R' to move right, or 'L' to move left.");
                    round--;
                    //continue;
            }

        }
    }

    private static void IntersectionWork(Map gameMap, Vehicle player, int playerLane) {
        //using lambda expression to re adjust the reputation points
        reputation.adjustPoints((currentPoints, pointsToAdd) -> currentPoints + pointsToAdd, 5);
        System.out.println("******************************************************");
        System.out.println("You Have Entered an Intersection.");
        System.out.println("1 to go straight, 2 for left turn, 3 for right turn, 4 to stop at the light");

        TrafficLight trafficLight = new TrafficLight(TrafficSignal.RED);
        boolean makeAnotherChoice = true;

        if (trafficLight.isSignalGreen()) {
            System.out.println("The traffic light is green.");
        } else {
            System.out.println("The traffic light is red.");
        }

        while (makeAnotherChoice) {
            gameMap.displayMap();
            System.out.println("Choose your action:");
            String inp = sc.nextLine(); // Reading the user's input.
            try {
                switch (inp) {
                    case "1":
                        if (!(playerLane == 1 || playerLane == 2) || !trafficLight.isSignalGreen()) {
                            throw new TrafficException();
                        }
                        System.out.println("You went straight");
                        makeAnotherChoice = false; // The player made a choice, exit the loop
                        break;
                    case "2":
                        if (playerLane != 0 || !trafficLight.isSignalGreen()) {
                            throw new TrafficException();
                        }
                        System.out.println("You went left");
                        makeAnotherChoice = false; // The player made a choice, exit the loop
                        break;
                    case "3":
                        if (playerLane != 2 || !trafficLight.isSignalGreen()) {
                            throw new TrafficException();
                        }
                        System.out.println("You went right");
                        makeAnotherChoice = false; // The player made a choice, exit the loop
                        break;
                    case "4":
                        System.out.println("You've stopped at the light.");
                        trafficLight.setSignal(TrafficSignal.GREEN);
                        System.out.println("The light turns green. Please choose your action again.");
                        break;
                    case "Q":
                        System.out.println("*********Game over!*********");
                        System.out.println("Game over! Your total reputation points: " + reputation.getPoints());
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
                        break;
                }
            } catch (TrafficException e) {
                System.out.println(e.getMessage());
                player.reduceHealth();
                AmIALive(player);
                makeAnotherChoice = false; // Exit the loop after handling the exception
            }
        }
    }

}


