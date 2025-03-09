package server;

/**
 * Interface for a card, representing one of the 81 playing cards of the Set Game.
 */
public interface ICard {

    /**
     * Method to set the colour of the shape on the card.
     */
    void setColour(int colour);

    /**
     * Method to set the shape on the card.
     */
    void setShape(int shape);

    /**
     * Method to set the line type of the shape on the card as a string of the line type.
     */
    void setLineType(int lineType);

    /**
     * Method to set the number of shapes on the card.
     */
    void setNumberOfShapes(int number);

    /**
     * Method to get the colour of the shape on the card.
     * @return String of the colour of the shape on the card.
     */
    int getColour();

    /**
     * Method to get the shape on the card.
     * @return String of the name of the shape on the card.
     */
    int getShape();

    /**
     * Method to get the line type of the shape on the card as a string of the line type.
     * @return String of the type of line used to draw the shape on the card.
     */
    int getLineType();

    /**
     * Method to get the number of shapes on the card.
     * @return int of the number of shapes on the card.
     */
    int getNumberOfShapes();

    /**
     * Returns true if a card is equal to another card, meaning that all features are identical.
     * @param o Card object to be compared
     * @return true if cards are equal, otherwise false
     */
    @Override
    public boolean equals(Object o);

}
