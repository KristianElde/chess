package no.uib.inf101.chess.model.pieces;

import java.util.ArrayList;

import no.uib.inf101.chess.model.ChessBoard;
import no.uib.inf101.chess.model.ChessColor;
import no.uib.inf101.chess.model.Square;

/**
 * The Piece class represents a chess piece on the board.
 * It serves as an abstract base class for specific types of chess pieces.
 */
public abstract class Piece implements MoveablePiece {

    /**
     * The list of legal moves available to the piece.
     */
    private ArrayList<Square> legalMoves = new ArrayList<>();

    /**
     * The color of the piece.
     */
    private ChessColor color;

    /**
     * The material value of the piece.
     */
    private int materialValue;

    /**
     * Constructs a new piece with the specified color and material value.
     *
     * @param color         The color of the piece.
     * @param materialValue The material value of the piece.
     */
    Piece(ChessColor color, int materialValue) {
        this.color = color;
        this.materialValue = materialValue;
    }

    /**
     * Retrieves the list of legal moves available to the piece.
     *
     * @return The list of legal moves.
     */
    public ArrayList<Square> getLegalMoves() {
        return legalMoves;
    }

    /**
     * Updates the legal moves variable for the piece based on the current board
     * position.
     *
     * @param board         The chess board.
     * @param currentSquare The current square occupied by the piece.
     * @param primitive     Indicates whether to perform primitive move calculation.
     *                      Primitive move calculation does not check whether a move
     *                      puts its own king in check.
     */
    public void updateLegalMoves(ChessBoard board, Square currentSquare, boolean primitive) {
        legalMoves = calculateLegalMoves(board, currentSquare, primitive);
    }

    /**
     * Retrieves the color of the piece.
     *
     * @return The color of the piece.
     */
    public ChessColor getColor() {
        return color;
    }

    /**
     * Removes moves from the list of legal moves that result in the player's own
     * king being in check.
     *
     * @param legalMoves    The list of legal moves to check.
     * @param board         The current chess board.
     * @param currentSquare The current square occupied by the piece.
     * @return The list of legal moves after removing moves that result in the
     *         player's own king being in check.
     */
    ArrayList<Square> removeInCheckMoves(ArrayList<Square> legalMoves, ChessBoard board, Square currentSquare) {
        ArrayList<Square> illegalMoves = new ArrayList<>();

        for (Square toSquare : legalMoves) {
            if (!board.testMoveIsLegal(currentSquare, toSquare, this))
                illegalMoves.add(toSquare);
        }

        legalMoves.removeAll(illegalMoves);

        return legalMoves;
    }

    /**
     * Retrieves the material value of the piece.
     *
     * @return The material value of the piece.
     */
    public int getMaterialValue() {
        return materialValue;
    }

}
