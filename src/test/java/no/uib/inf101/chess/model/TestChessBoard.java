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
        void initialPositionBoardTest() {
                ChessBoard board = ChessBoard.initialPositionBoard();

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
                ChessBoard board = ChessBoard.initialPositionBoard();

                // Check that pawn to E4 does not result in a capture
                assertEquals(null,
                                board.movePiece(board.get(Column.E, 2), board.get(Column.E, 4),
                                                board.get(Column.E, 2).getPiece()));

                // Move black Pawn to D5
                board.movePiece(board.get(Column.D, 7), board.get(Column.D, 5), board.get(Column.D, 7).getPiece());

                // Check that Pawn capture on D5 returns captured pawn
                assertEquals(board.get(Column.D, 5).getPiece(),
                                board.movePiece(board.get(Column.E, 4), board.get(Column.D, 5),
                                                board.get(Column.E, 4).getPiece()));

                // Move black pawn to E5
                board.movePiece(board.get(Column.E, 7), board.get(Column.E, 5), board.get(Column.E, 7).getPiece());

                // Check that en passent move returns captured pawn
                assertEquals(board.get(Column.E, 5).getPiece(),
                                board.movePiece(board.get(Column.D, 5), board.get(Column.E, 6),
                                                board.get(Column.D, 5).getPiece()));
        }

        @Test
        void isThreatenedByTest() {
                String boardString = """
                                -k------
                                ------P-
                                -N------
                                R----B--
                                --------
                                --------
                                --------
                                ------K-""";
                ChessBoard board = ChessBoard.stringToBoard(boardString, ChessColor.BLACK);

                assertFalse(board.isThreatenedBy(board.get(Column.B, 8), ChessColor.WHITE));
                assertTrue(board.isThreatenedBy(board.get(Column.A, 8), ChessColor.WHITE));
                assertFalse(board.isThreatenedBy(board.get(Column.G, 8), ChessColor.WHITE));
                assertTrue(board.isThreatenedBy(board.get(Column.F, 8), ChessColor.WHITE));
                assertTrue(board.isThreatenedBy(board.get(Column.C, 8), ChessColor.WHITE));
                assertTrue(board.isThreatenedBy(board.get(Column.B, 7), ChessColor.BLACK));
                assertTrue(board.isThreatenedBy(board.get(Column.F, 2), ChessColor.WHITE));
        }

        @Test
        void testMoveIsLegalTest() {
                String boardString = """
                                -k------
                                ------P-
                                -N------
                                R----B--
                                --------
                                --------
                                --------
                                ------K-""";
                ChessBoard board = ChessBoard.stringToBoard(boardString, ChessColor.BLACK);

                Square kingFrom = board.get(Column.B, 8);

                assertTrue(board.testMoveIsLegal(kingFrom, board.get(Column.B, 7), kingFrom.getPiece()));
                assertFalse(board.testMoveIsLegal(kingFrom, board.get(Column.A, 7), kingFrom.getPiece()));
                assertFalse(board.testMoveIsLegal(kingFrom, board.get(Column.A, 8), kingFrom.getPiece()));
                assertFalse(board.testMoveIsLegal(kingFrom, board.get(Column.C, 8), kingFrom.getPiece()));
        }

        @Test
        void testMoveIsLegalTest2() {
                String boardString = """
                                -k-r---R
                                -np---P-
                                --------
                                -R--B---
                                --------
                                --------
                                --------
                                ------K-""";
                ChessBoard board = ChessBoard.stringToBoard(boardString, ChessColor.BLACK);

                Square kingSquare = board.get(Column.B, 8);
                Square pawnSquare = board.get(Column.B, 7);
                Square knightSquare = board.get(Column.C, 7);
                Square rookSquare = board.get(Column.D, 8);

                assertTrue(board.testMoveIsLegal(kingSquare, board.get(Column.C, 8), kingSquare.getPiece()));
                assertFalse(board.testMoveIsLegal(pawnSquare, board.get(Column.C, 6), pawnSquare.getPiece()));
                assertFalse(board.testMoveIsLegal(pawnSquare, board.get(Column.C, 6), pawnSquare.getPiece()));
                assertFalse(board.testMoveIsLegal(knightSquare, board.get(Column.C, 5), knightSquare.getPiece()));
                assertTrue(board.testMoveIsLegal(rookSquare, board.get(Column.E, 8), rookSquare.getPiece()));
                assertTrue(board.testMoveIsLegal(rookSquare, board.get(Column.H, 8), rookSquare.getPiece()));
                assertFalse(board.testMoveIsLegal(rookSquare, board.get(Column.D, 7), rookSquare.getPiece()));
        }

        @Test
        void testCastlingIsLegal() {
                String boardString = """
                                r---k--r
                                pppqppbp
                                --n-bnp-
                                ---p----
                                ----P---
                                -PNB-N--
                                PBPPQPPP
                                R---K--R""";
                ChessBoard board = ChessBoard.stringToBoard(boardString, ChessColor.WHITE);

                King whiteKing = ((King) board.get(Column.E, 1).getPiece());
                King blackKing = ((King) board.get(Column.E, 8).getPiece());

                assertTrue(whiteKing.getLegalMoves().contains(board.get(Column.G, 1)));
                assertTrue(whiteKing.getLegalMoves().contains(board.get(Column.C, 1)));

                board.movePiece(board.get(Column.E, 1), board.get(Column.G, 1), whiteKing);
                board.updateLegalMoves(ChessColor.BLACK, false);

                assertTrue(blackKing.getLegalMoves().contains(board.get(Column.G, 8)));
                assertTrue(blackKing.getLegalMoves().contains(board.get(Column.C, 8)));

        }

        @Test
        void testCastlingNotLegalAfterMovedKing() {
                String boardString = """
                                r---k--r
                                pppqppbp
                                --n-bnp-
                                ---p----
                                ----P---
                                -PNB-N--
                                PBPPQPPP
                                R---K--R""";
                ChessBoard board = ChessBoard.stringToBoard(boardString, ChessColor.WHITE);

                King whiteKing = ((King) board.get(Column.E, 1).getPiece());
                King blackKing = ((King) board.get(Column.E, 8).getPiece());

                board.movePiece(board.get(Column.E, 1), board.get(Column.F, 1), whiteKing);
                board.movePiece(board.get(Column.E, 8), board.get(Column.F, 8), blackKing);
                board.movePiece(board.get(Column.F, 1), board.get(Column.E, 1), whiteKing);
                board.movePiece(board.get(Column.F, 8), board.get(Column.E, 8), blackKing);

                board.updateLegalMoves(ChessColor.WHITE, false);
                board.updateLegalMoves(ChessColor.BLACK, false);

                assertFalse(whiteKing.getLegalMoves().contains(board.get(Column.G, 1)));
                assertFalse(whiteKing.getLegalMoves().contains(board.get(Column.C, 1)));
                assertFalse(blackKing.getLegalMoves().contains(board.get(Column.G, 8)));
                assertFalse(blackKing.getLegalMoves().contains(board.get(Column.C, 8)));
        }

        @Test
        void testCastlingAfterMovedRook() {
                String boardString = """
                                r---k--r
                                pppqppbp
                                --n-bnp-
                                ---p----
                                ----P---
                                -PNB-N--
                                PBPPQPPP
                                R---K--R""";
                ChessBoard board = ChessBoard.stringToBoard(boardString, ChessColor.WHITE);

                King whiteKing = ((King) board.get(Column.E, 1).getPiece());
                King blackKing = ((King) board.get(Column.E, 8).getPiece());

                board.movePiece(board.get(Column.A, 1), board.get(Column.B, 1), board.get(Column.A, 1).getPiece());
                board.movePiece(board.get(Column.A, 8), board.get(Column.B, 8), board.get(Column.A, 8).getPiece());
                board.movePiece(board.get(Column.B, 1), board.get(Column.A, 1), board.get(Column.B, 1).getPiece());
                board.movePiece(board.get(Column.B, 8), board.get(Column.A, 8), board.get(Column.B, 8).getPiece());

                board.updateLegalMoves(ChessColor.WHITE, false);
                board.updateLegalMoves(ChessColor.BLACK, false);

                assertTrue(whiteKing.getLegalMoves().contains(board.get(Column.G, 1)));
                assertFalse(whiteKing.getLegalMoves().contains(board.get(Column.C, 1)));
                assertTrue(blackKing.getLegalMoves().contains(board.get(Column.G, 8)));
                assertFalse(blackKing.getLegalMoves().contains(board.get(Column.C, 8)));
        }

}
