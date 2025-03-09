package server.impl;

import server.ILeagueTable;

import java.util.Hashtable;

/**
 * Implementation of a {@link ILeagueTable}.
 */
public class LeagueTable implements ILeagueTable {
    Hashtable<String, Long> leagueTable = new Hashtable<String, Long>();

    /**
     * Method to add a players score after completing a challenge to the associated league table.
     */
    @Override
    public void addToLeagueTable(String name, long time) {
        leagueTable.put(name, time);
    }

    /**
     * Method to get the current league table of a challenge.
     * @return list of the scores in the league table.
     */
    @Override
    public Hashtable<String, Long> getLeagueTable() {
        return leagueTable;
    }
}
