package no.uib.inf101.chess.model;

import no.uib.inf101.chess.controller.ControllableModel;
import no.uib.inf101.chess.model.aiPlayer.AIPlayer;
import no.uib.inf101.chess.model.aiPlayer.AggressiveAI;
import no.uib.inf101.chess.model.aiPlayer.CarefulAI;
import no.uib.inf101.chess.model.aiPlayer.RandomAI;
import no.uib.inf101.chess.model.pieces.Piece;
import no.uib.inf101.chess.view.ViewableModel;

/**
 * The ChessModel class represents the model for a chess game,
 * implementing both the ViewableModel and ControllableModel interfaces.
 * It manages the game state, player actions, and AI opponents.
 */
public class ChessModel implements ViewableModel, ControllableModel {

    private ChessBoard board;
    private Square selectedSquare;
    private Square lastMoveFrom;
    private Square lastMoveTo;
    private GameState gameState = GameState.MAIN_MENU;
    private ChessColor winner;
    private Option selectedOption = Option.TEXTURE;
    private boolean aiOpposition = true;
    private ChessColor playerColor = ChessColor.WHITE;
    private AIPlayer aiPlayer;

    /**
     * Constructs a ChessModel object with the start chess board setup.
     */
    public ChessModel() {
        board = ChessBoard.initialPositionBoard();
        aiPlayer = new RandomAI(board);
    }

    /**
     * Constructs a ChessModel object with the specified chess board setup.
     *
     * @param boardString The string representation of the chess board.
     * @param toDraw      The color to draw the board from.
     */
    public ChessModel(String boardString, ChessColor toDraw) {
        board = ChessBoard.stringToBoard(boardString, toDraw);
        aiPlayer = new RandomAI(board);

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
        return gameState;
    }

    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public ChessColor getWinner() {
        return winner;
    }

    @Override
    public Square getLastMoveFrom() {
        return lastMoveFrom;
    }

    @Override
    public Square getLastMoveTo() {
        return lastMoveTo;
    }

    @Override
    public Option getSelectedOption() {
        return selectedOption;
    }

    @Override
    public void setSelectedOption(Option option) {
        selectedOption = option;
    }

    @Override
    public boolean isAiOpposition() {
        return aiOpposition;
    }

    @Override
    public ChessColor getPlayerColor() {
        return playerColor;
    }

    @Override
    public AIPlayer getAiPlayer() {
        return aiPlayer;
    }

    @Override
    public void setAiPlayer(AIPlayer aiPlayer) {
        this.aiPlayer = aiPlayer;
    }

    @Override
    public void toggleSelectedOption(boolean next) {
        if (selectedOption == Option.TEXTURE) {

        }
        if (selectedOption == Option.MULTIPLAYER)
            this.aiOpposition = !aiOpposition;

        if (selectedOption == Option.COLOR)
            this.playerColor = playerColor.toggle();

        if (selectedOption == Option.DIFFICULTY) {
            if (next)
                nextAiLevel();
            else
                prevAiLevel();
        }

    }

    private void nextAiLevel() {
        if (aiPlayer instanceof RandomAI)
            aiPlayer = new AggressiveAI(board);
        else if (aiPlayer instanceof AggressiveAI)
            aiPlayer = new CarefulAI(board);
        else if (aiPlayer instanceof CarefulAI)
            aiPlayer = new RandomAI(board);
    }

    private void prevAiLevel() {
        if (aiPlayer instanceof RandomAI)
            aiPlayer = new CarefulAI(board);
        else if (aiPlayer instanceof AggressiveAI)
            aiPlayer = new RandomAI(board);
        else if (aiPlayer instanceof CarefulAI)
            aiPlayer = new AggressiveAI(board);
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
                setLastMove(selectedSquare, newSelectedSquare);
                board.updateLegalMoves(board.getToDraw(), false);
                if (!anyLegalMoves(board.getToDraw())) {
                    this.gameState = (board.isInCheck(board.getToDraw()) ? GameState.CHECKMATE : GameState.STALEMATE);
                    if (gameState == GameState.CHECKMATE)
                        winner = board.getToDraw().toggle();
                }
            }
            this.selectedSquare = null;
        }
    }

    private boolean anyLegalMoves(ChessColor color) {
        for (Square square : board) {
            if (square.getPiece() != null && square.getPiece().getColor() == color
                    && !square.getPiece().getLegalMoves().isEmpty())
                return true;
        }
        return false;
    }

    private void setLastMove(Square from, Square to) {
        lastMoveFrom = from;
        lastMoveTo = to;
    }

}
