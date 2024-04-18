package no.uib.inf101.chess.view.design;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.pieces.Bishop;
import no.uib.inf101.chess.model.pieces.King;
import no.uib.inf101.chess.model.pieces.Knight;
import no.uib.inf101.chess.model.pieces.Pawn;
import no.uib.inf101.chess.model.pieces.Piece;
import no.uib.inf101.chess.model.pieces.Queen;
import no.uib.inf101.chess.model.pieces.Rook;

public class StarWarsTextureTheme implements TextureTheme {

    @Override
    public String getImgPath(Piece piece) {
        ChessColor color = piece.getColor();
        if (piece instanceof Pawn) {
            return (color == ChessColor.WHITE ? "c3po.png" : "clone.png");
        }
        if (piece instanceof Bishop) {
            return (color == ChessColor.WHITE ? "leia.png" : "blackClone.png");
        }
        if (piece instanceof Knight) {
            return (color == ChessColor.WHITE ? "chewbacca.png" : "bobba.png");
        }
        if (piece instanceof Rook) {
            return (color == ChessColor.WHITE ? "solo.png" : "maul.png");
        }
        if (piece instanceof King) {
            return (color == ChessColor.WHITE ? "yoda.png" : "palpatine.png");
        }
        if (piece instanceof Queen) {
            return (color == ChessColor.WHITE ? "luke.png" : "vader.png");
        }

        throw new IllegalArgumentException("Illegal argumemt: " + piece);
    }

}
