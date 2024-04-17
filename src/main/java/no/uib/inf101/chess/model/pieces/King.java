package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.Square;

public class King extends CastleablePiece {

    private Rook kingSideRook;
    private Rook queenSideRook;

    public King(ChessColor color, Rook kingSideRook, Rook queenSideRook) {
        super(color);
        this.kingSideRook = kingSideRook;
        this.queenSideRook = queenSideRook;
    }

    @Override
    public void updateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive) {
        setLegalMoves(calculateLegalMoves(board, currentSquare, primitive));
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

        if (kingSideRook.getAllowCastling()) {
            if (board.get(Column.F, row).getPiece() == null && board.get(Column.G, row).getPiece() == null) {
                if (!board.isThreatendBy(board.get(Column.E, row), getColor().toggle())
                        && !board.isThreatendBy(board.get(Column.F, row), getColor().toggle())
                        && !board.isThreatendBy(board.get(Column.G, row), getColor().toggle())) {
                    Square castlingSquare = board.get(Column.G, row);
                    legalCastlingMoves.add(castlingSquare);
                }
            }
        }

        if (queenSideRook.getAllowCastling()) {
            if (board.get(Column.B, row).getPiece() == null && board.get(Column.C, row).getPiece() == null
                    && board.get(Column.D, row).getPiece() == null) {
                if (!board.isThreatendBy(board.get(Column.C, row), getColor().toggle())
                        && !board.isThreatendBy(board.get(Column.D, row), getColor().toggle())
                        && !board.isThreatendBy(board.get(Column.E, row), getColor().toggle())) {
                    Square castlingSquare = board.get(Column.C, row);
                    legalCastlingMoves.add(castlingSquare);
                }
            }
        }

        return legalCastlingMoves;
    }

}
