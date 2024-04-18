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

    ChessBoard() {
        super(8, 8);

        for (int row = 1; row < 9; row++) {
            for (Column col : Column.values()) {
                Square square = new Square(col, row);
                set(new CellPosition(row - 1, col.ordinal()), square);
            }
        }

        get(Column.A, 1).setPiece(new Rook(ChessColor.WHITE));
        get(Column.B, 1).setPiece(new Knight(ChessColor.WHITE));
        get(Column.C, 1).setPiece(new Bishop(ChessColor.WHITE));
        get(Column.D, 1).setPiece(new Queen(ChessColor.WHITE));
        get(Column.E, 1).setPiece(new King(ChessColor.WHITE));
        get(Column.F, 1).setPiece(new Bishop(ChessColor.WHITE));
        get(Column.G, 1).setPiece(new Knight(ChessColor.WHITE));
        get(Column.H, 1).setPiece(new Rook(ChessColor.WHITE));
        get(Column.A, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.B, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.C, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.D, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.E, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.F, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.G, 2).setPiece(new Pawn(ChessColor.WHITE));
        get(Column.H, 2).setPiece(new Pawn(ChessColor.WHITE));

        get(Column.A, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.B, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.C, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.D, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.E, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.F, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.G, 7).setPiece(new Pawn(ChessColor.BLACK));
        get(Column.H, 7).setPiece(new Pawn(ChessColor.BLACK));

        get(Column.A, 8).setPiece(new Rook(ChessColor.BLACK));
        get(Column.B, 8).setPiece(new Knight(ChessColor.BLACK));
        get(Column.C, 8).setPiece(new Bishop(ChessColor.BLACK));
        get(Column.D, 8).setPiece(new Queen(ChessColor.BLACK));
        get(Column.E, 8).setPiece(new King(ChessColor.BLACK));
        get(Column.F, 8).setPiece(new Bishop(ChessColor.BLACK));
        get(Column.G, 8).setPiece(new Knight(ChessColor.BLACK));
        get(Column.H, 8).setPiece(new Rook(ChessColor.BLACK));

        whiteKingSquare = get(Column.E, 1);
        blackKingSquare = get(Column.E, 8);

        updateLegalMoves(toDraw.toggle(), true);
        updateLegalMoves(toDraw, true);
    }

    private Square getKingSquare(ChessColor color) {
        return (color == ChessColor.WHITE ? whiteKingSquare : blackKingSquare);
    }

    private void setKingSquare(Square kingSquare, ChessColor color) {
        if (color == ChessColor.WHITE)
            whiteKingSquare = kingSquare;
        else
            blackKingSquare = kingSquare;
    }

    boolean isInCheck(ChessColor color) {
        return (color == ChessColor.WHITE ? whiteInCheck : blackInCheck);
    }

    private void setCheck(boolean isCheck, ChessColor color) {
        if (color == ChessColor.WHITE) {
            whiteInCheck = isCheck;
        } else {
            blackInCheck = isCheck;
        }
    }

    ChessColor getToDraw() {
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
        if (piece instanceof King && isCastleMove(from, to, (King) piece))
            capturedPiece = performCastlingMove(from, to, (King) piece);

        // En passent move
        else if (piece instanceof Pawn && isEnPassentMove(from, to)) {
            capturedPiece = performEnPassentMove(from, to, piece);
            ((Pawn) capturedPiece).setCapturedByEnPassent(true);
            return capturedPiece;
        }

        else if (piece instanceof Pawn && isPawnPromotion(from, to, piece))
            performPawnPromotionMove(from, to);

        // Regular move
        else {
            from.setPiece(null);
            to.setPiece(piece);
        }

        setStateVariablesAfterMove(from, to, piece);
        return capturedPiece;
    }

    public boolean testMoveIsLegal(Square from, Square to, Piece piece) {
        boolean isLegalMove = true;
        boolean isCheck = isInCheck(toDraw);
        boolean allowCastling = false;
        if (piece instanceof CastleablePiece)
            allowCastling = ((CastleablePiece) piece).getAllowCastling();

        Piece capturedPiece = movePiece(from, to, piece);
        if (isThreatendBy(getKingSquare(piece.getColor()), piece.getColor().toggle()))
            isLegalMove = false;

        resetStateVariablesAfterMove(from, to, piece, capturedPiece, isCheck, allowCastling);
        undoMove(from, to, piece, capturedPiece);

        return isLegalMove;
    }

    private void undoMove(Square from, Square to, Piece piece, Piece capturedPiece) {

        if (piece instanceof King && isCastleMove(from, to, (King) piece)) {
            undoCastlingMove(from, to, (King) piece, (Rook) capturedPiece);
            return;
        }

        if (piece instanceof Pawn && capturedPiece instanceof Pawn && ((Pawn) capturedPiece).getCaptureByEnPassent()) {
            undoEnPassentMove(from, to, piece, ((Pawn) capturedPiece));
            return;
        }

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
    }

    private void setStateVariablesAfterMove(Square from, Square to, Piece piece) {

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

        if (isThreatendBy(getKingSquare(piece.getColor().toggle()), piece.getColor()))
            // Set inCheck to true when king is threatened
            setCheck(true, toDraw.toggle());

        toggleTurn();
    }

    private void resetStateVariablesAfterMove(Square from, Square to, Piece piece, Piece capturedPiece,
            boolean isCheck, boolean allowCastling) {
        toggleTurn();

        if (piece instanceof CastleablePiece)
            ((CastleablePiece) piece).setAllowCastling(allowCastling);

        if (piece instanceof King && isCastleMove(from, to, ((King) piece))) {
            ((King) piece).setAllowCastling(true);
            ((Rook) capturedPiece).setAllowCastling(true);
        }

        if (piece instanceof Pawn && isPawnDoubleStep(from, to))
            ((Pawn) piece).setEnPassentAllowed(false);

        if (piece instanceof King)
            setKingSquare(from, toDraw);

        setCheck(isCheck, toDraw);

        setCheck(false, toDraw.toggle());
    }

    private boolean isPawnDoubleStep(Square from, Square to) {
        return from.row() == 2 && to.row() == 4 || from.row() == 7 && to.row() == 5;
    }

    private boolean isPawnPromotion(Square from, Square to, Piece piece) {
        int lastRow = (piece.getColor() == ChessColor.WHITE ? 8 : 1);

        return to.row() == lastRow;
    }

    private void performPawnPromotionMove(Square from, Square to) {
        // Piece promotePawnTo =

        from.setPiece(null);
        to.setPiece(new Queen(toDraw));
    }

    void toggleTurn() {
        toDraw = toDraw.toggle();
    }

    void updateLegalMoves(ChessColor color, boolean primitive) {
        for (Square square : this) {
            Piece piece = square.getPiece();
            if (piece != null && piece.getColor().equals(color)) {
                square.getPiece().updateLegalMoves(this, square, primitive);
            }
        }
    }

    public boolean isThreatendBy(Square square, ChessColor threatenedBy) {
        for (Square currentSquare : this) {
            if (currentSquare.getPiece() != null && currentSquare.getPiece().getColor() == threatenedBy) {
                if (currentSquare.getPiece().calculateLegalMoves(this, currentSquare, true).contains(square))
                    return true;
            }
        }
        return false;
    }

    public static ChessBoard stringToBoard(String boardString, ChessColor toDraw) {
        if (boardString.length() != 71)
            throw new IllegalArgumentException("Illegal number of cells in boardString:" + boardString.length());

        ChessBoard board = new ChessBoard();
        for (Square square : board) {
            square.setPiece(null);
        }

        int row = 8;
        int colOrdinal = 0;
        for (char c : boardString.toCharArray()) {
            if (c == '\n') {
                row--;
                colOrdinal = 0;
                continue;
            }

            Column col = Column.values()[colOrdinal];
            Piece piece = charToPiece(c);
            if (piece instanceof King)
                board.setKingSquare(board.get(col, row), board.getToDraw());

            if (piece instanceof CastleablePiece && !isInitialPosition(((CastleablePiece) piece), board.get(col, row)))
                ((CastleablePiece) piece).setAllowCastling(false);

            board.get(col, row).setPiece(piece);
            colOrdinal++;
        }

        if (board.getToDraw() != toDraw)
            board.toggleTurn();

        board.updateLegalMoves(board.getToDraw().toggle(), true);
        board.updateLegalMoves(board.getToDraw(), false);
        if (board.isThreatendBy(board.getKingSquare(board.getToDraw()), board.getToDraw().toggle()))
            board.setCheck(true, board.getToDraw());

        return board;
    }

    private static boolean isInitialPosition(CastleablePiece piece, Square square) {
        int row = (piece.getColor() == ChessColor.WHITE ? 1 : 8);

        if (piece instanceof King)
            return square.col() == Column.E && square.row() == row;

        return (square.col() == Column.A || square.col() == Column.H) && square.row() == row;
    }

    private static Piece charToPiece(char c) {
        switch (c) {
            case '-':
                return null;
            case 'P':
                return new Pawn(ChessColor.WHITE);
            case 'p':
                return new Pawn(ChessColor.BLACK);
            case 'N':
                return new Knight(ChessColor.WHITE);
            case 'n':
                return new Knight(ChessColor.BLACK);
            case 'B':
                return new Bishop(ChessColor.WHITE);
            case 'b':
                return new Bishop(ChessColor.BLACK);
            case 'R':
                return new Rook(ChessColor.WHITE);
            case 'r':
                return new Rook(ChessColor.BLACK);
            case 'Q':
                return new Queen(ChessColor.WHITE);
            case 'q':
                return new Queen(ChessColor.BLACK);
            case 'K':
                return new King(ChessColor.WHITE);
            case 'k':
                return new King(ChessColor.BLACK);

            default:
                throw new IllegalArgumentException("The character: " + c + " does not correspond to any piece.");
        }

    }
}
