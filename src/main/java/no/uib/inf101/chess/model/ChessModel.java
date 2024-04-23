package no.uib.inf101.chess.model;

import no.uib.inf101.chess.controller.ControllableModel;
import no.uib.inf101.chess.model.aiPlayer.AIPlayer;
import no.uib.inf101.chess.model.aiPlayer.CarefulAI;
import no.uib.inf101.chess.model.pieces.Piece;
import no.uib.inf101.chess.view.ViewableModel;

public class ChessModel implements ViewableModel, ControllableModel {

    private ChessBoard board;
    private Square selectedSquare;
    private Square lastMoveFrom;
    private Square lastMoveTo;
    private GameState gameState = GameState.ACTIVE;
    private ChessColor winner;
    private boolean aiOpposition;
    private ChessColor playerColor;
    private AIPlayer aiPlayer;
    public int n = 1;

    public ChessModel() {
        board = ChessBoard.initialPositionBoard();
        aiOpposition = false;
        playerColor = ChessColor.WHITE;
        aiPlayer = new CarefulAI(board);
    }
    
    public int getDepth() {
        return n;
    }

    public ChessModel(String boardString, ChessColor toDraw) {
        board = ChessBoard.stringToBoard(boardString, toDraw);
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
