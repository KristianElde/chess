package no.uib.inf101.chess.model.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.Square;

public class TestPiece {

    @Test
    void constructorTest() {
        Piece piece = new Bishop(ChessColor.WHITE);

        assertNotNull(piece);
        assertEquals(ChessColor.WHITE, piece.getColor());
        assertNotNull(piece.getLegalMoves());
    }

    @Test
    void updateLegalMoves() {
        String boardString = """
                rnbqk-nr
                ppp--ppp
                ----p---
                ---p----
                Pb-P----
                --P-PN--
                -P---PPP
                RNBQKB-R""";

        ChessBoard board = ChessBoard.stringToBoard(boardString, ChessColor.BLACK);
        Square pawnSquare = board.get(Column.A, 7);
        Piece pawn = pawnSquare.getPiece();

        pawn.updateLegalMoves(board, pawnSquare, false);

        assertTrue(pawn.getLegalMoves().contains(board.get(Column.A, 6)));
        assertTrue(pawn.getLegalMoves().contains(board.get(Column.A, 5)));

        Square bishopSquare = board.get(Column.B, 4);
        Piece bishop = bishopSquare.getPiece();

        bishop.updateLegalMoves(board, bishopSquare, false);

        assertTrue(bishop.getLegalMoves().contains(board.get(Column.A, 5)));
        assertTrue(bishop.getLegalMoves().contains(board.get(Column.C, 3)));
        assertTrue(bishop.getLegalMoves().contains(board.get(Column.A, 3)));
        assertTrue(bishop.getLegalMoves().contains(board.get(Column.C, 5)));
        assertTrue(bishop.getLegalMoves().contains(board.get(Column.D, 6)));
        assertTrue(bishop.getLegalMoves().contains(board.get(Column.E, 7)));
        assertTrue(bishop.getLegalMoves().contains(board.get(Column.F, 8)));
        assertTrue(bishop.getLegalMoves().size() == 7);
    }

}
