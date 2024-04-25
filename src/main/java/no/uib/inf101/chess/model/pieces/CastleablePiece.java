package no.uib.inf101.chess.model.pieces;

import no.uib.inf101.chess.model.ChessColor;

/**
 * The CastleablePiece class represents a chess piece that can participate in
 * castling.
 * It extends the Piece class and provides functionality for allowing or
 * disallowing castling for the piece.
 */
public abstract class CastleablePiece extends Piece {

    /**
     * Indicates whether castling is allowed for the piece.
     */
    boolean allowCastling = true;

    /**
     * Constructs a new castleable piece with the specified color and material
     * value.
     *
     * @param color         The color of the piece.
     * @param materialValue The material value of the piece.
     */
    public CastleablePiece(ChessColor color, int materialValue) {
        super(color, materialValue);
    }

    /**
     * Retrieves whether castling is allowed for the piece.
     *
     * @return True if castling is allowed, false otherwise.
     */
    public boolean getAllowCastling() {
        return allowCastling;
    }

    /**
     * Sets whether castling is allowed for the piece.
     *
     * @param allowCastling True to allow castling, false to disallow castling.
     */
    public void setAllowCastling(boolean allowCastling) {
        this.allowCastling = allowCastling;
    }

}
