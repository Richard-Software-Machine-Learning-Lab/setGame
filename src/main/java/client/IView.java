package client;

import client.uimanagement.UIManager;
import client.view.CardView;
import client.view.ChallengeView;
import client.view.ResultsView;

/**
 * Interface for the main view class.
 */
public interface IView {

  /**
   * Set the UI manager.
   */
  void setUIManager(UIManager uiManager);

  /**
   * Creates the view by initiating the creation of child Swing elements.
   */
  void createView();

  /**
   * Gets the challenge view.
   */
  ChallengeView getChallengePanel();

  /**
   * Gets the results view.
   */
  ResultsView getResultsPanel();

  /**
   * Gets all 12 card views.
   */
  CardView[] getAllCardPanels();

  /**
   * Switch to challenge view.
   */
  void showChallenge();

  /**
   * Switch to results view.
   */
  void showResults();
}
