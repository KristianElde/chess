package no.uib.inf101.chess.view.design;

import java.awt.Color;

import no.uib.inf101.chess.model.Square;

/**
 * The ColorTheme interface defines a set of methods for obtaining
 * colors used in a chess game.
 */
public interface ColorTheme {

    /**
     * Returns the color associated with a specific square on the chessboard.
     * 
     * @param square The Square object representing a square on the chessboard.
     * @return The color of the square.
     */
    Color getSquareColor(Square square);

    /**
     * Returns the background color used in the chess game.
     * 
     * @return The background color.
     */
    Color getBackgroundColorMenu();

    Color getBackgroundColorActive();

    /**
     * Returns the color used for the game over screen.
     * 
     * @return The color of the game over screen.
     */
    Color getGameOverScreenColor();

    /**
     * Returns the color used for the text displayed on the game over
     * screen.
     * 
     * @return The color of the game over screen text.
     */
    Color getGameOverTextColor();

    /**
     * Returns the color used for displaying coordinates on the chessboard.
     * 
     * @return The color of the coordinates.
     */
    Color getCoordinatesColor();

    /**
     * Returns the color used for highlighting selected squares on the chessboard.
     * 
     * @return The color of the selected squares.
     */
    Color getSelectedSquareColor();

    /**
     * Returns the color used for highlighting the last moved squares on the
     * chessboard.
     * 
     * @return The color of the last moved squares.
     */
    Color getLastMoveSquareColor();

    /**
     * Returns the color used for displaying menu options.
     * 
     * @return The color of the menu options.
     */
    Color getMenuOptionsColor();

    /**
     * Returns the color used for highlighting selected menu options.
     * 
     * @return The color of the selected menu options.
     */
    Color getSelectedMenuOptionsColor();
}