package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

public class Pawn implements IPiece {

    private ChessColor color;
    private ArrayList<Square> legalMoves;

    public Pawn(ChessColor color) {
        this.color = color;
    }

    @Override
    public ArrayList<Square> getLegalMoves() {
        return this.legalMoves;
    }

    @Override
    public void updateLegalMoves(ChessBoard board, Square pos) {
        this.legalMoves = calculateLegalMoves(board, pos);
    }

    @Override
    public ArrayList<Square> calculateLegalMoves(ChessBoard board, Square pos) {
        ArrayList<Square> legalMoves = new ArrayList<>();

        int nextRow = (color == ChessColor.WHITE ? 1 : -1);

        Square oneAhead = board.get(pos.col(), pos.row() + nextRow);
        if (oneAhead.getPiece() == null)
            legalMoves.add(oneAhead);

        Square twoAhead = board.get(pos.col(), pos.row() + 2 * nextRow);
        if (pos.row() == startRow() && twoAhead.getPiece() == null)
            legalMoves.add(twoAhead);

        Square rightAhead = board.get(pos.col().nextCol(), pos.row() + nextRow);
        if (rightAhead != null && rightAhead.getPiece() != null && rightAhead.getPiece().getColor() != color) {
            legalMoves.add(rightAhead);
        }

        Square leftAhead = board.get(pos.col().prevCol(), pos.row() + nextRow);
        if (leftAhead != null && leftAhead.getPiece() != null && leftAhead.getPiece().getColor() != color) {
            legalMoves.add(leftAhead);
        }

        return legalMoves;
    }

    @Override
    public ChessColor getColor() {
        return this.color;
    }

    private int startRow() {
        return (this.color == ChessColor.WHITE ? 2 : 7);
    }

}
