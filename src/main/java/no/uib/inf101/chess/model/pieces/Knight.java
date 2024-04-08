package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

public class Knight implements IPiece {

    private ChessColor color;
    private ArrayList<Square> legalMoves;

    public Knight(ChessColor color) {
        this.color = color;
    }

    @Override
    public ArrayList<Square> getLegalMoves() {
        return this.legalMoves;
    }

    @Override
    public void updateLegalMoves(ChessBoard board, Square pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateLegalMoves'");
    }

    @Override
    public ChessColor getColor() {
        return this.color;
    }

    @Override
    public ArrayList<Square> calculateLegalMoves(ChessBoard board, Square pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateLegalMoves'");
    }

}