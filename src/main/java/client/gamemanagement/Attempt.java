package client.gamemanagement;

import client.IAttempt;
import client.IRequestHandler;
import client.ITimer;

import java.util.ArrayList;

/**
 * Implementation of a {@link IAttempt}.
 */
public class Attempt implements IAttempt {

  final int NUMBER_OF_CARDS_IN_SET = 3;

  ITimer timer;
  boolean isSolved;
  long finalTime = -1L;
  int[] cards;
  ArrayList<int[]> foundSets;
  int setsIncluded;
  IRequestHandler requestHandler;
  int challengeId;

  public Attempt() {
    this.foundSets = new ArrayList<int[]>();
    this.setTimer(new Timer());
  }

  @Override
  public void setRequestHandler(IRequestHandler requestHandler) {
    this.requestHandler = requestHandler;
  }

  @Override
  public void setChallengeId(int challengeId) {
    this.challengeId = challengeId;
  }

  @Override
  public void fetchChallenge() {
    int[] cardDeck = this.requestHandler.fetchChallengeDeck(this.challengeId);
    setChallengeDeck(cardDeck);
    this.setsIncluded = this.requestHandler.fetchSetsIncludedInChallenge(this.challengeId);
  }

  @Override
  public int[] getChallengeDeck() {
    return cards;
  }

  @Override
  public void setChallengeDeck(int[] cards) {
    this.cards = cards;
  }

  @Override
  public void setTimer(ITimer timer) {
    this.timer = timer;
  }

  @Override
  public ITimer getTimer() {
    return this.timer;
  }

  @Override
  public boolean isSolved() {
    return this.isSolved;
  }

  @Override
  public void setFinalTime(long time) {
    this.finalTime = time;
  }

  @Override
  public long getFinalTime() {
    return this.finalTime;
  }

  @Override
  public void startAttemptTimer() {
    this.timer.startTimer();
  }

  @Override
  public void addAttemptToLeaderboard(String playerName) {
    this.requestHandler.updateLeagueTableForChallenge(this.challengeId, playerName, this.getFinalTime());
  }

  @Override
  public String[] getLeaderboard() {
    return this.requestHandler.getLeagueTableForChallenge(this.challengeId);
  }

  @Override
  public String checkSet(int[] cardIndices) {
    String returnStatus = "";
    int[] cardRepresentation = getCardRepresentationFromIndices(cardIndices);

    if (this.requestHandler.checkSet(cardRepresentation, challengeId)) {
      if (!isAlreadyFound(cardIndices)) {
        this.foundSets.add(cardIndices);
        if (this.setsIncluded == this.foundSets.size()) {
          endAttempt();
          returnStatus = "All sets are found.";
        } else {
          returnStatus = "New set found.";
        }
      } else {
        returnStatus = "Set already found.";
      }
    } else {
      returnStatus =  "This is no set.";
    }
    return returnStatus;
  }

  /**
   * Ending attempt by setting status to "Challenge is solved", by stopping the timer
   * and by setting final time.
   */
  private void endAttempt() {
    this.isSolved = true;
    this.timer.stopTimer();
    this.setFinalTime(this.timer.getElapsedTime());
  }

  /**
   * Gets the integer representation of cards for a set based on indices.
   * @param cardIndices indices of three cards
   * @return integer representation of three cards
   */
  private int[] getCardRepresentationFromIndices(int[] cardIndices) {
    int[] cardRepresentation = new int[3];
    for (int i = 0; i < this.NUMBER_OF_CARDS_IN_SET; i++) {
      int cardIndex = cardIndices[i];
      cardRepresentation[i] = getChallengeDeck()[cardIndex];
    }
    return cardRepresentation;
  }

  /**
   * Returns whether set of 3 cards has already been found.
   * @param setToBeChecked Indices of three cards of set
   * @return true if passed set has already been found, otherwise false
   */
  private boolean isAlreadyFound(int[] setToBeChecked) {
    boolean alreadyFound = false;
    for(int[] set : this.foundSets) {
      if (setsAreEqual(set, setToBeChecked)) {
        alreadyFound = true;
        break;
      }
    }
    return alreadyFound;
  }

  /**
   * Returns whether two sets of 3 cards each are equal (containing exactly the same 3 cards).
   * @param set1 3 cards of first set
   * @param set2 3 cards of second set
   * @return true if two sets are equal, otherwise false
   */
  private boolean setsAreEqual(int[] set1, int[] set2) {
    int foundCounter = 0;
    for (int i = 0; i < this.NUMBER_OF_CARDS_IN_SET; i++) {
      for (int j = 0; j < this.NUMBER_OF_CARDS_IN_SET; j++) {
        if (set1[i] == set2[j]) {
          foundCounter++;
          break;
        }
      }
    }
    return (foundCounter == this.NUMBER_OF_CARDS_IN_SET);
  }

}
