package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.Square;

/**
 * The Rook class represents a rook chess piece.
 * It extends the Piece class and provides functionality for updating and
 * calculating legal moves for the rook.
 */
public class Rook extends CastleablePiece {

    /**
     * Constructs a new rook with the specified color.
     *
     * @param color The color of the rook.
     */
    public Rook(ChessColor color) {
        super(color, 5);
    }

    @Override
    public ArrayList<Square> calculateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive) {
        ArrayList<Square> legalMoves = new ArrayList<>();

        int[] stepDirections = { -1, 1 };
        for (int delta : stepDirections) {
            for (int row = currentSquare.row() + delta; row < 9 && row > 0; row += delta) {
                Square candidateSquare = board.get(currentSquare.col(), row);
                if (candidateSquare == null)
                    break;
                if (candidateSquare.getPiece() == null)
                    legalMoves.add(candidateSquare);
                else {
                    if (candidateSquare.getPiece().getColor() != getColor())
                        legalMoves.add(candidateSquare);
                    break;
                }
            }

            for (int colOrdinal = currentSquare.col().ordinal() + delta; colOrdinal < 8
                    && colOrdinal >= 0; colOrdinal += delta) {
                Square candidateSquare = board.get(Column.values()[colOrdinal], currentSquare.row());
                if (candidateSquare == null)
                    break;
                if (candidateSquare.getPiece() == null)
                    legalMoves.add(candidateSquare);
                else {
                    if (candidateSquare.getPiece().getColor() != getColor())
                        legalMoves.add(candidateSquare);
                    break;
                }
            }
        }

        if (!primitive)
            legalMoves = removeInCheckMoves(legalMoves, board, currentSquare);

        return legalMoves;
    }

}
