package client.view;

import client.ITwelveCardsView;
import client.uimanagement.UIManager;

import javax.swing.*;
import java.awt.*;

/**
 * Class for the view of the twelve cards as part of the challenge view, implements {@link ITwelveCardsView}.
 */
public class TwelveCardsView extends JPanel implements ITwelveCardsView {

  private UIManager controller;

  CardView card0;
  CardView card1;
  CardView card2;
  CardView card3;
  CardView card4;
  CardView card5;
  CardView card6;
  CardView card7;
  CardView card8;
  CardView card9;
  CardView card10;
  CardView card11;

  /**
   * Constructs twelve cards view by adding card components to it.
   */
  public TwelveCardsView(UIManager controller) {
    this.controller = controller;

    GridLayout layout = new GridLayout(3,4);
    setLayout(layout);

    this.card0 = new CardView(this.controller, this);
    card0.setCardId(0);
    add(card0);

    this.card1 = new CardView(this.controller, this);
    this.card1.setCardId(1);
    add(this.card1);

    this.card2 = new CardView(this.controller, this);
    this.card2.setCardId(2);
    add(this.card2);

    this.card3 = new CardView(this.controller, this);
    this.card3.setCardId(3);
    add(this.card3);

    this.card4 = new CardView(this.controller, this);
    this.card4.setCardId(4);
    add(this.card4);

    this.card5 = new CardView(this.controller, this);
    this.card5.setCardId(5);
    add(this.card5);

    this.card6 = new CardView(this.controller, this);
    this.card6.setCardId(6);
    add(this.card6);

    this.card7 = new CardView(this.controller, this);
    this.card7.setCardId(7);
    add(this.card7);

    this.card8 = new CardView(this.controller, this);
    this.card8.setCardId(8);
    add(this.card8);

    this.card9 = new CardView(this.controller, this);
    this.card9.setCardId(9);
    add(this.card9);

    this.card10 = new CardView(this.controller, this);
    this.card10.setCardId(10);
    add(this.card10);

    this.card11 = new CardView(this.controller, this);
    this.card11.setCardId(11);
    add(this.card11);
  }

  @Override
  public CardView[] getAllCardPanels() {
    CardView[] cardViews = new CardView[12];
    cardViews[0] = this.card0;
    cardViews[1] = this.card1;
    cardViews[2] = this.card2;
    cardViews[3] = this.card3;
    cardViews[4] = this.card4;
    cardViews[5] = this.card5;
    cardViews[6] = this.card6;
    cardViews[7] = this.card7;
    cardViews[8] = this.card8;
    cardViews[9] = this.card9;
    cardViews[10] = this.card10;
    cardViews[11] = this.card11;
    return cardViews;
  }


}