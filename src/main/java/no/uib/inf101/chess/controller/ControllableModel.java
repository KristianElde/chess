package no.uib.inf101.chess.controller;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.GameState;
import no.uib.inf101.chess.model.Option;
import no.uib.inf101.chess.model.Square;
import no.uib.inf101.chess.model.aiPlayer.AIPlayer;

/**
 * The ControllableModel interface represents a controllable model for a chess
 * application,
 * providing methods to interact with the game state and control various
 * settings.
 */
public interface ControllableModel {

    /**
     * Gets the current chess board.
     *
     * @return The chess board.
     */
    ChessBoard getBoard();

    /**
     * Gets the currently selected square on the chess board.
     *
     * @return The currently selected square.
     */
    Square getSelectedSquare();

    /**
     * Sets the currently selected square on the chess board.
     * If the selected square contains a piece belonging to the current player's
     * color,
     * it sets the selected square to the provided square without further action.
     * If the selected square does not contain a piece belonging to the current
     * player's color,
     * and the previously selected square is not null, it attempts to move the piece
     * from
     * the previously selected square to the provided square. If the move is legal,
     * it updates the game state accordingly, checks for checkmate or stalemate
     * conditions,
     * and adjusts the winner if necessary.
     * If the move is not legal, the previously selected square remains selected.
     * After handling the move, the selected square is set to null.
     *
     * @param newSelectedSquare The square to set as the currently selected square.
     */
    void setSelectedSquare(Square square);

    /**
     * Checks if the game is against an AI opponent.
     *
     * @return True if the game is against an AI opponent, false otherwise.
     */
    boolean isAiOpposition();

    /**
     * Gets the color the player plays as.
     *
     * @return The color the player plays as.
     */
    ChessColor getPlayerColor();

    /**
     * Gets the AI player controlling the opponent.
     *
     * @return The AI player controlling the opponent.
     */
    AIPlayer getAiPlayer();

    /**
     * Gets the current game state.
     *
     * @return The current game state.
     */
    GameState getGameState();

    /**
     * Sets the game state to the specified state.
     *
     * @param gameState The game state to set.
     */
    void setGameState(GameState gameState);

    /**
     * Sets the AI player controlling the opponent.
     *
     * @param aiPlayer The AI player to set.
     */
    void setAiPlayer(AIPlayer aiPlayer);

    /**
     * Sets the selected option for the game.
     *
     * @param option The option to set.
     */
    void setSelectedOption(Option option);

    /**
     * Gets the selected option for the game. The selected option is the option that
     * is currently possible to change.
     *
     * @return The selected option.
     */
    Option getSelectedOption();

    /**
     * Toggles the selected option based on the provided flag.
     *
     * @param next True if the next option should be selected, false if the previous
     *             option should be selected.
     */
    void toggleSelectedOption(boolean next);
}
