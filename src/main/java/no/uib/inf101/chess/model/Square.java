package no.uib.inf101.chess.model;

import no.uib.inf101.chess.model.pieces.Piece;

public class Square {
    private Column col;
    private int row;
    private Piece piece;

    public Square(Column col, int row) {
        this.col = col;
        this.row = row;
    }

    public Column col() {
        return col;
    }

    public int row() {
        return row;
    }

    public Piece getPiece() {
        return piece;
    }

    void setPiece(Piece piece) {
        this.piece = piece;
    }
}
