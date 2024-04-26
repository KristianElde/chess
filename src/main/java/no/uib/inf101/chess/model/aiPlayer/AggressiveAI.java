package no.uib.inf101.chess.model.aiPlayer;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.Move;
import no.uib.inf101.chess.model.pieces.Piece;

public class AggressiveAI extends AIPlayer {

    private ChessBoard board;

    public AggressiveAI(ChessBoard board) {
        this.board = board;
    }

    @Override
    public ArrayList<Move> getBestMoves() {
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

        return leadingMoves;
    }

}
