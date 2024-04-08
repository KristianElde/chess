package no.uib.inf101.chess.model;

import java.util.ArrayList;

import no.uib.inf101.chess.controller.ControllableModel;
import no.uib.inf101.chess.model.pieces.IPiece;
import no.uib.inf101.chess.model.pieces.Pawn;
import no.uib.inf101.chess.view.ViewableModel;
import no.uib.inf101.grid.GridDimension;

public class ChessModel implements ViewableModel, ControllableModel {

    private ChessBoard board;
    private Square selectedSquare;
    private ChessColor toDraw;

    public ChessModel() {
        board = new ChessBoard();
        toDraw = ChessColor.WHITE;

        for (Square square : board) {
            if (square.getPiece() != null && square.getPiece().getColor().equals(toDraw)
                    && square.getPiece() instanceof Pawn) {
                square.getPiece().updateLegalMoves(board, square);
            }
        }
    }

    @Override
    public ChessBoard getBoard() {
        return board;
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    @Override
    public Square getSelectedSquare() {
        return selectedSquare;
    }

    @Override
    public void setSelectedSquare(Square newSelectedSquare) {
        IPiece selectededPiece = newSelectedSquare.getPiece();

        if (this.selectedSquare == null) {
            if (selectededPiece != null && selectededPiece.getColor().equals(toDraw)) {
                this.selectedSquare = newSelectedSquare;
                return;
            }
        } else {
            movePiece(selectedSquare, newSelectedSquare);
        }
    }

    private void movePiece(Square from, Square to) {
        IPiece piece = from.getPiece();
        ArrayList<Square> legalMoves = piece.getLegalMoves();

        if (legalMoves.contains(to)) {
            from.setPiece(null);
            to.setPiece(piece);
            toggleTurn();
        }

        this.selectedSquare = null;
    }

    private void toggleTurn() {
        this.toDraw = this.toDraw.toggle();
    }

}
