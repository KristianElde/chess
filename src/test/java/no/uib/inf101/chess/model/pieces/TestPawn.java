package no.uib.inf101.chess.model.pieces;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.model.Column;

public class TestPawn {
    @Test
    void pawnLegalMoves() {
        ChessModel model = new ChessModel();

        Pawn pawnE2 = ((Pawn) model.getBoard().get(Column.E, 2).getPiece());
        Pawn pawnC7 = ((Pawn) model.getBoard().get(Column.C, 7).getPiece());

        assertTrue(pawnE2.getLegalMoves().size() == 2);
        assertTrue(pawnE2.getLegalMoves().contains(model.getBoard().get(Column.E, 3)));
        assertTrue(pawnE2.getLegalMoves().contains(model.getBoard().get(Column.E, 4)));

        assertTrue(pawnC7.getLegalMoves().size() == 2);
        assertTrue(pawnC7.getLegalMoves().contains(model.getBoard().get(Column.C, 6)));
        assertTrue(pawnC7.getLegalMoves().contains(model.getBoard().get(Column.C, 5)));
    }

    @Test
    void pawnCaptureMove() {
        ChessModel model = new ChessModel();
    }
}