package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.Square;

/**
 * The MoveablePiece interface represents a piece on the chess board that can
 * move. Classes implementing this interface must provide methods to update and
 * calculate legal moves.
 */
public interface MoveablePiece {

    /**
     * Calculates the legal moves for the piece based on the current board position.
     *
     * @param board         The chess board.
     * @param currentSquare The current square occupied by the piece.
     * @param primitive     Indicates whether to perform primitive move
     *                      calculations.
     *                      Primitive move calculation does not check whether a move
     *                      puts its own king in check.
     * @return A list of squares representing legal moves for the piece.
     */
    ArrayList<Square> calculateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive);

}
