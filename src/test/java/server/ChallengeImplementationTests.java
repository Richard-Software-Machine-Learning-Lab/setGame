package server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.impl.Card;
import server.impl.Challenge;
import server.impl.LeagueTable;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for Challenge
 */
public class ChallengeImplementationTests {
  // review throw error, and name of the cards
  IChallenge challenge;
  ArrayList<ICard> cards;
  int setsIncluded;
  ILeagueTable leagueTable;

  ICard card1;
  ICard card2;
  ICard card3;
  ICard card4;
  ICard card5;
  ICard card6;
  ICard card7;
  ICard card8;
  ICard card9;
  ICard card10;
  ICard card11;
  ICard card12;

  @BeforeEach
  void setup() {
    this.challenge = new Challenge();
    //create 12 mock cards
    this.cards = new ArrayList<ICard>();
    this.card1 = mock(Card.class);
    this.card2 = mock(Card.class);
    this.card3 = mock(Card.class);
    this.card4 = mock(Card.class);
    this.card5 = mock(Card.class);
    this.card6 = mock(Card.class);
    this.card7 = mock(Card.class);
    this.card8 = mock(Card.class);
    this.card9 = mock(Card.class);
    this.card10 = mock(Card.class);
    this.card11 = mock(Card.class);
    this.card12 = mock(Card.class);
    when(this.card1.getColour()).thenReturn(2);
    when(this.card1.getShape()).thenReturn(2);
    when(this.card1.getLineType()).thenReturn(3);
    when(this.card1.getNumberOfShapes()).thenReturn(3);
    this.cards.add(this.card1);
    when(this.card2.getColour()).thenReturn(2);
    when(this.card2.getShape()).thenReturn(1);
    when(this.card2.getLineType()).thenReturn(1);
    when(this.card2.getNumberOfShapes()).thenReturn(2);
    this.cards.add(this.card2);
    when(this.card3.getColour()).thenReturn(2);
    when(this.card3.getShape()).thenReturn(2);
    when(this.card3.getLineType()).thenReturn(3);
    when(this.card3.getNumberOfShapes()).thenReturn(2);
    this.cards.add(this.card3);
    when(this.card4.getColour()).thenReturn(2);
    when(this.card4.getShape()).thenReturn(3);
    when(this.card4.getLineType()).thenReturn(1);
    when(this.card4.getNumberOfShapes()).thenReturn(2);
    this.cards.add(this.card4);
    when(this.card5.getColour()).thenReturn(3);
    when(this.card5.getShape()).thenReturn(1);
    when(this.card5.getLineType()).thenReturn(1);
    when(this.card5.getNumberOfShapes()).thenReturn(2);
    this.cards.add(this.card5);
    when(this.card6.getColour()).thenReturn(2);
    when(this.card6.getShape()).thenReturn(3);
    when(this.card6.getLineType()).thenReturn(3);
    when(this.card6.getNumberOfShapes()).thenReturn(2);
    this.cards.add(this.card6);
    when(this.card7.getColour()).thenReturn(2);
    when(this.card7.getShape()).thenReturn(3);
    when(this.card7.getLineType()).thenReturn(3);
    when(this.card7.getNumberOfShapes()).thenReturn(1);
    this.cards.add(this.card7);
    when(this.card8.getColour()).thenReturn(1);
    when(this.card8.getShape()).thenReturn(2);
    when(this.card8.getLineType()).thenReturn(2);
    when(this.card8.getNumberOfShapes()).thenReturn(3);
    this.cards.add(this.card8);
    when(this.card9.getColour()).thenReturn(1);
    when(this.card9.getShape()).thenReturn(2);
    when(this.card9.getLineType()).thenReturn(3);
    when(this.card9.getNumberOfShapes()).thenReturn(1);
    this.cards.add(this.card9);
    when(this.card10.getColour()).thenReturn(3);
    when(this.card10.getShape()).thenReturn(1);
    when(this.card10.getLineType()).thenReturn(3);
    when(this.card10.getNumberOfShapes()).thenReturn(1);
    this.cards.add(this.card10);
    when(this.card11.getColour()).thenReturn(1);
    when(this.card11.getShape()).thenReturn(2);
    when(this.card11.getLineType()).thenReturn(3);
    when(this.card11.getNumberOfShapes()).thenReturn(3);
    this.cards.add(this.card11);
    when(this.card12.getColour()).thenReturn(3);
    when(this.card12.getShape()).thenReturn(2);
    when(this.card12.getLineType()).thenReturn(3);
    when(this.card12.getNumberOfShapes()).thenReturn(3);
    this.cards.add(this.card12);
    this.setsIncluded = 6;
    this.leagueTable = new LeagueTable();
  }

