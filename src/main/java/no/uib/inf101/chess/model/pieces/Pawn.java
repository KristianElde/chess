package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

public class Pawn implements IPiece {

    ChessColor color;
    ArrayList<Square> legalMoves;

    public Pawn(ChessColor color) {
        this.color = color;
    }

    @Override
    public ArrayList<Square> getLegalMoves() {
        return this.legalMoves;
    }

    @Override
    public void updateLegalMoves() {

    }

    @Override
    public ChessColor getColor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getColor'");
    }

}
