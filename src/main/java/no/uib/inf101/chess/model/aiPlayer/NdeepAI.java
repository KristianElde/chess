package no.uib.inf101.chess.model.aiPlayer;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.Move;
import no.uib.inf101.chess.model.pieces.CastleablePiece;
import no.uib.inf101.chess.model.pieces.Piece;

public class NdeepAI implements AIPlayer {

    private ChessBoard board;
    private int depth;

    public NdeepAI(ChessBoard board, int depth) {
        this.board = board;
        this.depth = depth;
    }

    @Override
    public Move getBestMove() {
        ArrayList<Move> possibleMoves = board.allLegalMoves(board.getToDraw());
        Move leadingMove = possibleMoves.get(0);
        int leadingMaterialValue = 0;

        for (Move candidateMove : possibleMoves) {

            int candidateMoveMaterialValue = testBranch(candidateMove, depth, board);

            if (candidateMoveMaterialValue > leadingMaterialValue)
                leadingMove = candidateMove;
        }

        return leadingMove;
    }

    private int testBranch(Move candidateMove, int depth, ChessBoard board) {
        AggressiveAI aggresiveAI = new AggressiveAI(board);

        int branchMaterialValue = 0;
        boolean isCheck = board.isInCheck(board.getToDraw());
        boolean allowCastling = false;
        if (candidateMove.getPieceToMove() instanceof CastleablePiece)
            allowCastling = ((CastleablePiece) candidateMove.getPieceToMove()).getAllowCastling();
        Piece capturedPiece = board.movePiece(candidateMove.from(), candidateMove.to(), candidateMove.getPieceToMove());
        if (capturedPiece != null) {
            branchMaterialValue += capturedPiece.getMaterialValue();
        }
        Move responseMove = aggresiveAI.getBestMove();
        boolean isCheckBeforeResponse = board.isInCheck(board.getToDraw());
        boolean allowCastlingBeforeResponse = false;
        if (candidateMove.getPieceToMove() instanceof CastleablePiece)
            allowCastlingBeforeResponse = ((CastleablePiece) candidateMove.getPieceToMove()).getAllowCastling();
        Piece responseCapturedPiece = board.movePiece(responseMove.from(), responseMove.to(),
                responseMove.getPieceToMove());
        if (capturedPiece != null) {
            branchMaterialValue += (-1) * capturedPiece.getMaterialValue();
        }

        if (depth > 0) {
            Move move = getBestMove();
            if (move.to().getPiece() != null) {
                branchMaterialValue += move.to().getPiece().getMaterialValue();
            }
        }

        board.undoMove(responseMove.from(), responseMove.to(), responseMove.from().getPiece(), responseCapturedPiece);
        board.resetStateVariablesAfterMove(responseMove.from(), responseMove.to(), responseMove.from().getPiece(),
                responseCapturedPiece, isCheckBeforeResponse, allowCastlingBeforeResponse);
        board.undoMove(candidateMove.from(), candidateMove.to(), candidateMove.from().getPiece(), capturedPiece);
        board.resetStateVariablesAfterMove(candidateMove.from(), candidateMove.to(), candidateMove.from().getPiece(),
                capturedPiece, isCheck, allowCastling);

        return branchMaterialValue;
    }
}
