package no.uib.inf101.chess.view.design;

import no.uib.inf101.chess.model.pieces.Piece;

/**
 * The TextureTheme interface defines a method for obtaining the image path
 * associated with a chess piece for displaying textures.
 */
public interface TextureTheme {

    /**
     * Returns the image path associated with a specific chess piece.
     * 
     * @param piece The chess piece.
     * @return The image path for the given chess piece.
     */
    String getImgPath(Piece piece);
}
