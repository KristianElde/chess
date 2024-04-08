package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

public class Rook implements IPiece {

    private ChessColor color;
    private ArrayList<Square> legalMoves;

    public Rook(ChessColor color) {
        this.color = color;
    }

    @Override
    public ArrayList<Square> getLegalMoves() {
        return this.legalMoves;
    }

    @Override
    public void updateLegalMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateLegalMoves'");
    }

    @Override
    public ChessColor getColor() {
        return this.color;
    }

}
