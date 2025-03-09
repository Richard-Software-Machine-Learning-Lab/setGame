package client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import client.gamemanagement.Attempt;
import client.gamemanagement.Timer;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests for Attempt.
 */
public class ClientAttemptImplementationTests {

  IAttempt attempt;

  @BeforeEach
  void setup() {
    this.attempt = new Attempt();
  }

  @Test
  void timerIsCorrectlySetAndReturned() {
    Timer timer = new Timer();
    this.attempt.setTimer(timer);
    assertTrue(this.attempt.getTimer().equals(timer));
  }

  @Test
  void checkSetReturnsNewSetStringIfSetIsGivenAndHasBeenFoundFirstTime() {
    //create mock request handler
    IRequestHandler requestHandler = mock(RequestHandler.class);
    when(requestHandler.checkSet(any(int[].class), anyInt())).thenReturn(true);

    this.attempt.setRequestHandler(requestHandler);

    int[] cardsMock= {0000, 0001, 0002, 2222, 2121, 2221, 2212, 2111, 1211, 1121, 2122, 2211};
    this.attempt.setChallengeDeck(cardsMock);

    int[] cardIndices = {0, 1, 2};

    assertEquals("New set found.", this.attempt.checkSet(cardIndices));
  }


  @Test
  void checkSetReturnsAlreadyFoundStringIfSetHasAlreadyBeenFoundBefore() {
    //create mock request handler
    IRequestHandler requestHandler = mock(RequestHandler.class);
    when(requestHandler.checkSet(any(int[].class), anyInt())).thenReturn(true);

    this.attempt.setRequestHandler(requestHandler);

    int[] cardsMock= {0000, 0001, 0002, 2222, 2121, 2221, 2212, 2111, 1211, 1121, 2122, 2211};
    this.attempt.setChallengeDeck(cardsMock);

    int[] cardIndices = {0, 1, 2};

    this.attempt.checkSet(cardIndices);
    assertEquals("Set already found.", this.attempt.checkSet(cardIndices));
  }

  @Test
  void checkSetReturnsAllFoundStringIfSetIsGivenAndAllSetsAreFound() {
    //create mock request handler
    IRequestHandler requestHandler = mock(RequestHandler.class);
    when(requestHandler.fetchSetsIncludedInChallenge(0)).thenReturn(1);
    when(requestHandler.checkSet(any(int[].class), anyInt())).thenReturn(true);

    this.attempt.setRequestHandler(requestHandler);
    this.attempt.setChallengeId(0);
    this.attempt.fetchChallenge();

    int[] cardsMock= {0000, 0001, 0002, 2222, 2121, 2221, 2212, 2111, 1211, 1121, 2122, 2211};
    this.attempt.setChallengeDeck(cardsMock);

    int[] cardIndices = {0, 1, 2};

    assertEquals("All sets are found.", this.attempt.checkSet(cardIndices));
  }

  @Test
  void checkSetReturnsNoSetStringIfSetIsNotGiven() {
    //create mock request handler
    IRequestHandler requestHandler = mock(RequestHandler.class);
    when(requestHandler.checkSet(any(int[].class), anyInt())).thenReturn(false);

    this.attempt.setRequestHandler(requestHandler);

    int[] cardsMock= {0000, 0001, 0002, 2222, 2121, 2221, 2212, 2111, 1211, 1121, 2122, 2211};
    this.attempt.setChallengeDeck(cardsMock);

    int[] cardIndices = {0, 1, 3};

    assertEquals("This is no set.", this.attempt.checkSet(cardIndices));
  }

  @Test
  void isSolvedReturnsFalseBeforeSetIsFoundAndTrueAfter() {
    //create mock request handler
    IRequestHandler requestHandler = mock(RequestHandler.class);
    when(requestHandler.fetchSetsIncludedInChallenge(0)).thenReturn(1);
    when(requestHandler.checkSet(any(int[].class), anyInt())).thenReturn(true);

    this.attempt.setRequestHandler(requestHandler);
    this.attempt.setChallengeId(0);
    this.attempt.fetchChallenge();

    int[] cardsMock= {0000, 0001, 0002, 2222, 2121, 2221, 2212, 2111, 1211, 1121, 2122, 2211};
    this.attempt.setChallengeDeck(cardsMock);

    int[] cardIndices = {0, 1, 2};

    assertFalse(this.attempt.isSolved());
    assertEquals("All sets are found.", this.attempt.checkSet(cardIndices));
    assertTrue(this.attempt.isSolved());
  }

