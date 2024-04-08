package no.uib.inf101.chess.model;

public enum ChessColor {
    BLACK, WHITE;

    ChessColor toggle() {
        return (this == WHITE ? BLACK : WHITE);
    }
}
