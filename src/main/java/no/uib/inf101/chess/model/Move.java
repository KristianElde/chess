package no.uib.inf101.chess.model;

import no.uib.inf101.chess.model.pieces.Piece;

public record Move(Square from, Square to) {

    public Piece getPieceToMove() {
        return from.getPiece();
    }
}
