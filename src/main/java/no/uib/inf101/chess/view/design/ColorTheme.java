package no.uib.inf101.chess.view.design;

import java.awt.Color;

import no.uib.inf101.chess.model.Square;

/**
 * The ColorTheme interface defines a set of methods for obtaining
 * colors used in the Tetris game.
 */
public interface ColorTheme {

    /**
     * Returns the color associated with a specific square on a chessboard.
     * 
     * @param square The Square object representing a square on the board.
     * @return The color of the square.
     * 
     */
    Color getSquareColor(Square square);

    /**
     * Returns the color used for the frame or the borders for cells in the
     * grid.
     * 
     * @return The color of the frame.
     */
    Color getFrameColor();

    /**
     * Returns the background color used in the game.
     * 
     * @return The background color.
     */
    Color getBackgroundColor();

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
     * Returns the color used for displaying the score.
     * 
     * @return The color of the score text.
     */
    Color getScoreColor();

    Color getCoordinatesColor();

    Color getSelectedSquareColor();
}
