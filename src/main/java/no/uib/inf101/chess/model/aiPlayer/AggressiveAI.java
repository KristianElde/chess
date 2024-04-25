package no.uib.inf101.chess.model.aiPlayer;

import java.util.ArrayList;
import java.util.Random;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.Move;
import no.uib.inf101.chess.model.pieces.Piece;

public class AggressiveAI implements AIPlayer {

    private ChessBoard board;

    public AggressiveAI(ChessBoard board) {
        this.board = board;
    }

    @Override
    public Move getBestMove() {
        ArrayList<Move> possibleMoves = board.allLegalMoves(board.getToDraw());
        ArrayList<Move> leadingMoves = new ArrayList<>();
        int leadingMaterialValue = 0;
        for (Move candidateMove : possibleMoves) {
            Piece capturedPiece = candidateMove.to().getPiece();
            int capturedMaterialValue = (capturedPiece != null ? capturedPiece.getMaterialValue() : 0);

            if (capturedMaterialValue > leadingMaterialValue) {
                leadingMoves.clear();
                leadingMoves.add(candidateMove);
                leadingMaterialValue = capturedMaterialValue;
            } else if (capturedMaterialValue == leadingMaterialValue) {
                leadingMoves.add(candidateMove);
            }
        }

        Random random = new Random();
        int randomIndex = random.nextInt(leadingMoves.size());

        return leadingMoves.get(randomIndex);
    }

}
