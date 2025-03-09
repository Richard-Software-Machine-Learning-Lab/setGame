package client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import client.gamemanagement.Attempt;
import client.gamemanagement.GameManager;
import client.gamemanagement.Timer;
import client.uimanagement.UIManager;
import client.view.View;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Tests for Game Manager.
 */
public class GameManagerImplementationTests {

  IUIManager uiManager;
  IGameManager gameManager;

  @BeforeEach
  void setup() {
    this.gameManager = new GameManager();
  }

  @Test
  void oncePlayIsCalledChallengeIsFetchedAndUIManagerIsCalledToUpdateViewToShowThisChallenge() {
    IUIManager uiManager = mock(UIManager.class);
    this.gameManager.setUIManager(uiManager);

    IRequestHandler requestHandler = mock(RequestHandler.class);
    this.gameManager.setRequestHandler(requestHandler);
    int[] cardsMock= {0000, 0001, 0002, 2222, 2121, 2221, 2212, 2111, 1211, 1121, 2122, 2211};
    when(requestHandler.fetchChallengeDeck(0)).thenReturn(cardsMock);
    when(requestHandler.fetchSetsIncludedInChallenge(0)).thenReturn(1);
    when(requestHandler.checkSet(any(int[].class), anyInt())).thenReturn(true);

      this.gameManager.play("Richard");

    verify(requestHandler, times(1)).fetchChallengeDeck(0);
    verify(requestHandler, times(1)).fetchSetsIncludedInChallenge(0);
    verify(uiManager, times(1)).updateCardDeck(cardsMock);
  }

  @Test
  void setIsCheckedAndUIManagerIsAskedToShowSetSubmissionFeedbackAndResultsIfAllSetsHaveBeenFound() {
    IUIManager uiManager = mock(UIManager.class);
    this.gameManager.setUIManager(uiManager);

    IRequestHandler requestHandler = mock(RequestHandler.class);
    this.gameManager.setRequestHandler(requestHandler);
    int[] cardsMock= {0000, 0001, 0002, 2222, 2121, 2221, 2212, 2111, 1211, 1121, 2122, 2211};
    int[] setToBeChecked = {0000, 0001, 0002};
    when(requestHandler.fetchChallengeDeck(0)).thenReturn(cardsMock);
    when(requestHandler.fetchSetsIncludedInChallenge(0)).thenReturn(1);
    when(requestHandler.checkSet(setToBeChecked, 0)).thenReturn(true);

      this.gameManager.play("Richard");

    int[] cardIndicesToBeChecked = {0, 1, 2};
    this.gameManager.checkSetBasedOnIndices(cardIndicesToBeChecked);

    verify(requestHandler, times(1)).checkSet(setToBeChecked,0);
    verify(uiManager, times(1)).giveSetSubmissionFeedback("All sets are found.");
    verify(uiManager, times(1)).displayResults(any(), any());
  }

  @Test
  void setIsCheckedAndUIManagerIsAskedToShowSetSubmissionFeedbackButNoResultsIfNotAllSetsHaveBeenFoundYet() {
    IUIManager uiManager = mock(UIManager.class);
    this.gameManager.setUIManager(uiManager);

    IRequestHandler requestHandler = mock(RequestHandler.class);
    this.gameManager.setRequestHandler(requestHandler);
    int[] cardsMock= {0000, 0001, 0002, 2222, 2121, 2221, 2212, 2111, 1211, 1121, 2122, 2211};
    int[] setToBeChecked = {0000, 0001, 0002};
    when(requestHandler.fetchChallengeDeck(0)).thenReturn(cardsMock);
    when(requestHandler.fetchSetsIncludedInChallenge(0)).thenReturn(2);
    when(requestHandler.checkSet(setToBeChecked,0)).thenReturn(true);

      this.gameManager.play("Richard");

    int[] cardIndicesToBeChecked = {0, 1, 2};
    this.gameManager.checkSetBasedOnIndices(cardIndicesToBeChecked);

    verify(requestHandler, times(1)).checkSet(setToBeChecked, 0);
    verify(uiManager, times(1)).giveSetSubmissionFeedback("New set found.");
    verify(uiManager, times(0)).displayResults(any(), any());
  }

  @Test
  void setIsCheckedAndUIManagerIsAskedToShowSetSubmissionFeedbackButNoResultsIfNoSetHasBeenFound() {
    IUIManager uiManager = mock(UIManager.class);
    this.gameManager.setUIManager(uiManager);

    IRequestHandler requestHandler = mock(RequestHandler.class);
    this.gameManager.setRequestHandler(requestHandler);
    int[] cardsMock= {1111, 1112, 1113, 3333, 3232, 3331, 3323, 3222, 1211, 1121, 2122, 2211};
    int[] setToBeChecked = {1111, 1112, 3333};
    when(requestHandler.fetchChallengeDeck(0)).thenReturn(cardsMock);
    when(requestHandler.fetchSetsIncludedInChallenge(0)).thenReturn(1);
    when(requestHandler.checkSet(setToBeChecked, 0)).thenReturn(false);

      this.gameManager.play("Richard");

    int[] cardIndicesToBeChecked = {0, 1, 3};
    this.gameManager.checkSetBasedOnIndices(cardIndicesToBeChecked);

    verify(requestHandler, times(1)).checkSet(setToBeChecked, 0);
    verify(uiManager, times(1)).giveSetSubmissionFeedback("This is no set.");
    verify(uiManager, times(0)).displayResults(any(), any());
  }

  @Test
  void setIsCheckedAndUIManagerIsAskedToShowSetSubmissionFeedbackButNoResultsIfSetHasAlreadyBeenFound() {
    IUIManager uiManager = mock(UIManager.class);
    this.gameManager.setUIManager(uiManager);

    IRequestHandler requestHandler = mock(RequestHandler.class);
    this.gameManager.setRequestHandler(requestHandler);
    int[] cardsMock= {1111, 1112, 1113, 3333, 3232, 3331, 3323, 3222, 1211, 1121, 2122, 2211};
    int[] setToBeChecked = {1111, 1112, 1113};
    when(requestHandler.fetchChallengeDeck(0)).thenReturn(cardsMock);
    when(requestHandler.fetchSetsIncludedInChallenge(0)).thenReturn(2);
    when(requestHandler.checkSet(setToBeChecked, 0)).thenReturn(true).thenReturn(true);

      this.gameManager.play("Richard");

    int[] cardIndicesToBeChecked = {0, 1, 2};
    this.gameManager.checkSetBasedOnIndices(cardIndicesToBeChecked);
    this.gameManager.checkSetBasedOnIndices(cardIndicesToBeChecked);

    verify(requestHandler, times(2)).checkSet(setToBeChecked, 0);
    verify(uiManager, times(1)).giveSetSubmissionFeedback("New set found.");
    verify(uiManager, times(1)).giveSetSubmissionFeedback("Set already found.");
    verify(uiManager, times(0)).displayResults(any(), any());
  }

}
