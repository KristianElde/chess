package no.uib.inf101.chess.controller;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.Square;

public interface ControllableModel {

    ChessBoard getBoard();

    Square getSelectedSquare();

    void setSelectedSquare(Square square);
}
