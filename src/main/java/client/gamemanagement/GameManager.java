package client.gamemanagement;
import client.IAttempt;
import client.IGameManager;
import client.IRequestHandler;
import client.IUIManager;
import client.RequestHandler;

/**
 * Implementation of a {@link IGameManager}. The game manager ensures the game flow
 * and could be seen as extension of the model on the client-side, maintaining client-
 * session specific game state.
 */
public class GameManager implements IGameManager {

    private int currentChallengeId = -1;
    private IAttempt currentAttempt;
    private String playerName;
    private IUIManager uiManager;
    private IRequestHandler requestHandler;

    public GameManager(){
        this.setCurrentChallengeId(-1);
        this.setRequestHandler(new RequestHandler());
    }

    @Override
    public void setRequestHandler(IRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public void setUIManager(IUIManager uiManager) {
        this.uiManager = uiManager;
    }

    @Override
    public void setCurrentAttempt(IAttempt attempt) {
        this.currentAttempt = attempt;
    }

    @Override
    public void play(String name) {
        this.playerName = name;
        this.playNextChallenge();
    }

    @Override
    public void playNextChallenge() {
        this.incrementChallengeCounter();
        this.setCurrentAttempt(new Attempt());
        this.currentAttempt.setRequestHandler(this.requestHandler);
        this.currentAttempt.setChallengeId(this.currentChallengeId);
        this.currentAttempt.fetchChallenge();
        this.currentAttempt.startAttemptTimer();
        this.uiManager.updateCardDeck(this.currentAttempt.getChallengeDeck());
    }

    @Override
    public void checkSetBasedOnIndices(int[] cardSetIndices) {
        String feedback = this.currentAttempt.checkSet(cardSetIndices);
        this.uiManager.giveSetSubmissionFeedback(feedback);
        if(this.currentAttempt.isSolved()) {
            generateChallengeResult();
        }
    }

    /**
     * Completes the challenge attempt by generating the challenge result and asking UI manager to display it.
     */
    private void generateChallengeResult() {
        this.currentAttempt.addAttemptToLeaderboard(this.playerName);
        String[] leaderboard = this.currentAttempt.getLeaderboard();
        long endTime = this.currentAttempt.getFinalTime();
        String congratsText = "Congrats " + this.playerName + "! You are brilliant! Your Time was " + endTime;
        this.uiManager.displayResults(congratsText, leaderboard);
    }

    /**
     * Sets the id of the current challenge.
     */
    private void setCurrentChallengeId(int id) {
        this.currentChallengeId = id;
    }

    /**
     * Increments the id of the currently active challenge.
     */
    private void incrementChallengeCounter() {
        this.currentChallengeId++;
    }
}
