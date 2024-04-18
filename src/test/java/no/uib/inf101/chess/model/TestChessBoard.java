package no.uib.inf101.chess.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.pieces.Bishop;
import no.uib.inf101.chess.model.pieces.King;
import no.uib.inf101.chess.model.pieces.Knight;
import no.uib.inf101.chess.model.pieces.Rook;

public class TestChessBoard {

    @Test
    void constructorTest() {
        ChessBoard board = new ChessBoard();

        assertNotNull(board);
        assertEquals(ChessColor.WHITE, board.getToDraw());

        // Check that white rook is at A1
        assertTrue(board.get(Column.A, 1).getPiece() instanceof Rook);
        assertTrue(board.get(Column.A, 1).getPiece().getColor() == ChessColor.WHITE);

        // Check that Black king is at E8
        assertTrue(board.get(Column.E, 8).getPiece() instanceof King);
        assertTrue(board.get(Column.E, 8).getPiece().getColor() == ChessColor.BLACK);
    }

    @Test
    void stringToBoardTest() {
        String revBoardString = """
                rnbqkbnr
                ppp--ppp
                ----p---
                -B-p----
                ---P----
                ----P---
                PPP--PPP
                RNBQK-NR""";

        ChessBoard board = ChessBoard.stringToBoard(revBoardString, ChessColor.BLACK);

        assertNotNull(board);
        assertEquals(ChessColor.BLACK, board.getToDraw());
        assertFalse(board.isInCheck(ChessColor.WHITE));
        assertTrue(board.isInCheck(ChessColor.BLACK));
        assertTrue(board.get(Column.A, 1).getPiece() instanceof Rook
                && board.get(Column.A, 1).getPiece().getColor() == ChessColor.WHITE);
        assertTrue(board.get(Column.B, 5).getPiece() instanceof Bishop
                && board.get(Column.B, 5).getPiece().getColor() == ChessColor.WHITE);
        assertTrue(board.get(Column.G, 8).getPiece() instanceof Knight
                && board.get(Column.G, 8).getPiece().getColor() == ChessColor.BLACK);
    }

    @Test
    void movePieceTest() {
        ChessBoard board = new ChessBoard();

        // Check that pawn to E4 does not result in a capture
        assertEquals(null,
                board.movePiece(board.get(Column.E, 2), board.get(Column.E, 4), board.get(Column.E, 2).getPiece()));

        // Move black Pawn to D5
        board.movePiece(board.get(Column.D, 7), board.get(Column.D, 5), board.get(Column.D, 7).getPiece());

        // Check that Pawn capture on D5 returns captured pawn
        assertEquals(board.get(Column.D, 5).getPiece(),
                board.movePiece(board.get(Column.E, 4), board.get(Column.D, 5), board.get(Column.E, 4).getPiece()));

        // Move black pawn to E5
        board.movePiece(board.get(Column.E, 7), board.get(Column.E, 5), board.get(Column.E, 7).getPiece());

        // Check that en passent move returns captured pawn
        assertEquals(board.get(Column.E, 5).getPiece(),
                board.movePiece(board.get(Column.D, 5), board.get(Column.E, 6), board.get(Column.D, 5).getPiece()));
    }

}
