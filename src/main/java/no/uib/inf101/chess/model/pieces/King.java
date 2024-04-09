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

    public King(ChessColor color, Rook kingSideRook, Rook queenSideRook) {
        this.color = color;
        this.kingSideRook = kingSideRook;
        this.queenSideRook = queenSideRook;
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

        int row = (color == ChessColor.WHITE ? 1 : 8);

        if (kingSideRook.getAllowCastling()) {
            if (board.get(Column.F, row).getPiece() == null && board.get(Column.G, row).getPiece() == null) {
                Square castlingSquare = board.get(Column.G, row);
                legalCastlingMoves.add(castlingSquare);
            }
        }

        if (queenSideRook.getAllowCastling()) {
            if (board.get(Column.B, row).getPiece() == null && board.get(Column.C, row).getPiece() == null
                    && board.get(Column.D, row).getPiece() == null) {
                Square castlingSquare = board.get(Column.C, row);
                legalCastlingMoves.add(castlingSquare);
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
