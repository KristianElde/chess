package no.uib.inf101.chess.model;

import no.uib.inf101.chess.model.pieces.IPiece;

public class Square {
    private Column col;
    private int row;
    private IPiece piece;

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

    public IPiece getPiece() {
        return piece;
    }

    void setPiece(IPiece piece) {
        this.piece = piece;
    }
}
