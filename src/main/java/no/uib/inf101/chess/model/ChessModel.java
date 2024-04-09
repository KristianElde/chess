package no.uib.inf101.chess.model;

import java.util.ArrayList;

import no.uib.inf101.chess.controller.ControllableModel;
import no.uib.inf101.chess.model.pieces.IPiece;
import no.uib.inf101.chess.view.ViewableModel;
import no.uib.inf101.grid.GridDimension;

public class ChessModel implements ViewableModel, ControllableModel {

    private ChessBoard board;
    private Square selectedSquare;
    private ChessColor toDraw;

    public ChessModel() {
        board = new ChessBoard();
        toDraw = ChessColor.WHITE;
        updateLegalMoves(toDraw);
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
        IPiece selectedPiece = newSelectedSquare.getPiece();

        if (selectedPiece != null && selectedPiece.getColor().equals(toDraw)) {
            this.selectedSquare = newSelectedSquare;
            return;
        }

        if (selectedSquare != null)
            movePiece(selectedSquare, newSelectedSquare);

    }

    private void movePiece(Square from, Square to) {
        IPiece piece = from.getPiece();
        ArrayList<Square> legalMoves = piece.getLegalMoves();

        if (legalMoves.contains(to)) {
            from.setPiece(null);
            to.setPiece(piece);
            toggleTurn();
            updateLegalMoves(toDraw);
        }

        this.selectedSquare = null;

    }

    private void toggleTurn() {
        this.toDraw = this.toDraw.toggle();
    }

    private void updateLegalMoves(ChessColor color) {
        for (Square square : board) {
            IPiece piece = square.getPiece();
            if (piece != null && piece.getColor().equals(color)) {
                square.getPiece().updateLegalMoves(board, square);
            }
        }
    }
}
