package client;

/**
 * Interface for request handler. A request handler handles all requests to the server.
 */
public interface IRequestHandler {

  /**
   * Sends request to check whether thrre cards are a set.
   * @param cards integer representation of three cards
   * @return true if three cards are a set, otherwise false.
   */
  boolean checkSet(int[] cards, int challengeId);

  /**
   * Sends request to fetch challenge card deck for specified challenge.
   * @param challengeId id of challenge to be fetched
   * @return 12 cards of challenge in integer representation
   */
  int[] fetchChallengeDeck(int challengeId);

  /**
   * Sends request to fetch number of sets included for specified challenge.
   * @param challengeId id of challenge for which no of sets included is to be fetched
   * @return number of sets included in specified challenge
   */
  int fetchSetsIncludedInChallenge(int challengeId);

  /**
   * Sends request to fetch the LeagueTable for a specified challenge.
   * @param challengeId id of challenge to be fetched.
   * @return LeagueTable in the form of a String array.
   */
  String[] getLeagueTableForChallenge(int challengeId);

  /**
   * Sends requests to update public LeagueTable for a specified challenge.
   * @param challengeId id of challenge to be fetched.
   */
  void updateLeagueTableForChallenge(int challengeId, String name, long time);
}
