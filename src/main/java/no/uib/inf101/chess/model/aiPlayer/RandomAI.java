package no.uib.inf101.chess.model.aiPlayer;

import java.util.ArrayList;
import java.util.Random;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.Move;

public class RandomAI implements AIPlayer {

    private ChessBoard board;

    public RandomAI(ChessBoard board) {
        this.board = board;
    } 

    @Override
    public Move getBestMove() {
        ArrayList<Move> possibleMoves = board.allLegalMoves(board.getToDraw());
        Random random = new Random();
        int randomIndex = random.nextInt(possibleMoves.size());

        return possibleMoves.get(randomIndex);
    }
}
