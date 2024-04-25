package no.uib.inf101.grid;

// Hentet fra: https://git.app.uib.no/ii/inf101/24v/assignments/Kristian.E.Johansen_sem1-tetris
// Opphaver: Torstein Strømme. Hentet: 10.04.24

/**
 * Represents a cell in a grid data structure. Each cell is associated with a
 * position and a value.
 * 
 * @param <E> The type of value stored in the cell.
 */
public record GridCell<E>(CellPosition pos, E value) {
}
