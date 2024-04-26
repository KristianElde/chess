package no.uib.inf101.chess.view.design;

import java.awt.Font;

/**
 * The DefaultFontTheme class implements the FontTheme interface and provides
 * default fonts for various UI elements.
 */
public class DefaultFontTheme implements FontTheme {

    @Override
    public Font getDefaultFont() {
        return new Font("Calibri", Font.BOLD, 16);
    }

    @Override
    public Font getHeaderFont() {
        return new Font("Calibri", Font.BOLD, 30);
    }

    @Override
    public Font getGameOverScreenFont() {
        return new Font("Calibri", Font.BOLD, 24);
    }

    @Override
    public Font getOptionFont() {
        return new Font("Calibri", Font.PLAIN, 20);
    }

    @Override
    public Font getSelectedOptionFont() {
        return new Font("Calibri", Font.BOLD, 20);
    }

    @Override
    public Font getNonSelectedOptionFont() {
        return new Font("Calibri", Font.PLAIN, 20);
    }

}
