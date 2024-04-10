package no.uib.inf101.chess.view.design;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.pieces.Bishop;
import no.uib.inf101.chess.model.pieces.Piece;
import no.uib.inf101.chess.model.pieces.King;
import no.uib.inf101.chess.model.pieces.Knight;
import no.uib.inf101.chess.model.pieces.Pawn;
import no.uib.inf101.chess.model.pieces.Queen;
import no.uib.inf101.chess.model.pieces.Rook;

public class DefaultTextureTheme implements TextureTheme {

    @Override
    public String getImgPath(Piece piece) {
        ChessColor color = piece.getColor();
        if (piece instanceof Pawn) {
            return (color == ChessColor.WHITE ? "wP.png" : "bP.png");
        }
        if (piece instanceof Bishop) {
            return (color == ChessColor.WHITE ? "wB.png" : "bB.png");
        }
        if (piece instanceof Knight) {
            return (color == ChessColor.WHITE ? "wN.png" : "bN.png");
        }
        if (piece instanceof Rook) {
            return (color == ChessColor.WHITE ? "wR.png" : "bR.png");
        }
        if (piece instanceof King) {
            return (color == ChessColor.WHITE ? "wK.png" : "bK.png");
        }
        if (piece instanceof Queen) {
            return (color == ChessColor.WHITE ? "wQ.png" : "bQ.png");
        }

        throw new IllegalArgumentException("Illegal argumemt: " + piece);
    }
}
