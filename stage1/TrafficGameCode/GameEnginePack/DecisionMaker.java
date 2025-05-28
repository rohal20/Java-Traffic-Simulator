package GameEnginePack;

public interface DecisionMaker {
// for making decisions at intersections
  public void intersectionDecision();
// for making lane change decisions
  public void laneDecision();
// for making decisions during challenges with other players or AI
  public void challengeDecision();
//for making decisions on whether to gamble within the game context
  public void gambleDecision();

}