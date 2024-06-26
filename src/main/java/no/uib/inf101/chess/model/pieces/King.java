package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.Square;

/**
 * The King class represents a king chess piece.
 * It extends the Piece class and provides functionality for updating and
 * calculating legal moves for the king.
 */
public class King extends CastleablePiece {

    /**
     * Constructs a new king with the specified color.
     *
     * @param color The color of the king.
     */
    public King(ChessColor color) {
        super(color, 100);
    }

    @Override
    public ArrayList<Square> calculateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive) {
        ArrayList<Square> legalMoves = new ArrayList<>();
        ArrayList<Square> candidateSquares = new ArrayList<>();

        candidateSquares.add(board.get(currentSquare.col(), currentSquare.row() + 1));
        candidateSquares.add(board.get(currentSquare.col(), currentSquare.row() - 1));
        candidateSquares.add(board.get(currentSquare.col().nextCol(), currentSquare.row()));
        candidateSquares.add(board.get(currentSquare.col().nextCol(), currentSquare.row() + 1));
        candidateSquares.add(board.get(currentSquare.col().nextCol(), currentSquare.row() - 1));
        candidateSquares.add(board.get(currentSquare.col().prevCol(), currentSquare.row()));
        candidateSquares.add(board.get(currentSquare.col().prevCol(), currentSquare.row() + 1));
        candidateSquares.add(board.get(currentSquare.col().prevCol(), currentSquare.row() - 1));

        for (Square candidateSquare : candidateSquares) {
            if (candidateSquare != null
                    && (candidateSquare.getPiece() == null || candidateSquare.getPiece().getColor() != getColor())) {
                legalMoves.add(candidateSquare);
            }
        }

        if (!primitive) {
            legalMoves.addAll(calculateCastlingMoves(board));
            legalMoves = removeInCheckMoves(legalMoves, board, currentSquare);
        }
        return legalMoves;
    }

    private ArrayList<Square> calculateCastlingMoves(ChessBoard board) {
        ArrayList<Square> legalCastlingMoves = new ArrayList<>();
        if (!allowCastling)
            return legalCastlingMoves;

        int row = (getColor() == ChessColor.WHITE ? 1 : 8);

        Piece piece = board.get(Column.A, row).getPiece();
        if (piece instanceof Rook && ((Rook) piece).getAllowCastling()) {
            if (board.get(Column.B, row).getPiece() == null && board.get(Column.C, row).getPiece() == null
                    && board.get(Column.D, row).getPiece() == null) {
                if (!board.isThreatenedBy(board.get(Column.C, row), getColor().toggle())
                        && !board.isThreatenedBy(board.get(Column.D, row), getColor().toggle())
                        && !board.isThreatenedBy(board.get(Column.E, row), getColor().toggle())) {
                    Square castlingSquare = board.get(Column.C, row);
                    legalCastlingMoves.add(castlingSquare);
                }
            }
        }
        piece = board.get(Column.H, row).getPiece();
        if (piece instanceof Rook && ((Rook) piece).getAllowCastling()) {
            if (board.get(Column.F, row).getPiece() == null && board.get(Column.G, row).getPiece() == null) {
                if (!board.isThreatenedBy(board.get(Column.E, row), getColor().toggle())
                        && !board.isThreatenedBy(board.get(Column.F, row), getColor().toggle())
                        && !board.isThreatenedBy(board.get(Column.G, row), getColor().toggle())) {
                    Square castlingSquare = board.get(Column.G, row);
                    legalCastlingMoves.add(castlingSquare);
                }
            }
        }
        // if (kingSideRook.getAllowCastling()) {
        // if (board.get(Column.F, row).getPiece() == null && board.get(Column.G,
        // row).getPiece() == null) {
        // if (!board.isThreatendBy(board.get(Column.E, row), getColor().toggle())
        // && !board.isThreatendBy(board.get(Column.F, row), getColor().toggle())
        // && !board.isThreatendBy(board.get(Column.G, row), getColor().toggle())) {
        // Square castlingSquare = board.get(Column.G, row);
        // legalCastlingMoves.add(castlingSquare);
        // }
        // }
        // }

        // if (queenSideRook.getAllowCastling()) {
        // if (board.get(Column.B, row).getPiece() == null && board.get(Column.C,
        // row).getPiece() == null
        // && board.get(Column.D, row).getPiece() == null) {
        // if (!board.isThreatendBy(board.get(Column.C, row), getColor().toggle())
        // && !board.isThreatendBy(board.get(Column.D, row), getColor().toggle())
        // && !board.isThreatendBy(board.get(Column.E, row), getColor().toggle())) {
        // Square castlingSquare = board.get(Column.C, row);
        // legalCastlingMoves.add(castlingSquare);
        // }
        // }
        // }

        return legalCastlingMoves;
    }

}
