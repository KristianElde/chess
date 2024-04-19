package no.uib.inf101.chess.model.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.model.Column;

public class TestRook {

    @Test
    void rookLegalMovesTest() {
        String boardString = """
                k-------
                --------
                -p------
                --------
                --p-R-P-
                ---p----
                ----p---
                -------K""";
        ChessModel model = new ChessModel(boardString, ChessColor.WHITE);
        Piece rook = model.getBoard().get(Column.E, 4).getPiece();

        // Checking that every move expected to be legal is in legalMoves for piece
        // Up
        assertTrue(rook.getLegalMoves().contains(model.getBoard().get(Column.E, 5)));
        assertTrue(rook.getLegalMoves().contains(model.getBoard().get(Column.E, 6)));
        assertTrue(rook.getLegalMoves().contains(model.getBoard().get(Column.E, 7)));
        assertTrue(rook.getLegalMoves().contains(model.getBoard().get(Column.E, 8)));

        // Down
        assertTrue(rook.getLegalMoves().contains(model.getBoard().get(Column.E, 3)));
        assertTrue(rook.getLegalMoves().contains(model.getBoard().get(Column.E, 2)));

        // Left
        assertTrue(rook.getLegalMoves().contains(model.getBoard().get(Column.D, 4)));
        assertTrue(rook.getLegalMoves().contains(model.getBoard().get(Column.C, 4)));

        // Right
        assertTrue(rook.getLegalMoves().contains(model.getBoard().get(Column.F, 4)));

        // Check that there is no more legal moves
        assertEquals(9, rook.getLegalMoves().size());

    }
}
