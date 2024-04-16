package no.uib.inf101.chess.view.design;

import java.awt.Color;

import no.uib.inf101.chess.model.Square;

/**
 * The DefaultColorTheme class implements the ColorTheme interface,
 * providing default colors for the Tetris game interface.
 */
public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getSquareColor(Square square) {
        if ((square.row() + square.col().ordinal()) % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }

    }

    @Override
    public Color getFrameColor() {
        return Color.LIGHT_GRAY;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.LIGHT_GRAY;
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
    public Color getScoreColor() {
        return Color.LIGHT_GRAY;
    }

    @Override
    public Color getCoordinatesColor() {
        return Color.BLACK;
    }

    @Override
    public Color getSelectedSquareColor() {
        return new Color(71, 143, 57);
    }

    @Override
    public Color getLastMoveSquareColor() {
        return Color.ORANGE;
    }

}