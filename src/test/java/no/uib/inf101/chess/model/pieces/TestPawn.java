package no.uib.inf101.chess.model.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.Square;

public class TestPawn {
    @Test
    void pawnLegalMovesTest() {
        ChessModel model = new ChessModel();

        Pawn pawnE2 = ((Pawn) model.getBoard().get(Column.E, 2).getPiece());
        Pawn pawnC7 = ((Pawn) model.getBoard().get(Column.C, 7).getPiece());

        assertEquals(2, pawnE2.getLegalMoves().size());
        assertTrue(pawnE2.getLegalMoves().contains(model.getBoard().get(Column.E, 3)));
        assertTrue(pawnE2.getLegalMoves().contains(model.getBoard().get(Column.E, 4)));

        assertEquals(2, pawnC7.getLegalMoves().size());
        assertTrue(pawnC7.getLegalMoves().contains(model.getBoard().get(Column.B, 6)));
        assertTrue(pawnC7.getLegalMoves().contains(model.getBoard().get(Column.D, 6)));
    }

    @Test
    void pawnCaptureMove() {
        ChessModel model = new ChessModel();

        model.setSelectedSquare(model.getBoard().get(Column.D, 2));
        model.setSelectedSquare(model.getBoard().get(Column.D, 4));

        model.setSelectedSquare(model.getBoard().get(Column.E, 7));
        model.setSelectedSquare(model.getBoard().get(Column.E, 5));

        Square pawnSquare = model.getBoard().get(Column.D, 4);
        Piece pawn = pawnSquare.getPiece();

        assertTrue(pawn instanceof Pawn && pawn.getColor() == ChessColor.WHITE);

        // Check if capture move is legal move
        assertTrue(pawn.getLegalMoves().contains(model.getBoard().get(Column.E, 5)));

        // Check if move one ahead is a legal move
        assertTrue(pawn.getLegalMoves().contains(model.getBoard().get(Column.D, 5)));

        // Check that there is no more legal moves
        assertEquals(2, pawn.getLegalMoves().size());
    }

    @Test
    void pawnEnPassentMove() {
        ChessModel model = new ChessModel();

        model.setSelectedSquare(model.getBoard().get(Column.D, 2));
        model.setSelectedSquare(model.getBoard().get(Column.D, 4));

        model.setSelectedSquare(model.getBoard().get(Column.E, 7));
        model.setSelectedSquare(model.getBoard().get(Column.E, 5));

        model.setSelectedSquare(model.getBoard().get(Column.D, 4));
        model.setSelectedSquare(model.getBoard().get(Column.D, 5));

        model.setSelectedSquare(model.getBoard().get(Column.C, 7));
        model.setSelectedSquare(model.getBoard().get(Column.C, 5));

        Square pawnSquare = model.getBoard().get(Column.D, 5);
        Piece pawn = pawnSquare.getPiece();

        assertTrue(pawn instanceof Pawn && pawn.getColor() == ChessColor.WHITE);

        // Check if move one ahead is a legal move
        assertTrue(pawn.getLegalMoves().contains(model.getBoard().get(Column.D, 6)));

        // Check if enPassent is a legal move
        assertTrue(pawn.getLegalMoves().contains(model.getBoard().get(Column.C, 6)));

        // Check that there is no more legal moves
        assertEquals(2, pawn.getLegalMoves().size());
    }

    @Test
    void pawnPromotionMove() {
        String boardString = """
                --------
                --P-----
                -K------
                -------k
                -----p--
                --------
                --------
                --------""";
        ChessModel model = new ChessModel(boardString, ChessColor.WHITE);

        model.setSelectedSquare(model.getBoard().get(Column.C, 7));
        model.setSelectedSquare(model.getBoard().get(Column.C, 8));

        // Check that pawn moved to C8 is promoted to a white queen
        assertTrue(model.getBoard().get(Column.C, 8).getPiece() instanceof Queen
                && model.getBoard().get(Column.C, 8).getPiece().getColor() == ChessColor.WHITE);
    }

}
