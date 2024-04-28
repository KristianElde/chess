package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

/**
 * The Pawn class represents a pawn chess piece.
 * It extends the Piece class and provides functionality for updating and
 * calculating legal moves for the pawn.
 */
public class Pawn extends Piece {

    private boolean enPassantAllowed = false;
    private boolean capturedByEnPassant = false;

    /**
     * Constructs a new pawn with the specified color.
     *
     * @param color The color of the pawn.
     */
    public Pawn(ChessColor color) {
        super(color, 1);
    }

    @Override
    public ArrayList<Square> calculateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive) {
        ArrayList<Square> legalMoves = new ArrayList<>();

        int nextRow = (getColor() == ChessColor.WHITE ? 1 : -1);
        int startRow = (getColor() == ChessColor.WHITE ? 2 : 7);

        if (!primitive) {
            Square oneAhead = board.get(currentSquare.col(), currentSquare.row() + nextRow);
            if (oneAhead != null && oneAhead.getPiece() == null) {
                legalMoves.add(oneAhead);

                Square twoAhead = board.get(currentSquare.col(), currentSquare.row() + 2 * nextRow);
                if (currentSquare.row() == startRow && twoAhead.getPiece() == null)
                    legalMoves.add(twoAhead);
            }
        }

        Square rightAhead = board.get(currentSquare.col().nextCol(), currentSquare.row() + nextRow);
        if (rightAhead != null && rightAhead.getPiece() != null && rightAhead.getPiece().getColor() != getColor()
                || primitive) {
            legalMoves.add(rightAhead);
        }

        Square leftAhead = board.get(currentSquare.col().prevCol(), currentSquare.row() + nextRow);
        if (leftAhead != null && leftAhead.getPiece() != null && leftAhead.getPiece().getColor() != getColor()
                || primitive) {
            legalMoves.add(leftAhead);
        }

        Square rightEnPassent = board.get(currentSquare.col().nextCol(), currentSquare.row());
        if (rightEnPassent != null && rightEnPassent.getPiece() instanceof Pawn
                && ((Pawn) rightEnPassent.getPiece()).getEnPassantAllowed())
            legalMoves.add(rightAhead);

        Square leftEnPassent = board.get(currentSquare.col().prevCol(), currentSquare.row());
        if (leftEnPassent != null && leftEnPassent.getPiece() instanceof Pawn
                && ((Pawn) leftEnPassent.getPiece()).getEnPassantAllowed())
            legalMoves.add(leftAhead);

        if (!primitive)
            legalMoves = removeInCheckMoves(legalMoves, board, currentSquare);

        return legalMoves;
    }

    public boolean getEnPassantAllowed() {
        return enPassantAllowed;
    }

    public void setEnPassantAllowed(boolean bool) {
        enPassantAllowed = bool;
    }

    public boolean getCaptureByEnPassent() {
        return capturedByEnPassant;
    }

    public void setCapturedByEnPassant(boolean capturedByEnPassent) {
        this.capturedByEnPassant = capturedByEnPassent;
    }

}
