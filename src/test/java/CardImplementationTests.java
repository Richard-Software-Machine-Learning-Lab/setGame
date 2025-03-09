import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import client.IRequestHandler;
import client.RequestHandler;
import server.ICard;
import server.impl.Card;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for Card.
 */
public class CardImplementationTests {
  ICard card;

  @BeforeEach
  public void setup() {
    int colour = 1;
    int shape = 2;
    int line = 3;
    int number = 2;
    this.card = new Card();
    this.card.setShape(shape);
    this.card.setLineType(line);
    this.card.setColour(colour);
    this.card.setNumberOfShapes(number);
  }


  @Test
  public void cardColourCorrectlyReturned() {
    Assertions.assertEquals(this.card.getColour(), 1);
    Assertions.assertNotEquals(this.card.getColour(), 2);
  }


  @Test
  public void cardShapeCorrectlyReturned() {
    Assertions.assertEquals(this.card.getShape(), 2);
    Assertions.assertNotEquals(this.card.getShape(), 1);
  }

  @Test
  public void cardLineTypeCorrectlyReturned() {
    Assertions.assertEquals(this.card.getLineType(), 3);
    Assertions.assertNotEquals(this.card.getLineType(), 2);
  }

  @Test
  public void checkSetIncorrectColor(){
    ICard card = new Card();
    assertThrows(IllegalArgumentException.class, () -> card.setColour(4));
  }

  @Test
  public void checkSetIncorrectShape(){
    ICard card = new Card();
    assertThrows(IllegalArgumentException.class, () -> card.setShape(4));
  }

    @Test
    public void checkSetIncorrectLine(){
      ICard card = new Card();
      assertThrows(IllegalArgumentException.class, () -> card.setLineType(4));
    }

    @Test
    public void checkSetIncorrectNumberOfShapes(){
      ICard card = new Card();
      assertThrows(IllegalArgumentException.class, () -> card.setNumberOfShapes(4));
    }

  @Test
  public void cardNumberCorrectlyReturned() {
    Assertions.assertEquals(this.card.getNumberOfShapes(), 2);
    Assertions.assertNotEquals(this.card.getNumberOfShapes(), 3);
  }

  @Test
  public void cardEqualityCorrectlyCheckedInCaseCardsAreIdentical() {
    Card card2 = mock(Card.class);
    when(card2.getColour()).thenReturn(1);
    when(card2.getShape()).thenReturn(2);
    when(card2.getLineType()).thenReturn(3);
    when(card2.getNumberOfShapes()).thenReturn(2);
    Assertions.assertTrue(this.card.equals(card2));
  }

  @Test
  public void cardEqualityCorrectlyCheckedInCaseCardsAreNotIdentical() {
    Card card2 = mock(Card.class);
    when(card2.getColour()).thenReturn(1);
    when(card2.getShape()).thenReturn(1);
    when(card2.getLineType()).thenReturn(1);
    when(card2.getNumberOfShapes()).thenReturn(1);
    Assertions.assertFalse(this.card.equals(card2));
  }


}
