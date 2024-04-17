package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.Square;

public interface MoveablePiece {

    void updateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive);

    ArrayList<Square> calculateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive);

}
