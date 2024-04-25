package no.uib.inf101.grid;

// Hentet fra: https://git.app.uib.no/ii/inf101/24v/assignments/Kristian.E.Johansen_sem1-tetris
// Opphaver: Torstein Strømme. Hentet: 10.04.24

/**
 * Represents a position in a two-dimensional grid or matrix. The position is
 * defined by its row and column coordinates. This class is immutable and can be
 * used to represent the position of cells in various grid-based data structures
 * or games.
 */
public record CellPosition(int row, int col) {
}
