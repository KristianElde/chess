package no.uib.inf101.chess.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.pieces.Pawn;

public class TestChessModel {

    @Test
    void constructorTest() {
        ChessModel model = new ChessModel();

        assertNotNull(model);
        assertNotNull(model.getBoard());
    }

    @Test
    void setSelectedSquareTest() {
        ChessModel model = new ChessModel();
        Square newSelectedSquare = model.getBoard().get(Column.E, 2);

        model.setSelectedSquare(newSelectedSquare);
        assertEquals(newSelectedSquare, model.getSelectedSquare());

    }

    @Test
    void legalMoveTest() {
        ChessModel model = new ChessModel();
        Square newSelectedSquare = model.getBoard().get(Column.E, 2);
        model.setSelectedSquare(newSelectedSquare);

        newSelectedSquare = model.getBoard().get(Column.E, 4);
        model.setSelectedSquare(newSelectedSquare);

        assertEquals(null, model.getSelectedSquare());
        assertTrue(model.getBoard().get(Column.E, 4).getPiece() instanceof Pawn);
        assertTrue(model.getBoard().get(Column.E, 4).getPiece().getColor() == ChessColor.WHITE);
        assertEquals(ChessColor.BLACK, model.getBoard().getToDraw());
    }

    @Test
    void illegalMoveTest() {
        ChessModel model = new ChessModel();
        Square newSelectedSquare = model.getBoard().get(Column.E, 2);
        model.setSelectedSquare(newSelectedSquare);

        newSelectedSquare = model.getBoard().get(Column.D, 3);
        model.setSelectedSquare(newSelectedSquare);

        assertEquals(null, model.getSelectedSquare());
        assertEquals(null, model.getBoard().get(Column.D, 3).getPiece());
        assertEquals(ChessColor.WHITE, model.getBoard().getToDraw());
    }

    @Test
    void latestMoveTest() {
        ChessModel model = new ChessModel();
        Square moveFromSquare = model.getBoard().get(Column.B, 1);
        model.setSelectedSquare(moveFromSquare);

        Square moveToSquare = model.getBoard().get(Column.C, 3);
        model.setSelectedSquare(moveToSquare);

        assertEquals(moveFromSquare, model.getLastMoveFrom());
        assertEquals(moveToSquare, model.getLastMoveTo());
    }
}
