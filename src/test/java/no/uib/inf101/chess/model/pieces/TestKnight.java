package no.uib.inf101.chess.model.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.model.Column;

public class TestKnight {

    @Test
    void knightLegalMoveTest() {
        String boardString = """
                K-------
                --------
                --------
                --------
                ---Np---
                ---p----
                --------
                -------k""";
        ChessModel model = new ChessModel(boardString, ChessColor.WHITE);

        Piece knight = model.getBoard().get(Column.D, 4).getPiece();

        // Checking that every move expected to be legal is in legalMoves for piece
        assertTrue(knight.getLegalMoves().contains(model.getBoard().get(Column.C, 6)));
        assertTrue(knight.getLegalMoves().contains(model.getBoard().get(Column.C, 2)));
        assertTrue(knight.getLegalMoves().contains(model.getBoard().get(Column.E, 6)));
        assertTrue(knight.getLegalMoves().contains(model.getBoard().get(Column.E, 2)));
        assertTrue(knight.getLegalMoves().contains(model.getBoard().get(Column.B, 5)));
        assertTrue(knight.getLegalMoves().contains(model.getBoard().get(Column.B, 3)));
        assertTrue(knight.getLegalMoves().contains(model.getBoard().get(Column.F, 5)));
        assertTrue(knight.getLegalMoves().contains(model.getBoard().get(Column.F, 3)));

        // Check that there is no more legal moves
        assertEquals(8, knight.getLegalMoves().size());
    }
}
