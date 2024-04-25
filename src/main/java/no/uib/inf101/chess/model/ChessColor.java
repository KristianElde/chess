package no.uib.inf101.chess.model;

/**
 * The ChessColor enum represents the colors of pieces in chess, black and
 * white. It provides a method to toggle between the two colors.
 */
public enum ChessColor {
    BLACK, WHITE;

    /**
     * Toggles the current color to its opposite color.
     *
     * @return The opposite color.
     */
    public ChessColor toggle() {
        return (this == WHITE ? BLACK : WHITE);
    }
}