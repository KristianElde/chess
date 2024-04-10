package no.uib.inf101.chess.model;

import no.uib.inf101.chess.controller.ControllableModel;
import no.uib.inf101.chess.model.pieces.Piece;
import no.uib.inf101.chess.view.ViewableModel;

public class ChessModel implements ViewableModel, ControllableModel {

    private ChessBoard board;
    private Square selectedSquare;
    private GameState gameState = GameState.ACTIVE;

    public ChessModel() {
        board = new ChessBoard();
    }

    @Override
    public ChessBoard getBoard() {
        return board;
    }

    @Override
    public Square getSelectedSquare() {
        return selectedSquare;
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void setSelectedSquare(Square newSelectedSquare) {
        Piece newSelectedPiece = newSelectedSquare.getPiece();

        if (newSelectedPiece != null && newSelectedPiece.getColor().equals(board.getToDraw())) {
            this.selectedSquare = newSelectedSquare;
            return;
        }

        if (selectedSquare != null) {
            Piece selectedPiece = selectedSquare.getPiece();
            if (selectedPiece.getLegalMoves().contains(newSelectedSquare)) {
                board.movePiece(selectedSquare, newSelectedSquare, selectedPiece);
                board.afterMovePiece(selectedSquare, newSelectedSquare, selectedPiece);
            }
            this.selectedSquare = null;
        }
    }

}
