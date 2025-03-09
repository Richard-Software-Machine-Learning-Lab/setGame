package client;

/**
 * Interface for the class representing the view of the challenge results.
 */
public interface IResultsView {

  /**
   * Set the congrats text of the results view.
   * @param notification text to be displayed
   */
  void setCongratsLabelText(String notification);

  /**
   * Sets a new league table.
   * @param leagueTable to be displayed
   */
  void setNewLeagueTable(String[] leagueTable);

  /**
   * Removes all entries from the league table.
   */
  void emptyLeagueTable();
}
