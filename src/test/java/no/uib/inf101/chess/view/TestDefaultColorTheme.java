package no.uib.inf101.chess.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.Square;
import no.uib.inf101.chess.view.design.ColorTheme;
import no.uib.inf101.chess.view.design.DefaultColorTheme;

public class TestDefaultColorTheme {
    @Test
    void sanityDefaultColorThemeTest() {
        ColorTheme colors = new DefaultColorTheme();
        assertEquals(Color.LIGHT_GRAY, colors.getBackgroundColor());
        assertEquals(Color.LIGHT_GRAY, colors.getFrameColor());
        assertEquals(new Color(0, 0, 0, 128), colors.getGameOverScreenColor());
        assertEquals(Color.RED, colors.getGameOverTextColor());
        assertEquals(Color.BLACK, colors.getCoordinatesColor());
        assertEquals(new Color(71, 143, 57), colors.getSelectedSquareColor());
        assertEquals(Color.ORANGE, colors.getLastMoveSquareColor());
        assertEquals(Color.DARK_GRAY, colors.getSquareColor(new Square(Column.A, 1)));
        assertEquals(Color.DARK_GRAY, colors.getSquareColor(new Square(Column.C, 5)));
        assertEquals(Color.DARK_GRAY, colors.getSquareColor(new Square(Column.H, 8)));
        assertEquals(Color.WHITE, colors.getSquareColor(new Square(Column.G, 6)));
        assertEquals(Color.WHITE, colors.getSquareColor(new Square(Column.A, 4)));
        assertEquals(Color.WHITE, colors.getSquareColor(new Square(Column.B, 7)));

        assertThrows(IllegalArgumentException.class, () -> colors.getSquareColor(new Square(Column.A, 9)));
    }
}