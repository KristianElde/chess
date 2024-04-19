package no.uib.inf101.chess.model.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.model.Column;

public class TestKing {
    @Test
    void knightLegalMoveTest() {
        String boardString = """
                --------
                --------
                --P-p---
                ---K----
                ---Np---
                ---p-k--
                --------
                --------""";
        ChessModel model = new ChessModel(boardString, ChessColor.WHITE);

        Piece king = model.getBoard().get(Column.D, 5).getPiece();

        // Checking that every move expected to be legal is in legalMoves for piece
        assertTrue(king.getLegalMoves().contains(model.getBoard().get(Column.D, 6)));
        assertTrue(king.getLegalMoves().contains(model.getBoard().get(Column.C, 5)));
        assertTrue(king.getLegalMoves().contains(model.getBoard().get(Column.C, 4)));
        assertTrue(king.getLegalMoves().contains(model.getBoard().get(Column.E, 6)));
        assertTrue(king.getLegalMoves().contains(model.getBoard().get(Column.E, 5)));

        // Check that there is no more legal moves
        assertEquals(5, king.getLegalMoves().size());
    }
}
