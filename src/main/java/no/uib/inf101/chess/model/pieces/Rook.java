package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.Square;

public class Rook implements IPiece, ICastleable {

    private ChessColor color;
    private ArrayList<Square> legalMoves;
    private boolean allowCastling = true;

    public Rook(ChessColor color) {
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

        int[] stepDirections = { -1, 1 };
        for (int delta : stepDirections) {
            for (int row = currentSquare.row() + delta; row < 9 && row > 0; row += delta) {
                Square candidateSquare = board.get(currentSquare.col(), row);
                if (candidateSquare == null)
                    break;
                if (candidateSquare.getPiece() == null)
                    legalMoves.add(candidateSquare);
                else {
                    if (!candidateSquare.getPiece().getColor().equals(color))
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
                    if (!candidateSquare.getPiece().getColor().equals(color))
                        legalMoves.add(candidateSquare);
                    break;
                }
            }
        }

        return legalMoves;
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
    public void setAllowCastling(boolean allowCastling) {
        this.allowCastling = allowCastling;
    }

}
