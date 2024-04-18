package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Column;
import no.uib.inf101.chess.model.Square;

public class Bishop extends Piece {

    public Bishop(ChessColor color) {
        super(color);
    }

    @Override
    public void updateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive) {
        legalMoves = calculateLegalMoves(board, currentSquare, primitive);
    }

    @Override
    public ArrayList<Square> calculateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive) {
        ArrayList<Square> legalMoves = new ArrayList<>();

        int[] stepDirections = { -1, 1 };
        for (int delta1 : stepDirections) {
            for (int delta2 : stepDirections) {
                for (int steps = 1; steps < 9; steps++) {
                    int colOrdinal = currentSquare.col().ordinal() + delta1 * steps;
                    Column col = (colOrdinal >= 0 && colOrdinal < 8 ? Column.values()[colOrdinal] : null);
                    int row = currentSquare.row() + delta2 * steps;
                    Square candidateSquare = board.get(col, row);

                    if (candidateSquare == null)
                        break;
                    if (candidateSquare.getPiece() == null)
                        legalMoves.add(candidateSquare);
                    else {
                        if (!candidateSquare.getPiece().getColor().equals(getColor()))
                            legalMoves.add(candidateSquare);
                        break;
                    }
                }
            }
        }

        if (!primitive)
            legalMoves = removeInCheckMoves(legalMoves, board, currentSquare);

        return legalMoves;
    }

}
