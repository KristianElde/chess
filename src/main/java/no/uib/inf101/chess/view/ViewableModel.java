package no.uib.inf101.chess.view;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.grid.GridDimension;

public interface ViewableModel {
    GridDimension getDimension();

    ChessBoard getBoard();
}
