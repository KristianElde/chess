package no.uib.inf101.chess.model.aiPlayer;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.Move;
import no.uib.inf101.chess.model.pieces.CastleablePiece;
import no.uib.inf101.chess.model.pieces.Piece;

public class CarefulAI implements AIPlayer {

    private ChessBoard board;

    public CarefulAI(ChessBoard board) {
        this.board = board;
    }

    @Override
    public Move getBestMove() {
        ArrayList<Move> possibleMoves = board.allLegalMoves(board.getToDraw());
        AggressiveAI aggressiveAI = new AggressiveAI(board);

        Move leadingMove = null;
        int leadingMatVal = -100;

        for (Move candidateMove : possibleMoves) {
            int candidateMoveMatVal = 0;
            boolean isCheck = board.isInCheck(board.getToDraw());
            boolean allowCastling = false;
            if (candidateMove.getPieceToMove() instanceof CastleablePiece)
                allowCastling = ((CastleablePiece) candidateMove.getPieceToMove()).getAllowCastling();

            Piece piece = candidateMove.getPieceToMove();
            Piece capturedPiece = board.movePiece(candidateMove.from(), candidateMove.to(),
                    candidateMove.getPieceToMove());
            int capturedPieceMatVal = (capturedPiece != null ? capturedPiece.getMaterialValue() : 0);

            Piece responseCapturedPiece = aggressiveAI.getBestMove().to().getPiece();
            int responseCapturedPieceMatVal = (responseCapturedPiece != null ? responseCapturedPiece.getMaterialValue()
                    : 0);

            board.undoMove(candidateMove.from(), candidateMove.to(), piece, capturedPiece);
            board.resetStateVariablesAfterMove(candidateMove.from(), candidateMove.to(), piece, capturedPiece, isCheck,
                    allowCastling);

            candidateMoveMatVal += capturedPieceMatVal;
            candidateMoveMatVal -= responseCapturedPieceMatVal;

            if (candidateMoveMatVal > leadingMatVal) {
                leadingMove = candidateMove;
                leadingMatVal = candidateMoveMatVal;
            }
        }

        return leadingMove;
    }

}
