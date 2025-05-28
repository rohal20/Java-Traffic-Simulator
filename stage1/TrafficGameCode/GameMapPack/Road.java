package GameMapPack;

import java.util.ArrayList;
import java.util.Map;

// Represents a road in the game map, connecting intersections.
public class Road {

  // ArrayList to store lanes on this road.
  public ArrayList<Lanes> lanes = new ArrayList<Lanes>();

  // References to the source and destination intersections connected by this road.
  Intersection source;
  Intersection destination;

  // Length of the road.
  double length;

  // Reference to the game map this road belongs to.
  public Map myMap;

  // Reference to the lanes on this road.
  public Lanes myLanes;

}