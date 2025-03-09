package server;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import server.IChallenge;
import server.ILeagueTable;

/**
 * Interface for the model of the Set Game.
 */
public interface IModelServer {
    /**
     * The set of 81 cards has been initialised.
     */
    boolean hasBeenInitialize();
    /***
     *  Get the number of challenges.
     */
    int getTotalNumberOfChallenges();
    /**
     * Checks if the choice is a set.
     */
    boolean checkSet(ArrayList<Integer> cardsValues, int challengeId);
    /**
     * Get challenge with the twelve cards.
     */
    ArrayList<Integer> challengeDeck(int challengeId);
    /**
     * Obtain the challenge given the challenge identification number.
     */
    IChallenge getChallenge(int challengeId);

    /**
     * Return all the number of sets to find for a challenge.
     */
    int getNumberOfSetsInChallenge(int challengeId);

    /**
     * Sent the league table given the challenge identification number.
     */
    ILeagueTable getLeagueTable(int challengeId);

    /**
     * Update public league table with client info for a particular challenge.
     */
    void updateLeagueTable(int challengeId, String name, long time);

}
