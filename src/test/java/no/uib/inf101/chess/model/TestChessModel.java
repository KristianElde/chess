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
        assertEquals(GameState.ACTIVE, model.getGameState());
        assertEquals(null, model.getSelectedSquare());
        assertEquals(null, model.getLastMoveFrom());
        assertEquals(null, model.getLastMoveTo());
        assertEquals(null, model.getWinner());
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

    @Test
    void checkMateTest() {
        ChessModel model = new ChessModel();

        // Move white pawn to E4
        model.setSelectedSquare(model.getBoard().get(Column.E, 2));
        model.setSelectedSquare(model.getBoard().get(Column.E, 4));

        // Move black pawn to E5
        model.setSelectedSquare(model.getBoard().get(Column.E, 7));
        model.setSelectedSquare(model.getBoard().get(Column.E, 5));

        // Move white bishop to E4
        model.setSelectedSquare(model.getBoard().get(Column.F, 1));
        model.setSelectedSquare(model.getBoard().get(Column.C, 4));

        // Move black pawn to D6
        model.setSelectedSquare(model.getBoard().get(Column.D, 7));
        model.setSelectedSquare(model.getBoard().get(Column.D, 6));

        // Move white queen to H5
        model.setSelectedSquare(model.getBoard().get(Column.D, 1));
        model.setSelectedSquare(model.getBoard().get(Column.H, 5));

        // Move black knight to E4
        model.setSelectedSquare(model.getBoard().get(Column.G, 8));
        model.setSelectedSquare(model.getBoard().get(Column.F, 6));

        // Move white queen to F7 and set black checkmate
        model.setSelectedSquare(model.getBoard().get(Column.H, 5));
        model.setSelectedSquare(model.getBoard().get(Column.F, 7));

        assertEquals(GameState.CHECKMATE, model.getGameState());
        assertEquals(ChessColor.WHITE, model.getWinner());
    }
}
