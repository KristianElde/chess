package no.uib.inf101.chess.model;

import no.uib.inf101.chess.model.pieces.Piece;

/**
 * The Square class represents a square on the chess board.
 * Each square has a column, a row, and may contain a piece.
 */
public class Square {
    private Column col;
    private int row;
    private Piece piece;

    /**
     * Constructs a new square with the specified column and row.
     *
     * @param col The column of the square.
     * @param row The row of the square.
     */
    public Square(Column col, int row) {
        this.col = col;
        this.row = row;
    }

    /**
     * Retrieves the column of the square.
     *
     * @return The column of the square.
     */
    public Column col() {
        return col;
    }

    /**
     * Retrieves the row of the square.
     *
     * @return The row of the square.
     */
    public int row() {
        return row;
    }

    /**
     * Retrieves the piece currently occupying the square.
     *
     * @return The piece on the square, or null if the square is empty.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Sets the piece on the square.
     *
     * @param piece The piece to set on the square.
     */
    void setPiece(Piece piece) {
        this.piece = piece;
    }
}
