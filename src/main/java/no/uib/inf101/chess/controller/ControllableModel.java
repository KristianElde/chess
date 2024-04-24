package no.uib.inf101.chess.controller;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.GameState;
import no.uib.inf101.chess.model.Option;
import no.uib.inf101.chess.model.Square;
import no.uib.inf101.chess.model.aiPlayer.AIPlayer;

public interface ControllableModel {

    ChessBoard getBoard();

    Square getSelectedSquare();

    void setSelectedSquare(Square square);

    boolean isAiOpposition();

    ChessColor getPlayerColor();

    AIPlayer getAiPlayer();

    GameState getGameState();

    void setGameState(GameState gameState);

    void setAiPlayer(AIPlayer aiPlayer);

    void setSelectedOption(Option option);

    Option getSelectedOption();

    void toggleSelectedOption(boolean next);
}
