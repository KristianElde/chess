package no.uib.inf101.chess.model.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.model.Column;

public class TestQueen {
    @Test
    void queenLegalMovesTest() {
        String boardString = """
                k-------
                -b------
                -p------
                --nQ----
                --p-R-P-
                ---p----
                ----p---
                -------K""";
        ChessModel model = new ChessModel(boardString, ChessColor.BLACK);
        Piece queen = model.getBoard().get(Column.D, 5).getPiece();

        // Checking that every move expected to be legal is in legalMoves for piece
        // Up
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.D, 6)));
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.D, 7)));
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.D, 8)));

        // Down
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.D, 4)));
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.D, 3)));

        // Left
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.D, 4)));

        // Right
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.E, 5)));
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.F, 5)));
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.G, 5)));
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.H, 5)));

        // Up and right
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.E, 6)));
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.F, 7)));
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.G, 8)));

        // Up and left
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.C, 6)));
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.B, 7)));

        // Down and right
        assertFalse(queen.getLegalMoves().contains(model.getBoard().get(Column.E, 4)));

        // Down and left
        assertTrue(queen.getLegalMoves().contains(model.getBoard().get(Column.C, 4)));

        // Check that there is no more legal moves
        assertEquals(16, queen.getLegalMoves().size());
    }
}
