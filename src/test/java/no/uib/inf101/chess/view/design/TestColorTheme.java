package no.uib.inf101.chess.view.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.Square;

public class TestColorTheme {
    @Test
    void sanityDefaultColorThemeTest() {
        ColorTheme colors = new DefaultColorTheme();
        assertEquals(new Color(176, 147, 132), colors.getBackgroundColorMenu());
        assertEquals(new Color(74, 47, 32), colors.getBackgroundColorActive());
        assertEquals(new Color(0, 0, 0, 160), colors.getGameOverScreenColor());
        assertEquals(Color.WHITE, colors.getGameOverTextColor());
        assertEquals(Color.WHITE, colors.getCoordinatesColor());
        assertEquals(new Color(0, 0, 0, 100), colors.getSelectedSquareColor());
        assertEquals(Color.ORANGE, colors.getLastMoveSquareColor());
        assertEquals(new Color(150, 101, 74), colors.getSquareColor(new Square(Column.A, 1)));
        assertEquals(new Color(150, 101, 74), colors.getSquareColor(new Square(Column.C, 5)));
        assertEquals(new Color(150, 101, 74), colors.getSquareColor(new Square(Column.H, 8)));
        assertEquals(new Color(219, 195, 182), colors.getSquareColor(new Square(Column.G, 6)));
        assertEquals(new Color(219, 195, 182), colors.getSquareColor(new Square(Column.A, 4)));
        assertEquals(new Color(219, 195, 182), colors.getSquareColor(new Square(Column.B, 7)));

        assertThrows(IllegalArgumentException.class, () -> colors.getSquareColor(new Square(Column.A, 9)));
    }
}