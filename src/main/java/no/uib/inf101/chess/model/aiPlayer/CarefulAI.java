package no.uib.inf101.chess.model.aiPlayer;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.Move;
import no.uib.inf101.chess.model.pieces.CastleablePiece;
import no.uib.inf101.chess.model.pieces.Piece;

public class CarefulAI extends AIPlayer {

    private ChessBoard board;

    public CarefulAI(ChessBoard board) {
        this.board = board;
    }

    @Override
    public ArrayList<Move> getBestMoves() {
        ArrayList<Move> possibleMoves = board.allLegalMoves(board.getToDraw());
        AggressiveAI aggressiveAI = new AggressiveAI(board);

        ArrayList<Move> leadingMoves = new ArrayList<>();
        int leadingMatVal = -100;

        for (Move candidateMove : possibleMoves) {
            int candidateMoveMatVal = 0;
            boolean isCheck = board.isInCheck(board.getToDraw());
            boolean allowCastling = false;
            if (candidateMove.getPieceToMove() instanceof CastleablePiece)
                allowCastling = ((CastleablePiece) candidateMove.getPieceToMove()).getAllowCastling();

            Piece piece = candidateMove.getPieceToMove();
            Piece capturedPiece = board.movePiece(candidateMove.from(), candidateMove.to(), piece);
            int capturedPieceMatVal = (capturedPiece != null ? capturedPiece.getMaterialValue() : 0);

            int responseCapturedPieceMatVal = 0;
            ArrayList<Move> responseMoves = aggressiveAI.getBestMoves();
            if (responseMoves.isEmpty())
                responseCapturedPieceMatVal = -100;
            else if (responseMoves.get(0).to().getPiece() != null)
                responseCapturedPieceMatVal = responseMoves.get(0).to().getPiece().getMaterialValue();

            board.undoMove(candidateMove.from(), candidateMove.to(), piece, capturedPiece);
            board.resetStateVariablesAfterMove(candidateMove.from(), candidateMove.to(), piece, capturedPiece, isCheck,
                    allowCastling);

            candidateMoveMatVal += capturedPieceMatVal;
            candidateMoveMatVal -= responseCapturedPieceMatVal;

            if (candidateMoveMatVal > leadingMatVal) {
                leadingMoves.clear();
                leadingMoves.add(candidateMove);
                leadingMatVal = candidateMoveMatVal;
            } else if (candidateMoveMatVal == leadingMatVal) {
                leadingMoves.add(candidateMove);
            }
        }

        return leadingMoves;
    }

}
