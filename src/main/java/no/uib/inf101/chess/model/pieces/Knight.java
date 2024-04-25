package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

/**
 * The Knight class represents a knight chess piece. It extends the Piece class
 * and provides functionality for updating and calculating legal moves for the
 * knight.
 */
public class Knight extends Piece {

    /**
     * Constructs a new knight with the specified color.
     *
     * @param color The color of the knight.
     */
    public Knight(ChessColor color) {
        super(color, 3);
    }

    @Override
    public void updateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive) {
        legalMoves = calculateLegalMoves(board, currentSquare, primitive);
    }

    @Override
    public ArrayList<Square> calculateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive) {
        ArrayList<Square> legalMoves = new ArrayList<>();
        ArrayList<Square> candidateSquares = new ArrayList<>();

        candidateSquares.add(board.get(currentSquare.col().nextCol(), currentSquare.row() + 2));
        candidateSquares.add(board.get(currentSquare.col().nextCol(), currentSquare.row() - 2));
        candidateSquares.add(board.get(currentSquare.col().nextNextCol(), currentSquare.row() + 1));
        candidateSquares.add(board.get(currentSquare.col().nextNextCol(), currentSquare.row() - 1));
        candidateSquares.add(board.get(currentSquare.col().prevCol(), currentSquare.row() + 2));
        candidateSquares.add(board.get(currentSquare.col().prevCol(), currentSquare.row() - 2));
        candidateSquares.add(board.get(currentSquare.col().prevPrevCol(), currentSquare.row() + 1));
        candidateSquares.add(board.get(currentSquare.col().prevPrevCol(), currentSquare.row() - 1));

        for (Square candidateSquare : candidateSquares) {
            if (candidateSquare != null
                    && (candidateSquare.getPiece() == null
                            || !candidateSquare.getPiece().getColor().equals(getColor()))) {
                legalMoves.add(candidateSquare);
            }
        }

        if (!primitive)
            legalMoves = removeInCheckMoves(legalMoves, board, currentSquare);

        return legalMoves;
    }

}
