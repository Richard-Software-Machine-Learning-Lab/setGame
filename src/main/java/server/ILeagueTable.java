package server;

import java.util.Hashtable;

/**
 * Interface for the league table of challenges.
 */
public interface ILeagueTable {
    /**
     * Method to add a players score after completing a challenge to the associated league table.
     * @param
     */
    void addToLeagueTable(String name, long time);

    /**
     * Method to get the current league table of a challenge.
     * @return list of the scores in the league table.
     */
    Hashtable<String, Long> getLeagueTable();


}
