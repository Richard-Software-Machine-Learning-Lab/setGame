package client;

import client.view.CardView;

/**
 * Interface for the class representing the view containing the twelve cards.
 */
public interface ITwelveCardsView {

  /**
   * Gets the views of all cards.
   */
  CardView[] getAllCardPanels();
}