  @Test
  void timerIsStartedOnlyWhenAttemptIsStarted() {
    assertFalse(this.attempt.getTimer().isRunning());
    this.attempt.startAttemptTimer();
    assertTrue(this.attempt.getTimer().isRunning());
  }

  @Test
  void timerIsStoppedOnlyWhenAllSetsAreFound() {
    //create mock request handler
    IRequestHandler requestHandler = mock(RequestHandler.class);
    when(requestHandler.fetchSetsIncludedInChallenge(0)).thenReturn(1);
    when(requestHandler.checkSet(any(int[].class), anyInt())).thenReturn(true);

    this.attempt.setRequestHandler(requestHandler);
    this.attempt.setChallengeId(0);
    this.attempt.fetchChallenge();

    int[] cardsMock= {0000, 0001, 0002, 2222, 2121, 2221, 2212, 2111, 1211, 1121, 2122, 2211};
    this.attempt.setChallengeDeck(cardsMock);

    int[] cardIndices = {0, 1, 2};

    this.attempt.startAttemptTimer();
    assertTrue(this.attempt.getTimer().isRunning());
    this.attempt.checkSet(cardIndices);
    assertFalse(this.attempt.getTimer().isRunning());
  }

  @Test
  void finalTimeIsSetAndCorrectlyReturnedAfterAllSetsAreFound() {
    //create mock request handler
    IRequestHandler requestHandler = mock(RequestHandler.class);
    when(requestHandler.fetchSetsIncludedInChallenge(0)).thenReturn(1);
    when(requestHandler.checkSet(any(int[].class), anyInt())).thenReturn(true);

    this.attempt.setRequestHandler(requestHandler);
    this.attempt.setChallengeId(0);
    this.attempt.fetchChallenge();

    int[] cardsMock= {0000, 0001, 0002, 2222, 2121, 2221, 2212, 2111, 1211, 1121, 2122, 2211};
    this.attempt.setChallengeDeck(cardsMock);

    int[] cardIndices = {0, 1, 2};

    this.attempt.startAttemptTimer();

    assertTrue(this.attempt.getFinalTime() == -1L);
    this.attempt.checkSet(cardIndices);
    assertTrue(this.attempt.getFinalTime() >= 0);
  }

  @Test
  void finalTimeIsCorrectlySetAndReturned() {
    long time = 10L;
    this.attempt.setFinalTime(time);
    assertTrue(this.attempt.getFinalTime() == time);
  }

  @Test
  void challengeDeckIsCorrectlySetAndReturned() {
    int[] cardsMock= {0000, 0001, 0002, 2222, 2121, 2221, 2212, 2111, 1211, 1121, 2122, 2211};
    this.attempt.setChallengeDeck(cardsMock);
    assertTrue(this.attempt.getChallengeDeck().equals(cardsMock));
  }

  @Test
  void challengeIdIsCorrectlySetAndRequestHandlerIsCalledWithCorrectChallengeId() {
    IRequestHandler requestHandler = mock(RequestHandler.class);
    this.attempt.setRequestHandler(requestHandler);
    this.attempt.setChallengeId(1);
    this.attempt.fetchChallenge();

    verify(requestHandler, times(1)).fetchChallengeDeck(1);
    verify(requestHandler, times(1)).fetchSetsIncludedInChallenge(1);
  }

  @Test
  void attemptIsCorrectlyAddedToTheLeaderboard() {
    IRequestHandler requestHandler = mock(RequestHandler.class);
    this.attempt.setRequestHandler(requestHandler);
    this.attempt.setChallengeId(1);
    this.attempt.setFinalTime(100L);
    this.attempt.addAttemptToLeaderboard("Mary");

    verify(requestHandler, times(1)).updateLeagueTableForChallenge(1,"Mary", 100L);
  }

  @Test
  void leaderbordIsReturned() {
    IRequestHandler requestHandler = mock(RequestHandler.class);
    this.attempt.setRequestHandler(requestHandler);
    String[] leaderboard = new String[2];
    leaderboard[0] = "Frank";
    leaderboard[1] = "13482";
    when(requestHandler.getLeagueTableForChallenge(0)).thenReturn(leaderboard);

    assertArrayEquals(this.attempt.getLeaderboard(), leaderboard);
  }

}
