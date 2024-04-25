package no.uib.inf101.chess.view.design;

import java.awt.Font;

/**
 * The FontTheme interface defines a set of methods for obtaining fonts used in
 * a chess game.
 */
public interface FontTheme {

    /**
     * Returns the default font used in the chess game.
     * 
     * @return The default font.
     */
    Font getDefaultFont();

    /**
     * Returns the font used for headers in the chess game.
     * 
     * @return The header font.
     */
    Font getHeaderFont();

    /**
     * Returns the font used for displaying text on the game over screen.
     * 
     * @return The font of the game over screen text.
     */
    Font getGameOverScreenFont();

    /**
     * Returns the font used for displaying menu options.
     * 
     * @return The font of the menu options.
     */
    Font getOptionFont();

    /**
     * Returns the font used for highlighting selected menu options.
     * 
     * @return The font of the selected menu options.
     */
    Font getSelectedOptionFont();

    /**
     * Returns the font used for non-selected menu options.
     * 
     * @return The font of the non-selected menu options.
     */
    Font getNonSelectedOptionFont();
}
