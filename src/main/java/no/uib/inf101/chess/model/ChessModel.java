package no.uib.inf101.chess.model;

import no.uib.inf101.chess.controller.ControllableModel;
import no.uib.inf101.chess.view.ViewableModel;
import no.uib.inf101.grid.GridDimension;

public class ChessModel implements ViewableModel, ControllableModel {

    private ChessBoard board;
    private Square selectedSquare;

    public ChessModel() {
        board = new ChessBoard();
        selectedSquare = board.getGrid().get(0).get(0);
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
    public void setSelectedSquare(Square selecredSquare) {
        this.selectedSquare = selecredSquare;
    }

}
