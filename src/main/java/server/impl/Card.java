package server.impl;

import server.ICard;

/**
 * Implementation of a {@link ICard}.
 */
public class Card implements ICard {
    int colour;
    int shape;
    int line;
    int number;

    /**
     * Method to set the colour of the shape on the card.
     */
    @Override
    public void setColour(int colour) {
        this.colour = colour;
        if (colour >= 1 && colour <= 3) {
            this.colour = colour;
        }
        else {
            throw new IllegalArgumentException("Color should be between the range 1 and 3");
        }
    }

    /**
     * Method to set the shape on the card.
     */
    @Override
    public void setShape(int shape) {
        if (shape >= 1 && shape <= 3) {
            this.shape = shape;
        }
        else {
            throw new IllegalArgumentException("Shape should be between the range 1 and 3");
        }
    }

    /**
     * Method to set the line type of the shape on the card as a string of the line type.
     */
    @Override
    public void setLineType(int line) {
        if (line >= 1 && line <= 3) {
            this.line = line;
        }
        else {
            throw new IllegalArgumentException("Line should be between the range 1 and 3");
        }
    }

    /**
     * Method to set the number of shapes on the card.
     */
    @Override
    public void setNumberOfShapes(int number) {
        if (number >= 1 && number <= 3) {
            this.number = number;
        }
        else {
            throw new IllegalArgumentException("Number should be between the range 1 and 3");
        }
    }

    /**
     * Method to get the colour of the shape on the card.
     *
     * @return String of the colour of the shape on the card.
     */
    @Override
    public int getColour() {
        return this.colour;
    }

    /**
     * Method to get the shape on the card.
     *
     * @return String of the name of the shape on the card.
     */
    @Override
    public int getShape() {
        return this.shape;
    }

    /**
     * Method to get the line type of the shape on the card as a string of the line type.
     *
     * @return String of the type of line used to draw the shape on the card.
     */
    @Override
    public int getLineType() {
        return this.line;
    }

    /**
     * Method to get the number of shapes on the card.
     *
     * @return int of the number of shapes on the card.
     */
    @Override
    public int getNumberOfShapes() {
        return this.number;
    }

}
