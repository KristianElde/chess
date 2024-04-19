package no.uib.inf101.chess.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestChessColor {
    @Test
    void chessColorToggleTest() {
        assertEquals(ChessColor.BLACK, ChessColor.WHITE.toggle());
        assertEquals(ChessColor.WHITE, ChessColor.BLACK.toggle());
    }
}
