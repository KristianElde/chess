package no.uib.inf101.chess.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.pieces.Rook;

public class TestSquare {

    @Test
    void squareTest() {
        Square square = new Square(Column.A, 1);
        assertNotNull(square);
        assertEquals(Column.A, square.col());
        assertEquals(1, square.row());

        square.setPiece(new Rook(ChessColor.WHITE));

        assertTrue(square.getPiece() instanceof Rook && square.getPiece().getColor() == ChessColor.WHITE);
    }
}
