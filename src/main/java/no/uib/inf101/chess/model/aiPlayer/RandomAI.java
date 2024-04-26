package no.uib.inf101.chess.model.aiPlayer;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.Move;

public class RandomAI extends AIPlayer {

    private ChessBoard board;

    public RandomAI(ChessBoard board) {
        this.board = board;
    }

    @Override
    public ArrayList<Move> getBestMoves() {
        ArrayList<Move> possibleMoves = board.allLegalMoves(board.getToDraw());

        return possibleMoves;
    }
}
