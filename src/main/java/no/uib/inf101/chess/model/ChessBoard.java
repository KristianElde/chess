package no.uib.inf101.chess.model;

import no.uib.inf101.chess.model.pieces.Bishop;
import no.uib.inf101.chess.model.pieces.CastleablePiece;
import no.uib.inf101.chess.model.pieces.Piece;
import no.uib.inf101.chess.model.pieces.King;
import no.uib.inf101.chess.model.pieces.Knight;
import no.uib.inf101.chess.model.pieces.Pawn;
import no.uib.inf101.chess.model.pieces.Queen;
import no.uib.inf101.chess.model.pieces.Rook;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class ChessBoard extends Grid<Square> {

    private ChessColor toDraw = ChessColor.WHITE;
    private Square whiteKingSquare;
    private Square blackKingSquare;
    private boolean whiteInCheck = false;
    private boolean blackInCheck = false;

    public ChessBoard() {
        super(8, 8);

        for (int row = 1; row < 9; row++) {
            for (Column col : Column.values()) {
                Square square = new Square(col, row);
                set(new CellPosition(row - 1, col.ordinal()), square);
            }
        }

        Rook whiteKingSideRook = new Rook(ChessColor.WHITE);
        Rook whiteQueenSideRook = new Rook(ChessColor.WHITE);
        King whiteKing = new King(ChessColor.WHITE, whiteKingSideRook, whiteQueenSideRook);
        // hei

        get(Column.A, 1).setPiece(whiteQueenSideRook);
        get(Column.B, 1).setPiece(new Knight(ChessColor.WHITE));
        get(Column.C, 1).setPiece(new Bishop(ChessColor.WHITE));
        get(Column.D, 1).setPiece(new Queen(ChessColor.WHITE));
        get(Column.E, 1).setPiece(whiteKing);
        get(Column.F, 1).setPiece(new Bishop(ChessColor.WHITE));
        get(Column.G, 1).setPiece(new Knight(ChessColor.WHITE));
        get(Column.H, 1).setPiece(whiteKingSideRook);
        get(Column.A, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.B, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.C, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.D, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.E, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.F, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.G, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.H, 2).setPiece(new Pawn(ChessColor.WHITE));

        Rook blackKingSideRook = new Rook(ChessColor.BLACK);
        Rook blackQueenSideRook = new Rook(ChessColor.BLACK);
        King blackKing = new King(ChessColor.BLACK, blackKingSideRook, blackQueenSideRook);

        get(Column.A, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.B, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.C, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.D, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.E, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.F, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.G, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.H, 7).setPiece(new Pawn(ChessColor.BLACK));

        get(Column.A, 8).setPiece(blackQueenSideRook);
        get(Column.B, 8).setPiece(new Knight(ChessColor.BLACK));
        get(Column.C, 8).setPiece(new Bishop(ChessColor.BLACK));
        get(Column.D, 8).setPiece(new Queen(ChessColor.BLACK));
        get(Column.E, 8).setPiece(blackKing);
        get(Column.F, 8).setPiece(new Bishop(ChessColor.BLACK));
        get(Column.G, 8).setPiece(new Knight(ChessColor.BLACK));
        get(Column.H, 8).setPiece(blackKingSideRook);

        whiteKingSquare = get(Column.E, 1);
        blackKingSquare = get(Column.E, 8);

        updateLegalMoves(toDraw.toggle());
        updateLegalMoves(toDraw);
    }

    Square getKingSquare(ChessColor color) {
        return (color == ChessColor.WHITE ? whiteKingSquare : blackKingSquare);
    }

    void setKingSquare(Square kingSquare, ChessColor color) {
        if (color == ChessColor.WHITE)
            whiteKingSquare = kingSquare;
        else
            blackKingSquare = kingSquare;
    }

    public boolean getInCheck(ChessColor color) {
        return (color == ChessColor.WHITE ? whiteInCheck : blackInCheck);
    }

    void setCheck(boolean isCheck, ChessColor color) {
        if (color == ChessColor.WHITE)
            whiteInCheck = isCheck;
        else
            blackInCheck = isCheck;
    }

    public ChessColor getToDraw() {
        return toDraw;
    }

    // OVERIDE?
    public Square get(Column col, int row) {
        if (col == null || row > 8 || row < 1)
            return null;
        return super.get(new CellPosition(row - 1, col.ordinal()));
    }

    Piece movePiece(Square from, Square to, Piece piece) {
        Piece capturedPiece = to.getPiece();

        // Castle move
        if (piece instanceof King && isCastleMove(from, to, (King) piece)) {
            capturedPiece = performCastlingMove(from, to, (King) piece);
            return capturedPiece;
        }

        // En passent move
        if (piece instanceof Pawn && isEnPassentMove(from, to)) {
            capturedPiece = performEnPassentMove(from, to, piece);
            return capturedPiece;
        }

        // Regular move
        from.setPiece(null);
        to.setPiece(piece);
        return capturedPiece;
    }

    public boolean testMoveIsLegal(Square from, Square to, Piece piece) {
        boolean legalMove = true;

        Piece capturedPiece = movePiece(from, to, piece);
        if (isThreatendBy(getKingSquare(toDraw), toDraw.toggle()))
            legalMove = false;

        undoMove(from, to, piece, capturedPiece);

        return legalMove;
    }

    private void undoMove(Square from, Square to, Piece piece, Piece capturedPiece) {

        if (piece instanceof King && isCastleMove(from, to, (King) piece))
            undoCastlingMove(from, to, (King) piece, ((Rook) capturedPiece));

        if (piece instanceof Pawn && isEnPassentMove(from, to))
            undoEnPassentMove(from, to, piece, ((Pawn) capturedPiece));

        from.setPiece(piece);
        to.setPiece(capturedPiece);
    }

    private boolean isCastleMove(Square from, Square to, King king) {
        if (!king.getAllowCastling())
            return false;

        int row = (king.getColor() == ChessColor.WHITE ? 1 : 8);

        if (to == get(Column.C, row) || to == get(Column.G, row)) {
            return true;
        }

        return false;
    }

    private Piece performCastlingMove(Square kingFrom, Square kingTo, King king) {
        int row = (king.getColor() == ChessColor.WHITE ? 1 : 8);
        Column colFrom = (kingTo.col() == Column.C ? Column.A : Column.H);
        Column colTo = (kingTo.col() == Column.C ? Column.D : Column.F);

        Square rookFrom = get(colFrom, row);
        Piece rook = rookFrom.getPiece();
        Square rookTo = get(colTo, row);

        kingFrom.setPiece(null);
        kingTo.setPiece(king);
        rookFrom.setPiece(null);
        rookTo.setPiece(rook);

        return rook;
    }

    private void undoCastlingMove(Square kingFrom, Square kingTo, King king, Rook rook) {
        int row = (king.getColor() == ChessColor.WHITE ? 1 : 8);
        Column colFrom = (kingTo.col() == Column.C ? Column.A : Column.H);
        Column colTo = (kingTo.col() == Column.C ? Column.D : Column.F);

        Square rookFrom = get(colFrom, row);
        Square rookTo = get(colTo, row);

        kingFrom.setPiece(king);
        kingTo.setPiece(null);
        rookFrom.setPiece(rook);
        rookTo.setPiece(null);

        king.setAllowCastling(true);
        rook.setAllowCastling(true);
    }

    private boolean isEnPassentMove(Square from, Square to) {
        if (from.col() != to.col() && from.row() != to.row() && to.getPiece() == null)
            return true;
        return false;
    }

    private Piece performEnPassentMove(Square from, Square to, Piece pawn) {
        Square capturedPawnSquare = get(to.col(), from.row());
        Piece capturedPawn = capturedPawnSquare.getPiece();

        from.setPiece(null);
        to.setPiece(pawn);
        capturedPawnSquare.setPiece(null);

        return capturedPawn;
    }

    private void undoEnPassentMove(Square from, Square to, Piece pawn, Pawn capturedPawn) {
        Square capturedPawnSquare = get(to.col(), from.row());

        from.setPiece(pawn);
        to.setPiece(null);
        capturedPawnSquare.setPiece(capturedPawn);

        capturedPawn.setEnPassentAllowed(true);
    }

    void setStateVariablesAfterMove(Square from, Square to, Piece piece) {
        updateLegalMoves(toDraw);

        if (piece instanceof CastleablePiece)
            // Stop this piece from being involved in castle-move
            ((CastleablePiece) piece).setAllowCastling(false);

        if (piece instanceof Pawn && isPawnDoubleStep(from, to))
            // Allow moved pawn to be captured by en passent-move
            ((Pawn) piece).setEnPassentAllowed(true);

        if (piece instanceof King)
            // Update kingposition if king is moved
            setKingSquare(to, toDraw);

        setCheck(false, toDraw);

        if (isThreatendBy(getKingSquare(toDraw.toggle()), toDraw))
            // Set inCheck to true when king is threatened
            setCheck(true, toDraw.toggle());

        toggleTurn();
        updateLegalMoves(toDraw);
    }

    private boolean isPawnDoubleStep(Square from, Square to) {
        if (from.row() == 2 && to.row() == 4 || from.row() == 7 && to.row() == 5)
            return true;

        return false;
    }

    private void toggleTurn() {
        toDraw = toDraw.toggle();
    }

    private void updateLegalMoves(ChessColor color) {
        for (Square square : this) {
            Piece piece = square.getPiece();
            if (piece != null && piece.getColor().equals(color)) {
                square.getPiece().updateLegalMoves(this, square);
            }
        }
    }

    public boolean isThreatendBy(Square square, ChessColor threatenedBy) {
        for (Square square2 : this) {
            if (square2.getPiece() != null && square2.getPiece().getColor() == threatenedBy) {
                if (square2.getPiece().getLegalMoves().contains(square))
                    return true;
            }
        }
        return false;
    }
}
