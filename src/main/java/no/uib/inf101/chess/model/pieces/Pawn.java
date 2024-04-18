package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

public class Pawn extends Piece {

    private boolean enPassentAllowed = false;
    private boolean capturedByEnPassent = false;

    public Pawn(ChessColor color) {
        super(color);
    }

    @Override
    public void updateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive) {
        legalMoves = calculateLegalMoves(board, currentSquare, primitive);
        this.enPassentAllowed = false;
    }

    @Override
    public ArrayList<Square> calculateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive) {
        ArrayList<Square> legalMoves = new ArrayList<>();

        int nextRow = (getColor() == ChessColor.WHITE ? 1 : -1);
        int startRow = (getColor() == ChessColor.WHITE ? 2 : 7);

        Square oneAhead = board.get(currentSquare.col(), currentSquare.row() + nextRow);
        if (oneAhead != null && oneAhead.getPiece() == null) {
            legalMoves.add(oneAhead);

            Square twoAhead = board.get(currentSquare.col(), currentSquare.row() + 2 * nextRow);
            if (currentSquare.row() == startRow && twoAhead.getPiece() == null)
                legalMoves.add(twoAhead);
        }

        Square rightAhead = board.get(currentSquare.col().nextCol(), currentSquare.row() + nextRow);
        if (rightAhead != null && rightAhead.getPiece() != null && rightAhead.getPiece().getColor() != getColor()) {
            legalMoves.add(rightAhead);
        }

        Square leftAhead = board.get(currentSquare.col().prevCol(), currentSquare.row() + nextRow);
        if (leftAhead != null && leftAhead.getPiece() != null && leftAhead.getPiece().getColor() != getColor()) {
            legalMoves.add(leftAhead);
        }

        Square rightEnPassent = board.get(currentSquare.col().nextCol(), currentSquare.row());
        if (rightEnPassent != null && rightEnPassent.getPiece() instanceof Pawn
                && ((Pawn) rightEnPassent.getPiece()).getEnPassentAllowed())
            legalMoves.add(rightAhead);

        Square leftEnPassent = board.get(currentSquare.col().prevCol(), currentSquare.row());
        if (leftEnPassent != null && leftEnPassent.getPiece() instanceof Pawn
                && ((Pawn) leftEnPassent.getPiece()).getEnPassentAllowed())
            legalMoves.add(leftAhead);

        if (!primitive)
            legalMoves = removeInCheckMoves(legalMoves, board, currentSquare);

        return legalMoves;
    }

    public boolean getEnPassentAllowed() {
        return enPassentAllowed;
    }

    public void setEnPassentAllowed(boolean bool) {
        enPassentAllowed = bool;
    }

    public boolean getCaptureByEnPassent() {
        return capturedByEnPassent;
    }

    public void setCapturedByEnPassent(boolean capturedByEnPassent) {
        this.capturedByEnPassent = capturedByEnPassent;
    }

}
