package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

public interface IPiece {
    ArrayList<Square> getLegalMoves();

    void updateLegalMoves(ChessBoard board, Square currentSquare);

    ArrayList<Square> calculateLegalMoves(ChessBoard board, Square currentSquare);

    ChessColor getColor();

}
