package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

public class Knight extends Piece {

    public Knight(ChessColor color) {
        super(color);
    }

    @Override
    public void updateLegalMoves(ChessBoard board, Square currentSquare) {
        setLegalMoves(calculateLegalMoves(board, currentSquare));
    }

    @Override
    public ArrayList<Square> calculateLegalMoves(ChessBoard board, Square currentSquare) {
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

        return legalMoves;
    }

}
