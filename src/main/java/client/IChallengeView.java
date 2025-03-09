package client;

/**
 * Interface for the class representing the view of a challenge (challenge page).
 */
import client.view.TwelveCardsView;

/**
 * Interface for the class representing the view of the current challenge.
 */
public interface IChallengeView {

  /**
   * Show a warning that only a selection of three cards can be submitted.
   */
  void showSelectionWarning();

  /**
   * Remove the warning that only a selection of three cards can be submitted.
   */
  void removeSelectionWarning();

  /**
   * Prints passed status notification to give feedback about submission of three cards (set candidate).
   * @param notification Notification to be printed
   */
  void setStatusLabelText(String notification);

  /**
   * Gets the twelve cards view.
   */
  TwelveCardsView getTwelveCardsPanel();
}
