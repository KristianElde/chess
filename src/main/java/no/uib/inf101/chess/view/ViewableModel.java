package no.uib.inf101.chess.view;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.GameState;
import no.uib.inf101.chess.model.Option;
import no.uib.inf101.chess.model.Square;
import no.uib.inf101.chess.model.aiPlayer.AIPlayer;

public interface ViewableModel {

    ChessBoard getBoard();

    Square getSelectedSquare();

    Square getLastMoveFrom();

    Square getLastMoveTo();

    GameState getGameState();

    ChessColor getWinner();

    boolean isAiOpposition();

    AIPlayer getAiPlayer();

    ChessColor getPlayerColor();

    Option getSelectedOption();
}
