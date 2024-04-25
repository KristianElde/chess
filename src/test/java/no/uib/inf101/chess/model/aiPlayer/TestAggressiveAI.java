package no.uib.inf101.chess.model.aiPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.ChessModel;
import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.Move;
import no.uib.inf101.chess.model.Option;

public class TestAggressiveAI {

    @Test
    void AggresiveAITest() {
        String boardString = """
                rnb-kbnr
                ppp--ppp
                ----p---
                ---p-q--
                ---P-N--
                ----P---
                PPP-NPPP
                RNBQKB-R""";
        ChessModel model = new ChessModel(boardString, ChessColor.BLACK);
        model.setSelectedOption(Option.DIFFICULTY);
        model.toggleSelectedOption(true);
        assertTrue(model.getAiPlayer() instanceof AggressiveAI);

        Move aiMove = model.getAiPlayer().getBestMove();
        assertNotNull(aiMove);
        assertEquals(model.getBoard().get(Column.F, 5), aiMove.from());
        assertEquals(model.getBoard().get(Column.F, 4), aiMove.to());
    }
}
