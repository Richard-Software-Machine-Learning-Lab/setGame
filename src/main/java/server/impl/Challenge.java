package server.impl;

import server.ICard;
import server.IChallenge;
import server.ILeagueTable;

import java.util.ArrayList;

/**
 * Implementation of a {@link IChallenge}.
 */
public class Challenge implements IChallenge {

  final int NUMBER_OF_CARDS = 12;

  ArrayList<ICard> cards;
  int setsIncluded;
  ILeagueTable leagueTable;
  int challengeId;

  @Override
  public void setChallengeCardDeck(ArrayList<ICard> cards, int setsIncluded) {
    if (cards.size() != NUMBER_OF_CARDS) {
      throw new IllegalArgumentException("The challenge card deck need to consist of exactly 12 cards.");
    } else {
      if (setsIncluded < 1) {
        throw new IllegalArgumentException("Deck must have at least one set.");
      } else {
        this.setLeagueTable(new LeagueTable());
        this.cards = cards;
        this.setsIncluded = setsIncluded;
      }
    }
  }

  @Override
  public ArrayList<ICard> getChallengeCardDeck() {
    return this.cards;
  }

  @Override
  public void setLeagueTable(ILeagueTable leagueTable) {
    this.leagueTable = leagueTable;
  }

  @Override
  public ILeagueTable getLeagueTable() {
    return this.leagueTable;
  }

  @Override
  public void setChallengeId(int id) {
    this.challengeId = id;
  }

  @Override
  public int getChallengeId() {
    return this.challengeId;
  }

  @Override
  public int getNumberOfSetsIncluded() {
    return this.setsIncluded;
  }

  @Override
  public boolean checkSet(ArrayList<ICard> cards) {
    if (cards.size() != 3) {
      throw new IllegalArgumentException("Passed set does not consist of exactly three cards");
    }
    return (checkShapeFeature(cards) && checkColorFeature(cards)
        && checkLineTypeFeature(cards) && checkNumberOfShapesFeature(cards));
  }

  /**
   * Checks whether shapes of all three cards are all same or all different.
   * @param cards Three cards of set
   * @return true if shapes of of all three cards are either all same or all different, otherwise false
   */
  private boolean checkShapeFeature(ArrayList<ICard> cards) {
    int shapeCardOne = cards.get(0).getShape();
    int shapeCardTwo = cards.get(1).getShape();
    int shapeCardThree = cards.get(2).getShape();
    return areAllFeaturesSameOrAllDifferent(shapeCardOne, shapeCardTwo, shapeCardThree);
  }

  /**
   * Checks whether color of all three cards are all same or all different.
   * @param cards Three cards of set
   * @return true if colors of of all three cards are either all same or all different, otherwise false
   */
  private boolean checkColorFeature(ArrayList<ICard> cards) {
    int colorCardOne = cards.get(0).getColour();
    int colorCardTwo = cards.get(1).getColour();
    int colorCardThree = cards.get(2).getColour();
    return areAllFeaturesSameOrAllDifferent(colorCardOne, colorCardTwo, colorCardThree);
  }

  /**
   * Checks whether line type of all three cards are all same or all different.
   * @param cards Three cards of set
   * @return true if line type of of all three cards are either all same or all different, otherwise false
   */
  private boolean checkLineTypeFeature(ArrayList<ICard> cards) {
    int lineTypeCardOne = cards.get(0).getLineType();
    int lineTypeCardTwo = cards.get(1).getLineType();
    int lineTypeCardThree = cards.get(2).getLineType();
    return areAllFeaturesSameOrAllDifferent(lineTypeCardOne, lineTypeCardTwo, lineTypeCardThree);
  }

  /**
   * Checks whether number of shapes of all three cards are all same or all different.
   * @param cards Three cards of set
   * @return true if number of shapes of of all three cards are either all same or all different, otherwise false
   */
  private boolean checkNumberOfShapesFeature(ArrayList<ICard> cards) {
    int numberShapesCardOne = cards.get(0).getNumberOfShapes();
    int numberShapesCardTwo = cards.get(1).getNumberOfShapes();
    int numberShapesCardThree = cards.get(2).getNumberOfShapes();
    return areAllFeaturesSameOrAllDifferent(numberShapesCardOne, numberShapesCardTwo, numberShapesCardThree);
  }

  /**
   * Checks whether passed feature instances are all same/all different or not.
   * @param featureInstanceA first feature instance
   * @param featureInstanceB second feature instance
   * @param featureInstanceC third feature instance
   * @return true if passed feature instances are all same/all different, otherwise false
   */
  private boolean areAllFeaturesSameOrAllDifferent(int featureInstanceA, int featureInstanceB, int featureInstanceC) {
    // Are feature instances all the same?
    return((featureInstanceA == (featureInstanceB) && featureInstanceB == (featureInstanceC))
        // Are feature instances all different?
        || (!(featureInstanceA == (featureInstanceB)) && !(featureInstanceB == (featureInstanceC)) && !(featureInstanceC == (featureInstanceA))));
  }
}
