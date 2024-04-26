package no.uib.inf101.chess.model.aiPlayer;

import java.util.ArrayList;
import java.util.Random;

import no.uib.inf101.chess.model.Move;

public abstract class AIPlayer implements AIPlayerI {

    public Move getMove() {
        ArrayList<Move> bestMoves = getBestMoves();

        Random random = new Random();
        int randomIndex = random.nextInt(bestMoves.size());

        return bestMoves.get(randomIndex);
    }

}
