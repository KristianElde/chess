package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

public interface IPiece {
    ArrayList<Square> getLegalMoves();

    void updateLegalMoves();

    ChessColor getColor();

}
