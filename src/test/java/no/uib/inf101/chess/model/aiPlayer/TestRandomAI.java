package no.uib.inf101.chess.model.aiPlayer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.Move;

public class TestRandomAI {

    @Test
    void randomAITest() {
        ChessModel model = new ChessModel();
        assertTrue(model.isAiOpposition());
        assertTrue(model.getAiPlayer() instanceof RandomAI);

        model.setSelectedSquare(model.getBoard().get(Column.D, 2));
        model.setSelectedSquare(model.getBoard().get(Column.D, 4));
        assertTrue(model.getBoard().getToDraw() == ChessColor.BLACK);

        Move aiMove = model.getAiPlayer().getBestMove();

        assertNotNull(aiMove);
    }
}
