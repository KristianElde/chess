package no.uib.inf101.chess.view.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.pieces.Bishop;
import no.uib.inf101.chess.model.pieces.King;
import no.uib.inf101.chess.model.pieces.Knight;
import no.uib.inf101.chess.model.pieces.Pawn;
import no.uib.inf101.chess.model.pieces.Queen;
import no.uib.inf101.chess.model.pieces.Rook;

public class TestTextureTheme {

    @Test
    void sanityDefaultTextureThemeTest() {
        TextureTheme textures = new DefaultTextureTheme();
        assertEquals("wP.png", textures.getImgPath(new Pawn(ChessColor.WHITE)));
        assertEquals("bP.png", textures.getImgPath(new Pawn(ChessColor.BLACK)));
        assertEquals("wN.png", textures.getImgPath(new Knight(ChessColor.WHITE)));
        assertEquals("bN.png", textures.getImgPath(new Knight(ChessColor.BLACK)));
        assertEquals("wB.png", textures.getImgPath(new Bishop(ChessColor.WHITE)));
        assertEquals("bB.png", textures.getImgPath(new Bishop(ChessColor.BLACK)));
        assertEquals("wR.png", textures.getImgPath(new Rook(ChessColor.WHITE)));
        assertEquals("bR.png", textures.getImgPath(new Rook(ChessColor.BLACK)));
        assertEquals("wQ.png", textures.getImgPath(new Queen(ChessColor.WHITE)));
        assertEquals("bQ.png", textures.getImgPath(new Queen(ChessColor.BLACK)));
        assertEquals("wK.png", textures.getImgPath(new King(ChessColor.WHITE)));
        assertEquals("bK.png", textures.getImgPath(new King(ChessColor.BLACK)));
    }

    @Test
    void sanityStarWarsTextureThemeTest() {
        TextureTheme textures = new StarWarsTextureTheme();
        assertEquals("c3po.png", textures.getImgPath(new Pawn(ChessColor.WHITE)));
        assertEquals("clone.png", textures.getImgPath(new Pawn(ChessColor.BLACK)));
        assertEquals("chewbacca.png", textures.getImgPath(new Knight(ChessColor.WHITE)));
        assertEquals("bobba.png", textures.getImgPath(new Knight(ChessColor.BLACK)));
        assertEquals("leia.png", textures.getImgPath(new Bishop(ChessColor.WHITE)));
        assertEquals("blackClone.png", textures.getImgPath(new Bishop(ChessColor.BLACK)));
        assertEquals("solo.png", textures.getImgPath(new Rook(ChessColor.WHITE)));
        assertEquals("maul.png", textures.getImgPath(new Rook(ChessColor.BLACK)));
        assertEquals("luke.png", textures.getImgPath(new Queen(ChessColor.WHITE)));
        assertEquals("vader.png", textures.getImgPath(new Queen(ChessColor.BLACK)));
        assertEquals("yoda.png", textures.getImgPath(new King(ChessColor.WHITE)));
        assertEquals("palpatine.png", textures.getImgPath(new King(ChessColor.BLACK)));
    }
}
