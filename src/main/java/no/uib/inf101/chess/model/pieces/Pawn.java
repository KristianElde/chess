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

        Square oneAhead = board.get(pos.col(), pos.row() + 1);
        if (oneAhead.getPiece() == null)
            legalMoves.add(oneAhead);

        if (pos.row() == startRow()) {
            Square twoAhead = board.get(pos.col(), pos.row() + 2);
            legalMoves.add(twoAhead);
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
