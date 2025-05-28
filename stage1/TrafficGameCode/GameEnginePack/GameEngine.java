package GameEnginePack;

import GameMapPack.Map;
import GameMapPack.Lanes;
import PlayerPack.MyPlayer;
import VehiclePack.Vehicles;

import java.util.ArrayList;
import java.util.List;

// Implements the DecisionMaker interface
public class GameEngine implements DecisionMaker {

  // Represent the player within the game.
  private MyPlayer MyPlayer;

  // Manage lane information within the game.
  public Lanes Lane;

  // Manage the game map.
  public Map Map;

  // Manage the vehicles present in the game.
  private List<Vehicles> vehicles = new ArrayList<>();

  // Starts the game loop.
  public void startGame() {
  }

  // End the game.
  public void endGame() {
  }

  // Decision logic for navigating intersections.
  @Override
  public void intersectionDecision() {
    System.out.println("Making decision at intersection");
  }

  // Decision logic for changing lanes.
  @Override
  public void laneDecision() {

  }

  // Decision logic for handling challenges within the game.
  @Override
  public void challengeDecision() {

  }

  // Decision logic for making gambles within the game.
  @Override
  public void gambleDecision() {

  }

  public static void main(String[] args) {
    GameEngine gameEngine = new GameEngine();
    gameEngine.startGame();
  }
}
