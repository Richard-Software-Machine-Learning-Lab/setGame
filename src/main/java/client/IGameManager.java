package client;

/**
 * Interface for the game manager of the Set Game client. The game manager ensures the game flow.
 */
public interface IGameManager {

    /**
     * Sets request handler.
     */
    void setRequestHandler(IRequestHandler requestHandler);

    /**
     * Sets current attempt.
     */
    void setCurrentAttempt(IAttempt attempt);

    /**
     * A player starts the game by providing her/his name.
     */
    void play(String name);

    /**
     * Starts the next challenge by creating an attempt for retrieving and handling attempt of specific challenge
     * and asks UI manager to update UI with next challenge.
     */
    void playNextChallenge();

    /**
     * Orchestrates game flow after three cards are submitted.
     * Calls challenge attempt to check passed set candidate and to get
     * check set status, and calls UI manager to display this status.
     * If set leads to completion of challenge attempt, challenge attempt completion is initiated.
     */
    void checkSetBasedOnIndices(int[] cardSetIndices);

    /**
     * Sets the UI manager.
     */
    void setUIManager(IUIManager uiManager);

}
