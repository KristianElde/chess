package no.uib.inf101.chess.view.design;

import java.awt.Color;

import no.uib.inf101.chess.model.Square;

/**
 * The DefaultColorTheme class implements the ColorTheme interface and provides
 * default colors for various UI elements in a chess game.
 */
public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getSquareColor(Square square) {
        if (square.row() > 8 || square.row() < 1)
            throw new IllegalArgumentException("Square is not on chess board.");
        if ((square.row() + square.col().ordinal()) % 2 == 0) {
            return new Color(219, 195, 182);
        } else {
            return new Color(150, 101, 74);
        }
    }

    @Override
    public Color getBackgroundColorMenu() {
        return new Color(176, 147, 132);
    }

    @Override
    public Color getBackgroundColorActive() {
        return new Color(74, 47, 32);
    }

    @Override
    public Color getGameOverScreenColor() {
        return new Color(0, 0, 0, 128);
    }

    @Override
    public Color getGameOverTextColor() {
        return Color.RED;
    }

    @Override
    public Color getCoordinatesColor() {
        return Color.WHITE;
    }

    @Override
    public Color getSelectedSquareColor() {
        return new Color(0, 0, 0, 100);
    }

    @Override
    public Color getLastMoveSquareColor() {
        return Color.ORANGE;
    }

    @Override
    public Color getMenuOptionsColor() {
        return new Color(74, 47, 32);
    }

    @Override
    public Color getSelectedMenuOptionsColor() {
        return new Color(255, 245, 228);
    }

}