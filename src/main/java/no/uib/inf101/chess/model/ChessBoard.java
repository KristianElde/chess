package no.uib.inf101.chess.model;

import no.uib.inf101.chess.model.pieces.Bishop;
import no.uib.inf101.chess.model.pieces.King;
import no.uib.inf101.chess.model.pieces.Knight;
import no.uib.inf101.chess.model.pieces.Pawn;
import no.uib.inf101.chess.model.pieces.Queen;
import no.uib.inf101.chess.model.pieces.Rook;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class ChessBoard extends Grid<Square> {

    private Square whiteKingSquare;
    private Square blackKingSquare;
    private boolean whiteInCheck = false;
    private boolean blackInCheck = false;

    public ChessBoard() {
        super(8, 8);

        for (int row = 1; row < 9; row++) {
            for (Column col : Column.values()) {
                Square square = new Square(col, row);
                this.set(new CellPosition(row - 1, col.ordinal()), square);
            }
        }

        Rook whiteKingSideRook = new Rook(ChessColor.WHITE);
        Rook whiteQueenSideRook = new Rook(ChessColor.WHITE);
        King whiteKing = new King(ChessColor.WHITE, whiteKingSideRook, whiteQueenSideRook);
        // hei

        this.get(Column.A, 1).setPiece(whiteQueenSideRook);
        this.get(Column.B, 1).setPiece(new Knight(ChessColor.WHITE));
        this.get(Column.C, 1).setPiece(new Bishop(ChessColor.WHITE));
        this.get(Column.D, 1).setPiece(new Queen(ChessColor.WHITE));
        this.get(Column.E, 1).setPiece(whiteKing);
        this.get(Column.F, 1).setPiece(new Bishop(ChessColor.WHITE));
        this.get(Column.G, 1).setPiece(new Knight(ChessColor.WHITE));
        this.get(Column.H, 1).setPiece(whiteKingSideRook);
        this.get(Column.A, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.B, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.C, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.D, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.E, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.F, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.G, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.H, 2).setPiece(new Pawn(ChessColor.WHITE));

        Rook blackKingSideRook = new Rook(ChessColor.BLACK);
        Rook blackQueenSideRook = new Rook(ChessColor.BLACK);
        King blackKing = new King(ChessColor.BLACK, blackKingSideRook, blackQueenSideRook);

        this.get(Column.A, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.B, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.C, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.D, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.E, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.F, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.G, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.H, 7).setPiece(new Pawn(ChessColor.BLACK));

        this.get(Column.A, 8).setPiece(blackQueenSideRook);
        this.get(Column.B, 8).setPiece(new Knight(ChessColor.BLACK));
        this.get(Column.C, 8).setPiece(new Bishop(ChessColor.BLACK));
        this.get(Column.D, 8).setPiece(new Queen(ChessColor.BLACK));
        this.get(Column.E, 8).setPiece(blackKing);
        this.get(Column.F, 8).setPiece(new Bishop(ChessColor.BLACK));
        this.get(Column.G, 8).setPiece(new Knight(ChessColor.BLACK));
        this.get(Column.H, 8).setPiece(blackKingSideRook);

        this.whiteKingSquare = this.get(Column.E, 1);
        this.blackKingSquare = this.get(Column.E, 8);
    }

    Square getKingSquare(ChessColor color) {
        return (color == ChessColor.WHITE ? whiteKingSquare : blackKingSquare);
    }

    void setKingSquare(Square kingSquare, ChessColor color) {
        if (color == ChessColor.WHITE)
            this.whiteKingSquare = kingSquare;
        else
            this.blackKingSquare = kingSquare;
    }

    // OVERIDE??
    public Square get(Column col, int row) {
        if (col == null || row > 8 || row < 1)
            return null;
        return super.get(new CellPosition(row - 1, col.ordinal()));
    }
}
