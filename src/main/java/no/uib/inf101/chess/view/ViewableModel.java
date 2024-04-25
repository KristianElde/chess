package no.uib.inf101.chess.view;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.GameState;
import no.uib.inf101.chess.model.Option;
import no.uib.inf101.chess.model.Square;
import no.uib.inf101.chess.model.aiPlayer.AIPlayer;

/**
 * The ViewableModel interface represents a viewable model for a chess
 * application, providing methods to retrieve information about the game state
 * and settings.
 */
public interface ViewableModel {

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
     * Gets the square from which the last move was made.
     *
     * @return The square from which the last move was made.
     */
    Square getLastMoveFrom();

    /**
     * Gets the square to which the last move was made.
     *
     * @return The square to which the last move was made.
     */
    Square getLastMoveTo();

    /**
     * Gets the current game state.
     *
     * @return The current game state.
     */
    GameState getGameState();

    /**
     * Gets the color of the winner, if the game has ended.
     *
     * @return The color of the winner, or null if there is no winner yet.
     */
    ChessColor getWinner();

    /**
     * Checks if the game is against an AI opponent.
     *
     * @return True if the game is against an AI opponent, false otherwise.
     */
    boolean isAiOpposition();

    /**
     * Gets the AI player controlling the opponent.
     *
     * @return The AI player controlling the opponent.
     */
    AIPlayer getAiPlayer();

    /**
     * Gets the color of the player.
     *
     * @return The color of the player.
     */
    ChessColor getPlayerColor();

    /**
     * Gets the selected option for the game. The selected option is the option that
     * is currently possible to change.
     *
     * @return The selected option.
     */
    Option getSelectedOption();
}
