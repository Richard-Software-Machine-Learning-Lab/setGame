package server;

import java.util.ArrayList;

/**
 * Interface for a challenge. A challenge contains 12 cards, the player is supposed to find all the sets in them.
 */
public interface IChallenge {

  /**
   * Set challenge card deck.
   * @param cards 12 cards of challenge
   * @param setsIncluded Number of sets included in 12 cards of challenge
   * @throws IllegalArgumentException if more or less than 12 cards are passed or no sets are included
   */
  void setChallengeCardDeck(ArrayList<ICard> cards, int setsIncluded) throws IllegalArgumentException;

  /**
   * Get the challenge card deck.
   */
  ArrayList<ICard> getChallengeCardDeck();

  /**
   * Get the league table of challenge.
   */
  void setLeagueTable(ILeagueTable leagueTable);

  /**
   * Get the league table of challenge.
   */
  ILeagueTable getLeagueTable();

  /**
   * Set challenge id.
   */
  void setChallengeId(int id);

  /**
   * Get id of challenge
   */
  int getChallengeId();

  /**
   * Get number of sets included in challenge.
   */
  int getNumberOfSetsIncluded();

  /**
   * Checks if set.
   * @param cards three cards to check if they are a set
   * @return true if passed cards are a set, otherwise false
   * @throws IllegalArgumentException if not exactly three cards are passed
   */
  boolean checkSet(ArrayList<ICard> cards) throws IllegalArgumentException;
}
