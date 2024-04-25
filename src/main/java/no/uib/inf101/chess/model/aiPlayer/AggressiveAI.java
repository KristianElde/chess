package no.uib.inf101.chess.model.aiPlayer;

import java.util.ArrayList;

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
        Move leadingMove = possibleMoves.get(0);
        int leadingMaterialValue = 0;
        for (Move candidateMove : possibleMoves) {
            Piece capturedPiece = candidateMove.to().getPiece();
            int capturedMaterialValue = (capturedPiece != null ? capturedPiece.getMaterialValue() : 0);

            if (capturedMaterialValue > leadingMaterialValue) {
                leadingMove = candidateMove;
                leadingMaterialValue = capturedMaterialValue;
            }
        }

        return leadingMove;
    }

}
