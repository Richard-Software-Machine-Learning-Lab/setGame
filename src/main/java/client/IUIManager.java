package client;

import client.gamemanagement.GameManager;
import client.view.CardView;
import client.view.View;

/**
 * Interface for the UIManager.
 */
public interface IUIManager {

  /**
   * Sets the view.
   */
  void setView(IView view);

  /**
   * Sets the game manager.
   */
  void setGameManager(IGameManager gameManager);

  /**
   * Informs game manager about playing wanting to starting playing.
   */
  void play(String name);

  /**
   * Handling the mouse click on a card. If a set selection warning has been active, it is removed.
   * If the card has been selected, it is changed to 'deselected'.
   * If it has not been selected, it is changed to 'selected'.
   * @param card The card that is clicked.
   */
  void cardClicked(ICardView card);

  /**
   * Handling the button click to submit set selection.
   * If exactly three cards are selected, the selected cards are identified
   * and game manager is called to check the set.
   * If more or less than three cards are selected, a warning is shown to the user.
   */
  void submitSetSelection();

  /**
   * Handling the button click to play the next challenge, calling the game manager
   * to generate the next challenge.
   */
  void chooseNextChallenge();

  /**
   * Updating the card deck in the view. Calling methods in each of the 12 CardViews
   * to display the card images as specified in the passed array.
   * @param cards 12 integers representing different cards
   */
  void updateCardDeck(int[] cards);

  /**
   * Initiates the display of the results. Ensures that passed congrats text
   * as well as league table is displayed.
   */
  void displayResults(String congratsText, String[] leagueTable);

  /**
   * Displays the feedback after a set is submitted,
   * for example that it has already been found or that it is not a set.
   * @param feedback feedback messages to be prompted to user
   */
  void giveSetSubmissionFeedback(String feedback);

}
