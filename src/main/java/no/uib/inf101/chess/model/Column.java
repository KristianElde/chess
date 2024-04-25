package no.uib.inf101.chess.model;

/**
 * The Column enum represents the columns of a chess board. It provides methods
 * to navigate to the next or previous column, as well as methods to navigate
 * two columns ahead or behind.
 */
public enum Column {
    A, B, C, D, E, F, G, H;

    /**
     * Returns the column next to the current column.
     *
     * @return The next column, or null if the current column is the last column.
     */
    public Column nextCol() {
        int nextOrdinal = this.ordinal() + 1;
        if (nextOrdinal > 7) {
            return null;
        }
        return Column.values()[nextOrdinal];
    }

    /**
     * Returns the column two columns ahead of the current column.
     *
     * @return The column two columns ahead, or null if the current column is one of
     *         the last two columns.
     */
    public Column nextNextCol() {
        int nextOrdinal = this.ordinal() + 2;
        if (nextOrdinal > 7) {
            return null;
        }
        return Column.values()[nextOrdinal];
    }

    /**
     * Returns the column previous to the current column.
     *
     * @return The previous column, or null if the current column is the first
     *         column.
     */
    public Column prevCol() {
        int prevOrdinal = this.ordinal() - 1;
        if (prevOrdinal < 0) {
            return null;
        }
        return Column.values()[prevOrdinal];
    }

    /**
     * Returns the column two columns behind the current column.
     *
     * @return The column two columns behind, or null if the current column is one
     *         of the first two columns.
     */
    public Column prevPrevCol() {
        int prevOrdinal = this.ordinal() - 2;
        if (prevOrdinal < 0) {
            return null;
        }
        return Column.values()[prevOrdinal];
    }
}