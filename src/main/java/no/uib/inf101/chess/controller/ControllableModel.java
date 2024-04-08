package no.uib.inf101.chess.controller;

import no.uib.inf101.chess.model.Square;

public interface ControllableModel {

    Square getSelectedSquare();

    void setSelectedSquare(Square square);
}
