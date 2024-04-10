package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

public abstract class Piece implements MoveablePiece {

    private ArrayList<Square> legalMoves;
    private ChessColor color;

    public Piece(ChessColor color) {
        this.color = color;
    }

    public ArrayList<Square> getLegalMoves() {
        return legalMoves;
    }

    public ChessColor getColor() {
        return color;
    }

    public void setLegalMoves(ArrayList<Square> legalMoves) {
        this.legalMoves = legalMoves;
    }

}
