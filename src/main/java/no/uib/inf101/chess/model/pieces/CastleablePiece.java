package no.uib.inf101.chess.model.pieces;

import no.uib.inf101.chess.model.ChessColor;

public abstract class CastleablePiece extends Piece {

    protected boolean allowCastling = true;

    public CastleablePiece(ChessColor color, int materialValue) {
        super(color, materialValue);
    }

    public boolean getAllowCastling() {
        return allowCastling;
    }

    public void setAllowCastling(boolean allowCastling) {
        this.allowCastling = allowCastling;
    }

}
