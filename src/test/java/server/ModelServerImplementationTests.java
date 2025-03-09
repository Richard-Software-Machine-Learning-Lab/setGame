package server;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import server.impl.ModelServer;

import java.util.ArrayList;
import java.util.Hashtable;


/**
 * Tests for Model of Set Game.
 */
public class ModelServerImplementationTests {
    IModelServer model;

    @BeforeEach
    void setup() {
        model = new ModelServer();
    }

    @Test
    public void modelHasBeenInitialized(){
         Assertions.assertTrue(model.hasBeenInitialize());
    }

    @Test
    public void checkSet() {
        int challengeOne = 0;
        ArrayList<Integer> cards = new ArrayList<>();
        int cardOne = 2232;
        int cardTwo = 2112;
        int cardThree = 2322;
        cards.add(cardOne);
        cards.add(cardTwo);
        cards.add(cardThree);
        Assertions.assertTrue(this.model.checkSet(cards,challengeOne));
    }

    @Test
    public void checkNoSet() {
        int challengeOne = 0;
        ArrayList<Integer> cards = new ArrayList<>();
        int cardOne = 2332;
        int cardTwo = 2112;
        int cardThree = 1311;
        cards.add(cardOne);
        cards.add(cardTwo);
        cards.add(cardThree);
        Assertions.assertFalse(this.model.checkSet(cards,challengeOne));
    }


    @Test
    public void checkSetCardsException(){
        int challengeOne = 0;
        ArrayList<Integer> cards = new ArrayList<>();
        int cardOne = 2234;
        int cardTwo = 2112;
        int cardThree = 2322;
        cards.add(cardOne);
        cards.add(cardTwo);
        cards.add(cardThree);
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.model.checkSet(cards, challengeOne));
    }


    @Test
    public void theLeagueTableIsSetup(){
        int challengeId = 0;
        Assertions.assertEquals(this.model.getLeagueTable(challengeId), this.model.getChallenge(challengeId).getLeagueTable());
    }


    @Test
    public void updateLeagueTable() {
        long time = -1L;
        int challengeId = 0;
        this.model.updateLeagueTable(challengeId, "Richard", time);
        Assertions.assertNotNull(this.model.getLeagueTable(0).getLeagueTable().get("Richard"));
        Assertions.assertEquals(this.model.getLeagueTable(0).getLeagueTable().get("Richard"), -1);
    }

    @Test
    public void getLeagueTableHoldsHashTable() {
        long time = -1L;
        int challengeId = 0;
        Hashtable<String, Long> test = new Hashtable<>();
        test.put("Richard", time);
        this.model.updateLeagueTable(challengeId, "Richard", time);
        Assertions.assertEquals(this.model.getLeagueTable(challengeId).getLeagueTable(), test);
    }

    @Test
    public void checkNumberOfSetsInChallenge() {
        int numberOfSetsInChallenge1 = 4;
        int numberOfSetsInChallenge2 = 13;
        int challenge1Id = 0;
        int challenge2Id = 1;

        Assertions.assertEquals(this.model.getNumberOfSetsInChallenge(challenge1Id), numberOfSetsInChallenge1);
        Assertions.assertEquals(this.model.getNumberOfSetsInChallenge(challenge2Id), numberOfSetsInChallenge2);
    }

    @Test
    public void getTotalNumberOfChallenges() {
        Assertions.assertEquals(this.model.getTotalNumberOfChallenges(),3);
    }

    @Test
    public void challengeCardDeck() {
        ArrayList<Integer> cards = this.model.challengeDeck(0);
        ArrayList<Integer> cardsChallengeOne = new ArrayList<>();
        cardsChallengeOne.add(1221);
        cardsChallengeOne.add(1222);
        cardsChallengeOne.add(1223);
        cardsChallengeOne.add(1231);
        cardsChallengeOne.add(1232);
        cardsChallengeOne.add(1233);
        cardsChallengeOne.add(1311);
        cardsChallengeOne.add(1312);
        cardsChallengeOne.add(1313);
        cardsChallengeOne.add(1321);
        cardsChallengeOne.add(1322);
        cardsChallengeOne.add(1323);
        for (int position = 0; position < cardsChallengeOne.size(); position ++) {
            Assertions.assertEquals(cards.get(position), cardsChallengeOne.get(position));
        }
    }

    @Test
    public void checkSetWithTwoCards() {
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1233);
        cards.add(1232);
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.model.checkSet(cards,0));
    }

    @Test
    public void checkSetWithCardsWithMoreOrLessThanFourAttributes() {
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(12331);
        cards.add(123);
        cards.add(1231);
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.model.checkSet(cards,0));
    }

    public void checkSetWrongColour() {
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(0233);
        cards.add(1232);
        cards.add(1231);
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.model.checkSet(cards,0));
    }

    public void checkSetWrongShape(){
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1233);
        cards.add(1032);
        cards.add(1231);
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.model.checkSet(cards,0));
    }

    public void checkSetWrongLine() {
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1233);
        cards.add(1202);
        cards.add(1231);
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.model.checkSet(cards,0));
    }

    public void checkSetWrongNumber() {
        ArrayList<Integer> cards = new ArrayList<>();
        cards.add(1233);
        cards.add(1230);
        cards.add(1231);
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.model.checkSet(cards,0));
    }
}
