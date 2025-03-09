package server.impl;
import server.ICard;
import server.IChallenge;
import server.ILeagueTable;
import server.IModelServer;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of a {@link ModelServer}.
 */
public class ModelServer implements IModelServer {
    private ArrayList<IChallenge> challenges;
    private static final int totalNumbersOfCards = 81;
    private static final int totalNumberOfCardsInChallenge = 12;
    private static final int numberOfProperties = 3;
    private boolean initialize;
    private ArrayList<ICard> cardDeck;
    private boolean isSet = false;

    public ModelServer(){
        initialiseCardsDeck();
        createChallenges();
    }

    /**
     * Initialise the deck with the 81 cards.
     */
    private void initialiseCardsDeck () {
        cardDeck = new ArrayList<>(totalNumbersOfCards);
        for (int color = 1; color <= numberOfProperties; color++){
            for (int shape = 1; shape <= numberOfProperties; shape++){
                for (int line = 1; line <= numberOfProperties; line++){
                    for (int number = 1; number <= numberOfProperties; number++){
                        Card card = new Card();
                        card.setColour(color);
                        card.setShape(shape);
                        card.setLineType(line);
                        card.setNumberOfShapes(number);
                        cardDeck.add(card);
                    }
                }
            }
        }
        initialize = true;
    }
    /**
     * Create two challenges for the game.
     */
    private void createChallenges() {
        challenges = new ArrayList<>();
        Challenge challengeOne = new Challenge();
        challengeOne.setChallengeCardDeck(createSetOne() , 4);
        this.challenges.add(challengeOne);
        challengeOne.setChallengeId(challenges.indexOf(challengeOne));

        Challenge challengeTwo = new Challenge();
        challengeTwo.setChallengeCardDeck(createSetTwo() , 6);
        this.challenges.add(challengeTwo);
        challengeTwo.setChallengeId(challenges.indexOf(challengeTwo));

        Challenge challengeThree = new Challenge();
        challengeThree.setChallengeCardDeck(createSetThree() , 13);
        this.challenges.add(challengeThree);
        challengeThree.setChallengeId(challenges.indexOf(challengeThree));
    }

    /**
     * Create set for the first challenge.
     */
    private ArrayList<ICard> createSetOne() {
        ArrayList<ICard> cardSet = new ArrayList<>();
        for (int numberCard = 12; numberCard < totalNumberOfCardsInChallenge * 2; numberCard++){
            cardSet.add(cardDeck.get(numberCard));
        }
        return cardSet;
    }

    /**
     * Create set for the second challenge.
     */
    private ArrayList<ICard> createSetTwo()  {
        ArrayList<ICard> cardSet = new ArrayList<>();
        int counter = 0;
        int increment = 23;
        while (counter <= 2) {
            for (int cardNumber = increment; cardNumber < increment + 4; cardNumber++) {
                cardSet.add(cardDeck.get(cardNumber));
            }
            increment = increment + 26;
            counter = counter + 1;
        }
        return cardSet;
    }

    /**
     * Create set for the third challenge.
     */
    private ArrayList<ICard> createSetThree() {
        ArrayList<ICard> cardSet = new ArrayList<>();
        for (int numberCard = 0; numberCard < totalNumberOfCardsInChallenge; numberCard++){
            cardSet.add(cardDeck.get(numberCard));
        }
        return cardSet;
    }


    @Override
    public boolean hasBeenInitialize() {
        return initialize;
    }

    @Override
    public int getTotalNumberOfChallenges() {
        return challenges.size();
    }

    @Override
    public IChallenge getChallenge(int challengeId) {
        return this.challenges.get(challengeId);
    }

    @Override
    public boolean checkSet(ArrayList<Integer> cardsValues, int challengeId) {
         ArrayList<ICard> cards = createThreeSetOfCardsRetrieveFromTheClient(cardsValues);
         isSet = this.getChallenge(challengeId).checkSet(cards);
         return isSet;
    }

    @Override
    public ArrayList<Integer> challengeDeck(int challengeId) {
        IChallenge challenge = this.challenges.get(challengeId);
        ArrayList<ICard> cards = challenge.getChallengeCardDeck();
        ArrayList<Integer> cardsWithEachNumberProperty =  new ArrayList<>();
        for (int position = 0; position < cards.size(); position++) {
            cardsWithEachNumberProperty.add(cardAttributesNumbers(cards.get(position).getColour(),
                    cards.get(position).getShape(),cards.get(position).getLineType(), cards.get(position).getNumberOfShapes()));
        }
        return cardsWithEachNumberProperty;
    }

    /**
     *
     * @param color Integer parameter to represent the card color.
     * @param shape Integer parameter to represent the card shape.
     * @param line Integer parameter to represent the card line.
     * @param number Integer parameter to represent the card number.
     * @return The a number composed of all the attributes in the card.
     */
    private int cardAttributesNumbers(int color, int shape, int line, int number) {
        String card = Stream.of(color, shape, line, number).map(Object::toString).collect(Collectors.joining());
        int cardWithAllTheAttributes = Integer.parseInt(card);
        return cardWithAllTheAttributes;
    }

    /**
     * Create a set of three cards to be retrieved from the client.
     * @param cardsValues The integer values of each card.
     * @return An array list of cards.
     */
    private ArrayList<ICard> createThreeSetOfCardsRetrieveFromTheClient(ArrayList<Integer> cardsValues) {
        ArrayList<ICard> cardsWithAttributes = new ArrayList<>();
        if (cardsValues.size() == 3) {
            for (int cardPosition = 0; cardPosition < cardsValues.size(); cardPosition++) {
                String cardAttributes = String.valueOf(cardsValues.get(cardPosition));
                char[] eachCardAttributes = cardAttributes.toCharArray();
                if (eachCardAttributes.length == 4) {
                    Card card = new Card();
                    card.setColour(Character.getNumericValue(eachCardAttributes [0]));
                    card.setShape(Character.getNumericValue(eachCardAttributes [1]));
                    card.setLineType(Character.getNumericValue(eachCardAttributes [2]));
                    card.setNumberOfShapes(Character.getNumericValue(eachCardAttributes [3]));
                    cardsWithAttributes.add(card);
                } else {
                    throw new IllegalArgumentException("The card has not four attributes");
                }
            }
        }
        else {
            throw new IllegalArgumentException("The set of cards are not three");
        }
       return cardsWithAttributes;
    }

    @Override
    public int getNumberOfSetsInChallenge(int challengeId) {
        return this.challenges.get(challengeId).getNumberOfSetsIncluded();
    }

    @Override
    public void updateLeagueTable(int challengeId, String name, long time) {
        this.getLeagueTable(challengeId).addToLeagueTable(name, time);
    }


    @Override
    public ILeagueTable getLeagueTable(int challengeId) {
        return this.getChallenge(challengeId).getLeagueTable();
    }

}
