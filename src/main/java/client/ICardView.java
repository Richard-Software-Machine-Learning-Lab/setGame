package client;

/**
 * Interface for the class representing the view of a single playing card.
 */
public interface ICardView {

  /**
   * Adding an id to a card view.
   */
  void setCardId(int cardId);

  /**
   * Getting the card view id.
   */
  int getCardId();

  /**
   * Adding an image to a card view based on image number, identifying an image in the resources folder.S
   */
  void addImage(int imageNumber);

  /**
   * Show a card as selected.
   */
  void showClicked();

  /**
   * Show a card as unselected.
   */
  void showNotClicked();
}
