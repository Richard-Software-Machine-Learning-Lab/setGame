package client;

/**
 * Interface for the class representing the view of a league table.
 */
public interface ILeagueTableView {

  /**
   * Adds entries to the league table.
   * @param leagueTableEntries in alternating order, the name and the corresponding time.
   */
  void addLeagueTableEntries(String[] leagueTableEntries);

  /**
   * Removes all table entries from the league table.
   */
  void removeLeagueTableEntries();

}
