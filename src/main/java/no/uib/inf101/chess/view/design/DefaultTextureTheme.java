package no.uib.inf101.chess.view.design;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.pieces.IPiece;
import no.uib.inf101.chess.model.pieces.Pawn;
import no.uib.inf101.chess.model.pieces.Rook;

public class DefaultTextureTheme implements TextureTheme {

    @Override
    public String getImgPath(IPiece piece) {
        ChessColor color = piece.getColor();
        if (piece instanceof Pawn) {
            return (color == ChessColor.WHITE ? "../../../resources/wP.png" : "../../../resources/bP.png");
        }
        if (piece instanceof Rook) {
            return (color == ChessColor.WHITE ? "../../../resources/wR.png" : "../../../resources/bR.png");
        }

        throw new IllegalArgumentException("Illegal argumemt: " + piece);
    }
}
