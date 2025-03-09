package client.view;

import client.ICardView;
import client.uimanagement.UIManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class for the view of one card, implements {@link ICardView}.
 * Each card is part of the TwelveCardsView.
 */
public class CardView extends JLabel implements ICardView {

  private UIManager controller;
  private CardView card;
  private int cardId;
  private TwelveCardsView parent;

  /**
   * Constructs view of a single card.
   */
  public CardView(UIManager controller, TwelveCardsView parent) {
    this.card = this;
    this.controller = controller;
    this.parent = parent;
    this.showNotClicked();
    this.addMouseListener();
  }

  @Override
  public void setCardId(int cardId) {
    this.cardId = cardId;
  }

  @Override
  public int getCardId() {
    return this.cardId;
  }

  @Override
  public void addImage(int imageNumber) {
    String imagePath = Integer.toString(imageNumber);
    imagePath = "/" + imagePath + ".PNG";
    ImageIcon image = new ImageIcon(this.getClass().getResource(imagePath));
    setIcon(image);
  }

  @Override
  public void showClicked() {
    Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
    this.setBorder(border);
  }

  @Override
  public void showNotClicked() {
    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    this.setBorder(border);
  }

  /**
   * Adding mouse listener, so that card click is registered.
   */
  public void addMouseListener() {

    addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
      }

      @Override
      public void mousePressed(MouseEvent e) {
        controller.cardClicked(card);
      }

      @Override
      public void mouseReleased(MouseEvent e) {
      }

      @Override
      public void mouseEntered(MouseEvent e) {
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }
    });
  }

}
