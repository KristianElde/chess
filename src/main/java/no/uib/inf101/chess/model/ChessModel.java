package no.uib.inf101.chess.model;

import java.util.ArrayList;

import no.uib.inf101.chess.controller.ControllableModel;
import no.uib.inf101.chess.model.pieces.ICastleable;
import no.uib.inf101.chess.model.pieces.IPiece;
import no.uib.inf101.chess.model.pieces.King;
import no.uib.inf101.chess.model.pieces.Pawn;
import no.uib.inf101.chess.view.ViewableModel;

public class ChessModel implements ViewableModel, ControllableModel {

    private ChessBoard board;
    private Square selectedSquare;
    private ChessColor toDraw;
    private GameState gameState = GameState.ACTIVE;

    public ChessModel() {
        board = new ChessBoard();
        toDraw = ChessColor.WHITE;
        updateLegalMoves(toDraw);
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
        return this.gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void setSelectedSquare(Square newSelectedSquare) {
        IPiece selectedPiece = newSelectedSquare.getPiece();

        if (selectedPiece != null && selectedPiece.getColor().equals(toDraw)) {
            this.selectedSquare = newSelectedSquare;
            return;
        }

        if (selectedSquare != null)
            movePiece(selectedSquare, newSelectedSquare);

    }

    private boolean movePiece(Square from, Square to) {
        IPiece piece = from.getPiece();
        ArrayList<Square> legalMoves = piece.getLegalMoves();

        if (legalMoves.contains(to)) {
            if (piece instanceof King && isCastleMove(from, to, (King) piece)) {
                performCastlingMove(from, to, (King) piece);
                afterMovePerformed(from, to, piece);
                return true;
            }

            if (piece instanceof Pawn && isEnPassentMove(from, to)) {
                performEnPassentMove(from, to, piece);
                afterMovePerformed(from, to, piece);
                return true;
            }

            from.setPiece(null);
            to.setPiece(piece);
            afterMovePerformed(from, to, piece);
            return true;
        }

        this.selectedSquare = null;
        return false;
    }

    private boolean isCastleMove(Square from, Square to, King king) {
        if (!king.getAllowCastling())
            return false;

        int row = (king.getColor() == ChessColor.WHITE ? 1 : 8);

        if (to == board.get(Column.C, row) || to == board.get(Column.G, row)) {
            return true;
        }

        return false;
    }

    //
    private void performCastlingMove(Square kingFrom, Square kingTo, King king) {
        int row = (king.getColor() == ChessColor.WHITE ? 1 : 8);
        Column colFrom = (kingTo.col() == Column.C ? Column.A : Column.H);
        Column colTo = (kingTo.col() == Column.C ? Column.D : Column.F);

        Square rookFrom = board.get(colFrom, row);
        IPiece rook = rookFrom.getPiece();
        Square rookTo = board.get(colTo, row);

        kingFrom.setPiece(null);
        kingTo.setPiece(king);
        rookFrom.setPiece(null);
        rookTo.setPiece(rook);
    }

    private boolean isEnPassentMove(Square from, Square to) {
        if (from.col() != to.col() && from.row() != to.row() && to.getPiece() == null)
            return true;
        return false;
    }

    private void performEnPassentMove(Square from, Square to, IPiece pawn) {
        Square capturedPawnSquare = board.get(to.col(), from.row());

        from.setPiece(null);
        to.setPiece(pawn);
        capturedPawnSquare.setPiece(null);
    }

    private void afterMovePerformed(Square from, Square to, IPiece piece) {

        if (piece instanceof ICastleable)
            // Stop this piece from being involved in castle-move
            ((ICastleable) piece).stopAllowCastling();

        if (piece instanceof Pawn && isPawnDoubleStep(from, to))
            // Allow moved pawn to be captured by en passent-move
            ((Pawn) piece).setEnPassentAllowed(true);

        if (piece instanceof King)
            // Update kingposition if king is moved
            board.setKingSquare(to, toDraw);

        updateLegalMoves(toDraw);
        if (board.isThreatendBy(board.getKingSquare(toDraw.toggle()), toDraw))
            // Set inCheck to true when king is threatened
            board.setCheck(true, toDraw.toggle());

        this.selectedSquare = null;
        toggleTurn();
        updateLegalMoves(toDraw);
    }

    private boolean isPawnDoubleStep(Square from, Square to) {
        if (from.row() == 2 && to.row() == 4 || from.row() == 7 && to.row() == 5)
            return true;

        return false;
    }

    private void toggleTurn() {
        this.toDraw = this.toDraw.toggle();
    }

    private void updateLegalMoves(ChessColor color) {
        for (Square square : board) {
            IPiece piece = square.getPiece();
            if (piece != null && piece.getColor().equals(color)) {
                square.getPiece().updateLegalMoves(board, square);
            }
        }
    }

}
