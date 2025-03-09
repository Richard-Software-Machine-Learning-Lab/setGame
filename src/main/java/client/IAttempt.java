package client;

public interface IAttempt {

  /**
   * Sets request handler.
   */
  void setRequestHandler(IRequestHandler requestHandler);

  /**
   * Sets challenge id.
   */
  void setChallengeId(int challengeId);

  /**
   * Fetches a challenge based on an id and stores the challenge card deck.
   */
  void fetchChallenge();

  /**
   * Returns the challenge card deck.
   * @return integer representations of 12 cards
   */
  int[] getChallengeDeck();

  /**
   * Sets the challenge card deck.
   * @param cards integer representation of 12 cards
   */
  void setChallengeDeck(int[] cards);

  /**
   * Set timer.
   */
  void setTimer(ITimer timer);

  /**
   * Get timer.
   */
  ITimer getTimer();

  /**
   * Returns whether challenge has been solved by the player.
   */
  boolean isSolved();

  /**
   * Sets final time the player needed to solve the challenge.
   * @param time TODO Time Format
   */
  void setFinalTime(long time);

  /**
   * Gets final time the player needed to solve the challenge.
   */
  long getFinalTime();

  /**
   * Starting timer of attempt.
   */
  void startAttemptTimer();

  /**
   * Initiates that time to complete challenge is added to the leaderboard.
   * It is called with the name of the player.
   */
  void addAttemptToLeaderboard(String playerName);

  /**
   * Initiates request that leaderboard for the challenge of the attempt and returns the leaderboard.
   */
  String[] getLeaderboard();

  /**
   * Checks if selected three cards are a set. Returns status based on that:
   * 1) It is a set: a) newly identified set b) already found set
   * 2) It is not a set
   * If the last set of a challenge is found and thus all sets of the
   * challenge are found, challenge is set to 'solved'.
   * @param cardIndices Indices of selected three cards
   */
  String checkSet(int[] cardIndices);

}