  @Test
  void challengeCardDeckIsCorrectlySetAndReturned() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    assertEquals(this.challenge.getChallengeCardDeck(), this.cards);
  }

  @Test
  void lessThanTwelveCardsCannotBeSetAndExceptionIsThrown() {
    //create 11 mock cards
    ArrayList<ICard> elevenCards = new ArrayList<>();
    for (int i = 0; i < 11; i++) {
      ICard card = mock(Card.class);
      elevenCards.add(card);
    }
    assertThrows(IllegalArgumentException.class, () -> this.challenge.setChallengeCardDeck(elevenCards, 1));
  }

  @Test
  void moreThanTwelveCardsCannotBeSetAndExceptionIsThrown() {
    //create 13 mock cards
    ArrayList<ICard> thirteenCards = new ArrayList<>();
    for (int i = 0; i < 13; i++) {
      ICard card = mock(Card.class);
      thirteenCards.add(card);
    }
    assertThrows(IllegalArgumentException.class, () -> this.challenge.setChallengeCardDeck(thirteenCards, 1));
  }

  @Test
  void passedCardDeckWithLessThanOneSetIncludedResultsInThrownException() {
    assertThrows(IllegalArgumentException.class, () -> this.challenge.setChallengeCardDeck(this.cards, 0));
  }

  @Test
  void cardDeckGetterReturnsNullIfNoCardDeckIsSet() {
    assertTrue(this.challenge.getChallengeCardDeck() == null);
  }

  @Test
  void leagueTableIsCorrectlySetAndReturned() {
    this.challenge.setLeagueTable(this.leagueTable);
    assertTrue(this.leagueTable.equals(this.challenge.getLeagueTable()));
  }

  @Test
  void leagueTableGetterReturnsNullIfNoCardDeckIsSet() {
    assertTrue(this.challenge.getLeagueTable() == null);
  }

  @Test
  void numberOfSetsIncludedIsCorrectlyReturned() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    assertTrue(this.challenge.getNumberOfSetsIncluded() == 6);
  }

  @Test
  void exceptionIsThrownWhenCardsToBeCheckedContainsMoreThanThreeCards() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    //create 4 mock cards
    ArrayList<ICard> fourCards = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      ICard card = mock(Card.class);
      fourCards.add(card);
    }
    assertThrows(IllegalArgumentException.class, () -> this.challenge.checkSet(fourCards));
  }

  @Test
  void exceptionIsThrownWhenCardsToBeCheckedContainsLessThanThreeCards() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    //create 2 mock cards
    ArrayList<ICard> twoCards = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      ICard card = mock(Card.class);
      twoCards.add(card);
    }
    assertThrows(IllegalArgumentException.class, () -> this.challenge.checkSet(twoCards));
  }

  @Test
  void falseCorrectlyReturnedIfCardsToBeCheckedDoNotContainSet() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    ArrayList<ICard> cardsNotContainingSet = new ArrayList<>();
    cardsNotContainingSet.add(this.card1);
    cardsNotContainingSet.add(this.card2);
    cardsNotContainingSet.add(this.card3);
    assertFalse(this.challenge.checkSet(cardsNotContainingSet));
  }

  @Test
  void cardsWithSameShapesNoOfShapesAndAllDifferentColorsButNotAllDifferentShadingAreNotIdentifiedAsSet() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    ArrayList<ICard> cardsNotContainingSet = new ArrayList<>();
    cardsNotContainingSet.add(this.card1);
    cardsNotContainingSet.add(this.card8);
    cardsNotContainingSet.add(this.card12);
    assertFalse(this.challenge.checkSet(cardsNotContainingSet));
  }

  @Test
  void cardsWithSameShadingColorAndAllDifferentNoOfShapesButNotAlLDifferentShapesAreNotIdentifiedAsSet() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    ArrayList<ICard> cardsNotContainingSet = new ArrayList<>();
    cardsNotContainingSet.add(this.card1);
    cardsNotContainingSet.add(this.card6);
    cardsNotContainingSet.add(this.card7);
    assertFalse(this.challenge.checkSet(cardsNotContainingSet));
  }

  @Test
  void cardsWithNotAllDifferentColorShapesShadingAndNoOfShapesAreCorrectlyNotIdentifiedAsSet() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    ArrayList<ICard> cardsNotContainingSet = new ArrayList<>();
    cardsNotContainingSet.add(this.card8);
    cardsNotContainingSet.add(this.card11);
    cardsNotContainingSet.add(this.card12);
    assertFalse(this.challenge.checkSet(cardsNotContainingSet));
  }

  @Test
  void cardsWithAllDifferentColorsNoOfShapesButNotAllDifferentShapesShadingAreCorrectlyNotIdentifiedAsSet() {//
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    ArrayList<ICard> cardsNotContainingSet = new ArrayList<>();
    cardsNotContainingSet.add(this.card1);
    cardsNotContainingSet.add(this.card5);
    cardsNotContainingSet.add(this.card9);
    assertFalse(this.challenge.checkSet(cardsNotContainingSet));
  }

  @Test
  void trueCorrectlyReturnedIfCardsToBeCheckedContainSet1() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    ArrayList<ICard> cardsContainingSet = new ArrayList<>();
    cardsContainingSet.add(this.card1);
    cardsContainingSet.add(this.card11);
    cardsContainingSet.add(this.card12);
    assertTrue(this.challenge.checkSet(cardsContainingSet));
  }

  @Test
  void trueCorrectlyReturnedIfCardsToBeCheckedContainSet2() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    ArrayList<ICard> cardsContainingSet = new ArrayList<>();
    cardsContainingSet.add(this.card3);
    cardsContainingSet.add(this.card9);
    cardsContainingSet.add(this.card12);
    assertTrue(this.challenge.checkSet(cardsContainingSet));
  }

  @Test
  void trueCorrectlyReturnedIfCardsToBeCheckedContainSet3() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    ArrayList<ICard> cardsContainingSet = new ArrayList<>();
    cardsContainingSet.add(this.card4);
    cardsContainingSet.add(this.card8);
    cardsContainingSet.add(this.card10);
    assertTrue(this.challenge.checkSet(cardsContainingSet));
  }

  @Test
  void trueCorrectlyReturnedIfCardsToBeCheckedContainSet4() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    ArrayList<ICard> cardsContainingSet = new ArrayList<>();
    cardsContainingSet.add(this.card5);
    cardsContainingSet.add(this.card7);
    cardsContainingSet.add(this.card8);
    assertTrue(this.challenge.checkSet(cardsContainingSet));
  }

  @Test
  void trueCorrectlyReturnedIfCardsToBeCheckedContainSet5() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    ArrayList<ICard> cardsContainingSet = new ArrayList<>();
    cardsContainingSet.add(this.card6);
    cardsContainingSet.add(this.card10);
    cardsContainingSet.add(this.card11);
    assertTrue(this.challenge.checkSet(cardsContainingSet));
  }

  @Test
  void trueCorrectlyReturnedIfCardsToBeCheckedContainSet6() {
    this.challenge.setChallengeCardDeck(this.cards, this.setsIncluded);
    ArrayList<ICard> cardsContainingSet = new ArrayList<>();
    cardsContainingSet.add(this.card7);
    cardsContainingSet.add(this.card9);
    cardsContainingSet.add(this.card10);
    assertTrue(this.challenge.checkSet(cardsContainingSet));
  }

  @Test
  void challengeIdIsCorrectlySetAndReturned() {
    this.challenge.setChallengeId(0);
    assertTrue(this.challenge.getChallengeId() == 0);
  }

}
