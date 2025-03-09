package client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Class for the RequestHandler.
 */
public class RequestHandlerTests {
    IRequestHandler requestHandler;

    @BeforeEach
    public void setup() {
        requestHandler = new RequestHandler();
    }
    @Test
    public void checkSetReturnsTrueIfASet() {
        int card1Number = 2233;
        int card2Number = 1233;
        int card3Number = 3233;
        int[] cardsAreASet = new int[3];
        cardsAreASet[0] = card1Number;
        cardsAreASet[1] = card2Number;
        cardsAreASet[2] = card3Number;
        int challenge1Id = 0;
        Assertions.assertTrue(this.requestHandler.checkSet(cardsAreASet, challenge1Id));
    }

    @Test
    public void checkSetReturnsTrueIfNotASet() {
        int card4Number = 1233;
        int card5Number = 2233;
        int card6Number = 2233;
        int[] cardsAreNotASet = new int[3];
        cardsAreNotASet[0] = card4Number;
        cardsAreNotASet[1] = card5Number;
        cardsAreNotASet[2] = card6Number;

        int challenge1Id = 0;
        Assertions.assertFalse(this.requestHandler.checkSet(cardsAreNotASet, challenge1Id));
    }

    @Test
    public void fetchChallengeDeckReturnsStringOfCardsInChallenge() {
        int[] cardDeckForChallenge1InString = {1221,1222,1223,1231,1232,1233,1311,1312,1313,1321,1322,1323};
        Assertions.assertArrayEquals(this.requestHandler.fetchChallengeDeck(0), cardDeckForChallenge1InString);
        int[] cardDeckForChallenge2InString = {1111,1112,1113,1121,1122,1123,1131,1132,1133,1211,1212,1213};
        Assertions.assertArrayEquals(this.requestHandler.fetchChallengeDeck(1), cardDeckForChallenge2InString);
    }

    @Test
    public void fetchSetsIncludedInChallengeReturnsInteger() {
        int challengeId1 = 0;
        int challengeId2 = 1;
        int numberOfSetsInChallenge1 = 4;
        int numberOfSetsInChallenge2 = 13;

        Assertions.assertEquals(this.requestHandler.fetchSetsIncludedInChallenge(challengeId1), numberOfSetsInChallenge1);
        Assertions.assertEquals(this.requestHandler.fetchSetsIncludedInChallenge(challengeId2), numberOfSetsInChallenge2);
    }

    @Test
    public void getLeagueTableForChallengeShouldBeEmptyBeforeUpdateLeagueTableCalled() {
        int challengeId2 = 1;
        String[] input = new String[1];
        input[0] = "";
        Assertions.assertArrayEquals(this.requestHandler.getLeagueTableForChallenge(challengeId2), input);
    }


    @Test
    public void updateLeagueTableForChallenge() {
        int challengeId2 = 1;
        String[] input = new String[4];
        this.requestHandler.updateLeagueTableForChallenge(challengeId2, "Andrew", 13);
        this.requestHandler.updateLeagueTableForChallenge(challengeId2, "Richard", 14);
        input[0] = "Andrew";
        input[1] = "13";
        input[2] = "Richard";
        input[3] = "14";
        Assertions.assertEquals(this.requestHandler.getLeagueTableForChallenge(challengeId2)[0], input[0]);
        Assertions.assertArrayEquals(this.requestHandler.getLeagueTableForChallenge(challengeId2), input);
    }

    @Test
    public void getLeagueTableThrowsErrorIfChallengeIdDoesNotExist() {
        //Out of range
        Error error = Assertions.assertThrows(Error.class, () -> this.requestHandler.getLeagueTableForChallenge(5));
        String actualMessage = "challengeId does not exist";
        assertEquals(error.getMessage(), actualMessage);

        // Negative challengeId
        Error errorNegative = Assertions.assertThrows(Error.class, () -> this.requestHandler.getLeagueTableForChallenge(-4));
        String actualMessageNegative = "challengeId does not exist";
        assertEquals(errorNegative.getMessage(), actualMessageNegative);

    }

    @Test
    public void updateLeagueTableThrowsErrorIfChallengeIdDoesNotExist() {
        //Out of range
        Error error = Assertions.assertThrows(Error.class, () -> this.requestHandler.updateLeagueTableForChallenge(5, "Emma", 2353));
        String actualMessage = "challengeId does not exist";
        assertEquals(error.getMessage(), actualMessage);

        // Negative challengeId
        Error errorNegative = Assertions.assertThrows(Error.class, () -> this.requestHandler.updateLeagueTableForChallenge(-5, "Emma", 2353));
        String actualMessageNegative = "challengeId does not exist";
        assertEquals(errorNegative.getMessage(), actualMessageNegative);
    }

}
