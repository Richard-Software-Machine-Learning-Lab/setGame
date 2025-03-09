package client.uimanagement;

import client.ICardView;
import client.IGameManager;
import client.IUIManager;
import client.IView;

/**
 * Implementation of the UI Manager interface {@link IUIManager}.
 * The UI manager orchestrates the communication and acts as an interface between view and game manager.
 * It manages the actions triggered by users in the UI. It also triggers changes in the view(s)
 * based on calls from the game manager.
 */
public class UIManager implements IUIManager {

  final static int CARDS_IN_CHALLENGE = 12;

  private IView view;
  private IGameManager gameManager;
  private boolean[] cardsClickStatus;
  private boolean isWarningShown;

  public UIManager() {
    this.cardsClickStatus = new boolean[CARDS_IN_CHALLENGE];
    for(int i = 0; i < CARDS_IN_CHALLENGE; i++) {
      this.cardsClickStatus[i] = false;
    }
  }

  @Override
  public void setView(IView view) {
    this.view = view;
  }

  @Override
  public void setGameManager(IGameManager gameManager) {
    this.gameManager = gameManager;
  }

  @Override
  public void play(String name) {
    this.gameManager.play(name);
  }

  @Override
  public void cardClicked(ICardView card) {
    if(this.isWarningShown) {
      this.view.getChallengePanel().removeSelectionWarning();
      this.isWarningShown = false;
    }

    int cardId = card.getCardId();
    if (this.cardsClickStatus[cardId]) {
      card.showNotClicked();
      this.cardsClickStatus[cardId] = false;
    } else {
      card.showClicked();
      this.cardsClickStatus[cardId] = true;
    }
  }

  @Override
  public void submitSetSelection() {
    int clickedCardsCounter = 0;
    for (int i = 0; i < CARDS_IN_CHALLENGE; i++) {
      if (this.cardsClickStatus[i]) {
        clickedCardsCounter++;
      }
    }

    if (clickedCardsCounter == 3) {
      int[] indexSelectedCards = new int[3];
      int selectedCardsCounter = 0;
      for (int i = 0; i < CARDS_IN_CHALLENGE; i++) {
        if (this.cardsClickStatus[i]) {
          indexSelectedCards[selectedCardsCounter] = i;
          selectedCardsCounter++;
        }
      }
      this.gameManager.checkSetBasedOnIndices(indexSelectedCards);

    } else {
      this.isWarningShown = true;
      this.view.getChallengePanel().showSelectionWarning();
    }
  }

  @Override
  public void chooseNextChallenge() {
    this.gameManager.playNextChallenge();
    this.view.getResultsPanel().emptyLeagueTable();
  }

  @Override
  public void updateCardDeck(int[] cards) {
    for(int i = 0; i < CARDS_IN_CHALLENGE; i++) {
      this.view.getAllCardPanels()[i].addImage(cards[i]);
    }
    this.view.showChallenge();
  }

  @Override
  public void displayResults(String congratsText, String[] leagueTable) {
    this.cleanUpChallengeState();
    this.view.getResultsPanel().setCongratsLabelText(congratsText);
    this.view.getResultsPanel().setNewLeagueTable(leagueTable);
    this.view.showResults();
  }

  @Override
  public void giveSetSubmissionFeedback(String feedback) {
    this.view.getChallengePanel().setStatusLabelText(feedback);
  }

  /**
   * Tidying up the challenge view as well as the card selection state
   * to remove challenge-specific information.
   */
  private void cleanUpChallengeState() {
    for(int i = 0; i < CARDS_IN_CHALLENGE; i++) {
      this.view.getAllCardPanels()[i].showNotClicked();
      this.cardsClickStatus[i] = false;
    }
    this.view.getChallengePanel().setStatusLabelText("");
  }


}