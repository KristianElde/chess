package no.uib.inf101.chess.model;

import no.uib.inf101.chess.model.pieces.Piece;

/**
 * The Move class represents a move made on the chess board.
 * It consists of a source square (from) and a destination square (to).
 * This class provides a method to retrieve the piece to be moved from the
 * source square.
 */
public record Move(Square from, Square to) {

    /**
     * Retrieves the piece to be moved from the source square.
     *
     * @return The piece to be moved.
     */
    public Piece getPieceToMove() {
        return from.getPiece();
    }
}
