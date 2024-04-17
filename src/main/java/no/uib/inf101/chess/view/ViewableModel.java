package no.uib.inf101.chess.view;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.GameState;
import no.uib.inf101.chess.model.Square;

public interface ViewableModel {

    ChessBoard getBoard();

    Square getSelectedSquare();

    Square getLastMoveFrom();

    Square getLastMoveTo();

    GameState getGameState();

    ChessColor getWinner();
}
