package GameMapPack;

import GameEnginePack.GameEngine;
import java.util.ArrayList;
import java.util.HashMap;

// Represents the game map, managing intersections, roads, and their relationships.
public class Map {

  // Adjacency list representing the map's structure: intersections connected by roads.
  private final HashMap<Object, Object> adjList;

  // Adjacency list for storing the map's structure.
  public Map() {
    this.adjList = new HashMap<>();
  }

  // Adds an intersection to the map
  public void makeIntersection(Intersection intersection) {
    adjList.putIfAbsent(intersection, new ArrayList<>());
  }

  // Create a road between two intersections
  public void makeRoad(Intersection source, Intersection destination, double length) {
    Road road = new Road();
    adjList.get(source);
  }

  // Reference to the GameEngine for map changes with game logic.
  GameEngine myGameEngine;

}
