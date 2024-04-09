package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.Square;

public class King implements IPiece, ICastleable {

    private ChessColor color;
    private ArrayList<Square> legalMoves;
    private boolean allowCastling = true;
    private Rook kingSideRook;
    private Rook queenSideRook;

    public King(ChessColor color) {
        this.color = color;
    }

    @Override
    public ArrayList<Square> getLegalMoves() {
        return this.legalMoves;
    }

    @Override
    public void updateLegalMoves(ChessBoard board, Square currentSquare) {
        this.legalMoves = calculateLegalMoves(board, currentSquare);
    }

    @Override
    public ArrayList<Square> calculateLegalMoves(ChessBoard board, Square currentSquare) {
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
                    && (candidateSquare.getPiece() == null || !candidateSquare.getPiece().getColor().equals(color))) {
                legalMoves.add(candidateSquare);
            }
        }

        legalMoves.addAll(calculateCastlingMoves(board));

        return legalMoves;
    }

    private ArrayList<Square> calculateCastlingMoves(ChessBoard board) {
        ArrayList<Square> legalCastlingMoves = new ArrayList<>();
        if (!allowCastling)
            return legalCastlingMoves;

        if (kingSideRook.getAllowCastling()) {
            if (color == ChessColor.WHITE) {
                if (board.get(Column.F, 1).getPiece() == null && board.get(Column.G, 1).getPiece() == null) {
                    Square castlingSquare = board.get(Column.F, 1);
                    legalCastlingMoves.add(castlingSquare);
                }
            }
            if (color == ChessColor.BLACK) {
                if (board.get(Column.B, 8).getPiece() == null && board.get(Column.C, 8).getPiece() == null) {
                    Square castlingSquare = board.get(Column.B, 8);
                    legalCastlingMoves.add(castlingSquare);
                }
            }
        }

        if (queenSideRook.getAllowCastling()) {
            if (color == ChessColor.WHITE) {
                if (board.get(Column.B, 1).getPiece() == null && board.get(Column.C, 1).getPiece() == null
                        && board.get(Column.D, 1).getPiece() == null) {
                    Square castlingSquare = board.get(Column.C, 1);
                    legalCastlingMoves.add(castlingSquare);
                }
            }
            if (color == ChessColor.BLACK) {
                if (board.get(Column.E, 8).getPiece() == null && board.get(Column.F, 8).getPiece() == null
                        && board.get(Column.G, 8).getPiece() == null) {
                    Square castlingSquare = board.get(Column.F, 8);
                    legalCastlingMoves.add(castlingSquare);
                }
            }
        }

        return legalCastlingMoves;
    }

    @Override
    public ChessColor getColor() {
        return this.color;
    }

    @Override
    public boolean getAllowCastling() {
        return this.allowCastling;
    }

    @Override
    public void stopAllowCastling() {
        this.allowCastling = false;
    }

}
