package no.uib.inf101.chess.model;

import java.util.ArrayList;

import no.uib.inf101.chess.model.pieces.IPiece;
import no.uib.inf101.chess.model.pieces.Pawn;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class ChessBoard extends Grid<Square> {

    public ChessBoard() {
        super(8, 8);

        for (int row = 1; row < 9; row++) {
            for (Column col : Column.values()) {
                Square square = new Square(col, row);
                this.set(new CellPosition(row - 1, col.ordinal()), square);
            }
        }

        this.get(Column.A, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.B, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.C, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.D, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.E, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.F, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.G, 2).setPiece(new Pawn(ChessColor.WHITE));
        this.get(Column.H, 2).setPiece(new Pawn(ChessColor.WHITE));

        this.get(Column.A, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.B, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.C, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.D, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.E, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.F, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.G, 7).setPiece(new Pawn(ChessColor.BLACK));
        this.get(Column.H, 7).setPiece(new Pawn(ChessColor.BLACK));
    }

    // OVERIDE??
    public Square get(Column col, int row) {
        return super.get(new CellPosition(row - 1, col.ordinal()));
    }
}
