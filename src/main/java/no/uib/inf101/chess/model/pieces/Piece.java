package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

public abstract class Piece implements MoveablePiece {

    ArrayList<Square> legalMoves = new ArrayList<>();
    private ChessColor color;
    private int materialValue;

    Piece(ChessColor color, int materialValue) {
        this.color = color;
        this.materialValue = materialValue;
    }

    public ArrayList<Square> getLegalMoves() {
        return legalMoves;
    }

    public ChessColor getColor() {
        return color;
    }

    ArrayList<Square> removeInCheckMoves(ArrayList<Square> legalMoves, ChessBoard board, Square currentSquare) {
        ArrayList<Square> illegalMoves = new ArrayList<>();

        for (Square toSquare : legalMoves) {
            if (!board.testMoveIsLegal(currentSquare, toSquare, this))
                illegalMoves.add(toSquare);
        }

        legalMoves.removeAll(illegalMoves);

        return legalMoves;
    }

    public int getMaterialValue() {
        return materialValue;
    }

}
