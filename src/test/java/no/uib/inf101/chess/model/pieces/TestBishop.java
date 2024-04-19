package no.uib.inf101.chess.model.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.model.Column;

public class TestBishop {

    @Test
    void bishopLegalMoveTest() {
        String boardString = """
                k-------
                --------
                -p------
                ----P---
                ---b--N-
                ---p----
                --------
                -------K""";
        ChessModel model = new ChessModel(boardString, ChessColor.BLACK);

        Piece bishop = model.getBoard().get(Column.D, 4).getPiece();

        // Checking that every move expected to be legal is in legalMoves for piece
        // Up and left
        assertTrue(bishop.getLegalMoves().contains(model.getBoard().get(Column.E, 5)));

        // Down and left
        assertTrue(bishop.getLegalMoves().contains(model.getBoard().get(Column.E, 3)));
        assertTrue(bishop.getLegalMoves().contains(model.getBoard().get(Column.F, 2)));
        assertTrue(bishop.getLegalMoves().contains(model.getBoard().get(Column.G, 1)));

        // Up and right
        assertTrue(bishop.getLegalMoves().contains(model.getBoard().get(Column.C, 5)));

        // Down and right
        assertTrue(bishop.getLegalMoves().contains(model.getBoard().get(Column.C, 3)));
        assertTrue(bishop.getLegalMoves().contains(model.getBoard().get(Column.B, 2)));
        assertTrue(bishop.getLegalMoves().contains(model.getBoard().get(Column.A, 1)));

        // Check that there is no more legal moves
        assertEquals(8, bishop.getLegalMoves().size());
    }
}
