package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

public class Pawn implements IPiece {

    private ChessColor color;
    private ArrayList<Square> legalMoves;
    private boolean enPassentAllowed = false;

    public Pawn(ChessColor color) {
        this.color = color;
    }

    @Override
    public ArrayList<Square> getLegalMoves() {
        return this.legalMoves;
    }

    @Override
    public void updateLegalMoves(ChessBoard board, Square currentSquare) {
        this.legalMoves = calculateLegalMoves(board, currentSquare);
    }

    @Override
    public ArrayList<Square> calculateLegalMoves(ChessBoard board, Square currentSquare) {
        ArrayList<Square> legalMoves = new ArrayList<>();

        int nextRow = (color == ChessColor.WHITE ? 1 : -1);

        Square oneAhead = board.get(currentSquare.col(), currentSquare.row() + nextRow);
        if (oneAhead.getPiece() == null) {
            legalMoves.add(oneAhead);

            Square twoAhead = board.get(currentSquare.col(), currentSquare.row() + 2 * nextRow);
            if (currentSquare.row() == startRow() && twoAhead.getPiece() == null)
                legalMoves.add(twoAhead);
        }

        Square rightAhead = board.get(currentSquare.col().nextCol(), currentSquare.row() + nextRow);
        if (rightAhead != null && rightAhead.getPiece() != null && rightAhead.getPiece().getColor() != color) {
            legalMoves.add(rightAhead);
        }

        Square leftAhead = board.get(currentSquare.col().prevCol(), currentSquare.row() + nextRow);
        if (leftAhead != null && leftAhead.getPiece() != null && leftAhead.getPiece().getColor() != color) {
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

        return legalMoves;
    }

    @Override
    public ChessColor getColor() {
        return this.color;
    }

    public boolean getEnPassentAllowed() {
        return this.enPassentAllowed;
    }

    public void setEnPassentAllowed(boolean bool) {
        this.enPassentAllowed = bool;
    }

    private int startRow() {
        return (this.color == ChessColor.WHITE ? 2 : 7);
    }

}
