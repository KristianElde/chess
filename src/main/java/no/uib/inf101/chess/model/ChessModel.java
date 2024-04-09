package no.uib.inf101.chess.model;

import java.util.ArrayList;

import no.uib.inf101.chess.controller.ControllableModel;
import no.uib.inf101.chess.model.pieces.IPiece;
import no.uib.inf101.chess.model.pieces.King;
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

    private boolean movePiece(Square from, Square to) {
        IPiece piece = from.getPiece();
        ArrayList<Square> legalMoves = piece.getLegalMoves();

        if (piece instanceof King && isCastleMove(from, to, (King) piece)) {
            afterMovePerformed();
            performCastlingMove(from, to);
            return true;

        }

        if (legalMoves.contains(to)) {
            from.setPiece(null);
            to.setPiece(piece);
            toggleTurn();
            updateLegalMoves(toDraw);
            this.selectedSquare = null;
            return true;
        }

        this.selectedSquare = null;
        return false;
    }

    private boolean isCastleMove(Square from, Square to, King king) {
        if (!king.getAllowCastling())
            return false;

        if (king.getColor() == ChessColor.WHITE) {
            if (to == board.get(Column.C, 1) || to == board.get(Column.G, 1)) {
                return true;
            }
        }
        if (king.getColor() == ChessColor.BLACK) {
            if (to == board.get(Column.C, 8) || to == board.get(Column.G, 8)) {
                return true;
            }
        }

        return false;
    }

    private void performCastlingMove(Square from, Square to) {
    }

    private void afterMovePerformed() {
        this.selectedSquare = null;
        toggleTurn();
        updateLegalMoves(toDraw);
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
